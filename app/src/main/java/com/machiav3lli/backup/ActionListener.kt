/*
 * OAndBackupX: open-source apps backup and restore app.
 *
 * Copyright (C) 2020  Antonios Hazim
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.machiav3lli.backup;

import com.machiav3lli.backup.dbs.entity.Backup;
import com.machiav3lli.backup.handler.BackupRestoreHelper.ActionType;

/**
 * Listener interface for handling action events in the OAndBackupX app.
 */
public interface ActionListener {

    /**
     * Called when an action is triggered.
     *
     * @param actionType The type of action that was called.
     * @param mode       The mode in which the action was called.
     * @param backup     The backup for which the action was called, or null if not applicable.
     */
    void onActionCalled(ActionType actionType, int mode, Backup backup);
}
