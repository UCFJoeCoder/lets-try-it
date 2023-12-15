package com.ucfjoe.letstryit.screens.flowcombine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn

class MainViewModel : ViewModel() {

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    private val _user = MutableStateFlow<User?>(null)

    private val _username = MutableStateFlow("Unknown")
    val username = _username.asStateFlow()

    private val _posts = MutableStateFlow(emptyList<Post>())
    val posts = _posts.asStateFlow()

    private val _profileState = MutableStateFlow<ProfileState?>(null)
    private val profileState = _profileState.asStateFlow()


    init {
        // if either the isAuthenticated or user flows change it will check if the user is authenticated and return the user or null to trigger the next combine
        _isAuthenticated.combine(_user) { isAuthenticated, user ->
            println("IsAuthenticated $isAuthenticated")
            if (isAuthenticated) user else null
        }.combine(_posts) { user, posts ->
            println("user NOT null ? ${user != null}")
            user?.let {
                _profileState.value = profileState.value?.copy(
                    profilePicUrl = user.profilePicUrl,
                    username = user.username,
                    description = user.description,
                    posts = posts
                )
            }
        }.launchIn(viewModelScope)
    }

    fun setDescription(description: String?) {
        _user.value = _user.value?.copy(description = description)
    }

    fun setUser(username: String, description: String, profilePicUrl: String) {
        val user =
            User(username, description, profilePicUrl)
        _user.value = user
        _posts.value = emptyList()
        _username.value = user.username ?: "Unknown"
    }

    fun toggleAuthentication(){
        _isAuthenticated.value = !_isAuthenticated.value
    }

    fun addPost(username: String, description: String, imageUrl: String) {
        _posts.value += Post(
            imageUrl,
            username,
            description
        )
    }
}
