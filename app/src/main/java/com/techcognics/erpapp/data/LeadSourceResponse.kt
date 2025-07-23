package com.techcognics.erpapp.data

import com.google.gson.annotations.SerializedName

data class LeadSourceResponse(
    @SerializedName("activeFlag") val activeFlag: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("leadSource") val leadSource: String,
)
