package com.techcognics.erpapp.data.user_roles

data class Children(
    val authorities: List<Any>,
    val code: String,
    val hovered: Boolean,
    val name: String,
    val path: Any,
    val toolTip: String
)