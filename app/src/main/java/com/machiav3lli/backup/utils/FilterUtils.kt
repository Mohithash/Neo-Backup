/*
 * OAndBackupX: open-source apps backup and restore app.
 * This is a package util class that contains functions for filtering and sorting packages.
 */
package com.machiav3lli.backup.utils

import android.content.Context
import android.content.Intent
import //...

// filterPackages: Filters a list of packages based on the given filter, specialFilter, whiteList, and blackList.
// This function first filters the packages based on the whiteList, if provided. Then, it applies a predicate to
// filter the packages based on the mainFilter (system, user, or special apps). Finally, it applies the specialFilter
// and sorts the packages based on their package labels.
fun filterPackages(
    packages: List<Package>,
    filter: Int,
    specialFilter: SpecialFilter,
    whiteList: List<String> = emptyList(),
    blackList: List<String>,
): List<Package> {
    //...
}

// applyFilter: Filters a list of packages based on the given filter and context.
// This function first applies a predicate to filter the packages based on the mainFilter (system, user, or special apps).
// Then, it applies a backup filter based on the backupFilter (none, apk, data, etc.). After that, it applies the
// specialFilter and sorts the packages based on the sort and sortAsc parameters.
fun List<Package>.applyFilter(filter: SortFilterModel, context: Context): List<Package> {
    //...
}

// applyBackupFilter: Filters a list of packages based on the given backupFilter.
// This function applies a predicate to filter the packages based on the backupFilter (none, apk, data, etc.).
private fun List<Package>.applyBackupFilter(backupFilter: Int): List<Package> {
    //...
}

// applySpecialFilter: Filters a list of packages based on the given specialFilter and context.
// This function applies a predicate to filter the packages based on the specialFilter (installed, launchable, updated, etc.).
private fun List<Package>.applySpecialFilter(
    specialFilter: SpecialFilter,
    context: Context,
): List<Package> {
    //...
}

// applySort: Sorts a list of packages based on the given sort and sortAsc parameters.
private fun List<Package>.applySort(sort: Int, sortAsc: Boolean): List<Package> =
    //...

// filterToString: Returns a string representation of the given filter.
// This function returns a string representation of the given filter based on the active filters.
fun filterToString(context: Context, filter: Int): String {
    //...
}

// specialFilterToString: Returns a string representation of the given specialFilter.
// This function returns a string representation of the given specialFilter based on the active filters.
fun specialFilterToString(context: Context, specialFilter: SpecialFilter) = listOfNotNull(
    //...
).joinToString(", ")

