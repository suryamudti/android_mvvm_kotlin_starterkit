package com.surya.mvvmsimplifiedcoding.ui.home.profile

import androidx.lifecycle.ViewModel;
import com.surya.mvvmsimplifiedcoding.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()


}
