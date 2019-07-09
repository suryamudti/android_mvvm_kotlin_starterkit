package com.surya.mvvmsimplifiedcoding.data.network.responses

import com.surya.mvvmsimplifiedcoding.data.db.entities.Quote

data class QuotesResponse (
    val isSusccessful: Boolean,
    val quotes: List<Quote>
)