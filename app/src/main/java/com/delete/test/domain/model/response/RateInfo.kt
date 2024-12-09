package com.delete.test.domain.model.response

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class RateInfo(
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("timestamp")
    val timestamp: BigInteger,
    @SerializedName("base")
    val base: String,
    @SerializedName("rates")
    val rates: HashMap<String,String>,
)
