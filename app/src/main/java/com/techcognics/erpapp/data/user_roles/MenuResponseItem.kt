package com.techcognics.erpapp.data.user_roles

data class MenuResponseItem(
    val authorities: List<String>,
    val children: List<Children>,
    val code: String,
    val hovered: Boolean,
    val name: String,
    val toolTip: String
)