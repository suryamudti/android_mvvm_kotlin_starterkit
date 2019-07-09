package com.surya.mvvmsimplifiedcoding.ui.auth

import androidx.lifecycle.LiveData
import com.surya.mvvmsimplifiedcoding.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user:User)
    fun onFailure(message: String)
}