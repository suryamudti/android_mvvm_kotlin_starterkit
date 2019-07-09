package com.surya.mvvmsimplifiedcoding.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.surya.mvvmsimplifiedcoding.data.repositories.UserRepository
import com.surya.mvvmsimplifiedcoding.util.ApiException
import com.surya.mvvmsimplifiedcoding.util.Coroutines
import com.surya.mvvmsimplifiedcoding.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
): ViewModel() {


    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null

    var authListener:AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View){

        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){

            authListener?.onFailure("INVALID EMAIL OR PASSWORD")

            return
        }

        Coroutines.main {

            try {

                val authResponse = repository.userLogin(email!!,password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }

            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }

        }
    }

    fun onSignupButtonClick(view: View){


        authListener?.onStarted()

        if (name.isNullOrEmpty()){
            authListener?.onFailure("Name is required")
            return
        }

        if (email.isNullOrEmpty()){
            authListener?.onFailure("Please enter the email")
            return
        }

        if (password.isNullOrEmpty()){
            authListener?.onFailure("Please enter a passowrd")
            return
        }


        Coroutines.main {

            try {

                val authResponse = repository.userSignup(name!!,email!!,password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }

            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }

        }

    }

    fun onSignup(view: View){
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

}