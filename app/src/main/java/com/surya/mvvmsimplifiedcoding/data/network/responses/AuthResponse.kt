package com.surya.mvvmsimplifiedcoding.data.network.responses

import com.surya.mvvmsimplifiedcoding.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?

)