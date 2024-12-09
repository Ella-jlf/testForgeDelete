package com.delete.test.domain.model.response

import com.google.gson.annotations.SerializedName

data class CurrencyInfo(
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("symbols")
    val currencies: HashMap<String,String>,
)
