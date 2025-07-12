package com.techcognics.erpapp.util

import com.techcognics.erpapp.R
import com.techcognics.erpapp.data.DrawerMenu

object Constant {


    //network
    const val BASE_URL = "http://ec2-13-48-42-94.eu-north-1.compute.amazonaws.com:8080/api/"

    //Screens
    const val LOGIN_SCREEN = "LOGIN"
    const val REGISTRATION_SCREEN = "REGISTRATION"
    const val HOME_SCREEN = "HOME"
    const val DASHBOARD_SCREEN = "DASHBOARD"
    const val SALES_DASHBOARD_SCREEN = "SALES_DASHBOARD"
    const val COMPANY_DASHBOARD_SCREEN = "COMPANY_DASHBOARD"
    const val PROFILE_SCREEN = "PROFILE_SCREEN"



    //TABLE HEADER
    val headersOne = listOf(
        "Month",
        "Cash at EOM",
        "Account Payable",
        "Account Receivable",
        "Quick Ratio",
        "Current Ratio"
    )
    val headersTwo = listOf(
        "Month",
        "Cash at EOM",
        "Account Payable",
        "Account Receivable",
        "Quick Ratio",
        "Current Ratio"
    )


    val dashboardMenu = listOf(
        DrawerMenu(
            icon = R.drawable.domain,
            title = "Company Dashboard",
        ), DrawerMenu(
            icon = R.drawable.bar_chart,
            title = "Sales Dashboard",
        )
    )

    //menuList
    val menuItems = listOf(
        DrawerMenu(
            icon = R.drawable.hrm_icon,
            title = "Human Resource Management",
        ),
        DrawerMenu(
            icon = R.drawable.procurement_icon,
            title = "Procurement Management",
        ),
        DrawerMenu(
            icon = R.drawable.inventory_icon,
            title = "Inventory Management",
        ),
        DrawerMenu(
            icon = R.drawable.accounts_icon,
            title = "Accounts and Financial Management",
        ),
        DrawerMenu(
            icon = R.drawable.production_icon,
            title = "Production Management",
        ),
        DrawerMenu(
            icon = R.drawable.high_quality_icon,
            title = "Quality Management",
        ),
        DrawerMenu(
            icon = R.drawable.crm_icon,
            title = "Customer Relationship Management",
        ),
        DrawerMenu(
            icon = R.drawable.warehouse_icon,
            title = "Warehouse Management",
        ),
        DrawerMenu(
            icon = R.drawable.dispatch_icon,
            title = "Dispatch and Logistics",
        ),
        DrawerMenu(
            icon = R.drawable.franchise_icon,
            title = "Franchise Management",
        ),


        DrawerMenu(
            icon = R.drawable.project_management_icon,
            title = "Compliance Management",
        ),
        DrawerMenu(
            icon = R.drawable.asset_icon,
            title = "Asset Management",
        ),
        DrawerMenu(
            icon = R.drawable.project_icon,
            title = "Project Management",
        ),

        DrawerMenu(
            icon = R.drawable.planting_icon,
            title = "Sustainability",
        ),

        )
}