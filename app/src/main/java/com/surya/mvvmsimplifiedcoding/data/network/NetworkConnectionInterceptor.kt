package com.surya.mvvmsimplifiedcoding.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.surya.mvvmsimplifiedcoding.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    val context : Context
) :Interceptor {

    private  val applicationContext =context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable()){
            throw NoInternetException("Make sure anda punya paket data itu aktif")


        }
        return  chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean{

        val connectionManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectionManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}