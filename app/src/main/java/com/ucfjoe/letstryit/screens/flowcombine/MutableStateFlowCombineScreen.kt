package com.ucfjoe.letstryit.screens.flowcombine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MutableStateFlowCombineScreen(

) {
    val viewModel = viewModel<MainViewModel>()
    val posts = viewModel.posts.collectAsStateWithLifecycle()
    val username = viewModel.username.collectAsStateWithLifecycle()
    val isAuthenticated = viewModel.isAuthenticated.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Button(onClick = {
            viewModel.toggleAuthentication()
        }) { Text("Toggle Authentication") }
        Button(onClick = {
            viewModel.setUser("Joe", "description goes here", "some url")
        }) { Text("Set User") }
        Button(onClick = {
            viewModel.addPost(username.value, "post Desc", "http://yep.com/yep.jpg")
        }) { Text("Add Post") }

        Text("User is authenticated:${isAuthenticated.value}", Modifier.padding(32.dp))
        Text(text = "Authenticated Username ${username.value}", Modifier.padding(16.dp))
        Text(text = "Number of Posts ${posts.value.size}", Modifier.padding(16.dp))
    }
}
