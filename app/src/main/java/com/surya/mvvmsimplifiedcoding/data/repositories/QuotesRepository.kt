package com.surya.mvvmsimplifiedcoding.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.surya.mvvmsimplifiedcoding.data.db.AppDatabase
import com.surya.mvvmsimplifiedcoding.data.db.entities.Quote
import com.surya.mvvmsimplifiedcoding.data.network.MyApi
import com.surya.mvvmsimplifiedcoding.data.network.SafeApiRequest
import com.surya.mvvmsimplifiedcoding.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository(
    private val api: MyApi,
    private val db : AppDatabase
): SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>>{

        return withContext(Dispatchers.IO){
            fetchQuote()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuote(){
        if(isFetchedNeeded()){
            val response = apiRequest { api.getQuotes()}

            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchedNeeded() :Boolean{
        return true
    }

    private fun saveQuotes(quotes: List<Quote>){
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}