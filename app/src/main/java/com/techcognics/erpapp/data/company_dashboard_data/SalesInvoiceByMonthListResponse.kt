package com.techcognics.erpapp.data.company_dashboard_data

data class SalesInvoiceByMonthListResponse(
    val listOfInvoice: List<SalesInvoiceByMonthResponse> = emptyList<SalesInvoiceByMonthResponse>()
)
