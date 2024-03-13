#!/bin/bash

# Function to kill all background processes of a package
kill_package_pids() {
  local package=$1
  local userid=$2

  # Get pids of package processes
  local pids=$(
    (
      ps -A -o PID -u $userid | tail -n +2
      ls -l /proc/*/fd/* 2>/dev/null |
          grep -E "/data/data/$package/|/media/" |
          grep -F /$package/ |
          cut -s -d / -f 3
    ) | sort -u -n
  )

  # Stop background processes
  if [[ -n $pids ]]; then
    ps -A -w -o USER,PID,NAME -p $pids |
      while read -r user pid process; do
        if [[ $user != u0_* ]]; then continue; fi
        if [[ $process == android.process.media || $process == com.android.externalstorage ]]; then continue; fi
        kill -STOP $pid && echo $pid
      done
  fi
}

# Parse command line arguments
while getopts "c:u:s" opt; do
  case $opt in
    c)
      command=$OPTARG
      ;;
    u)
      userid=$OPTARG
      ;;
    s)
      suspend=true
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      exit 1
      ;;
    :)
      echo "Option -$OPTARG requires an argument." >&2
      exit 1
      ;;
  esac
done

# Check if command is valid
if [[ -z $command ]]; then
  echo "Invalid command. Use -c <command> where command is one of pre-backup, post-backup, pre-restore, or post-restore." >&2
  exit 1
fi

# Check if userid is valid
if ! id -u -r "$userid" >/dev/null 2>&1; then
  echo "Invalid userid. Userid must be a valid user id." >&2
  exit 1
fi

package=$1
shift

case $command in
  pre-backup)
    # Stop package processes
    if $suspend; then
      pm suspend "$package" >/dev/null
      sleep 3
    fi
    kill_package_pids "$package" "$userid"
    ;;
  post-backup)
    # Kill package processes
    am kill "$package"

    # Restart background processes
    if [[ -n $* ]]; then
      kill -CONT "$@"
    fi

    if $suspend; then
      pm unsuspend "$package"
    fi
    ;;
  pre-restore)
    # Stop app
