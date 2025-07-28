package com.techcognics.erpapp.data.table_data

data class SortState(
    val columnIndex: Int = -1, val order: SortOrder = SortOrder.ASCENDING
)
