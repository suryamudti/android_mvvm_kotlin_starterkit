package com.surya.mvvmsimplifiedcoding.ui.home.quotes

import androidx.lifecycle.ViewModel;
import com.surya.mvvmsimplifiedcoding.data.repositories.QuotesRepository
import com.surya.mvvmsimplifiedcoding.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}
