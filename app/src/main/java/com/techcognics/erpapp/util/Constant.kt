package com.techcognics.erpapp.util

import com.techcognics.erpapp.data.DrawerMenu

object Constant {


    //network
    const val BASE_URL = "http://ec2-13-48-42-94.eu-north-1.compute.amazonaws.com:8080/api/"

    //Screens
    const val LOGIN_SCREEN = "LOGIN"
    const val REGISTRATION_SCREEN = "REGISTRATION"
    const val HOME_SCREEN = "HOME"
    const val CUSTOMER_SCREEN = "CUSTOMER"

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
            title = "Company Dashboard",
        ), DrawerMenu(
            title = "Sales Dashboard",
        ), DrawerMenu(
            title = "Customer"
        )

    )


}