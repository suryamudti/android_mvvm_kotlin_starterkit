package com.surya.mvvmsimplifiedcoding

import android.app.Application
import com.surya.mvvmsimplifiedcoding.data.db.AppDatabase
import com.surya.mvvmsimplifiedcoding.data.network.MyApi
import com.surya.mvvmsimplifiedcoding.data.network.NetworkConnectionInterceptor
import com.surya.mvvmsimplifiedcoding.data.repositories.QuotesRepository
import com.surya.mvvmsimplifiedcoding.data.repositories.UserRepository
import com.surya.mvvmsimplifiedcoding.ui.auth.AuthViewModelFactory
import com.surya.mvvmsimplifiedcoding.ui.home.profile.ProfileViewModelFactory
import com.surya.mvvmsimplifiedcoding.ui.home.quotes.QuotesViewModel
import com.surya.mvvmsimplifiedcoding.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware  {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(),instance()) }
        bind() from singleton { QuotesRepository(instance(),instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }

    }

}