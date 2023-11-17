package com.ucfjoe.letstryit.retrofitexample

import android.util.Log
import android.widget.ProgressBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@Composable
fun RetrofitScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        val coroutineScope = rememberCoroutineScope()
        var loading by remember { mutableStateOf(false) }
        var todos by remember {
            mutableStateOf<List<Todo>>(emptyList())
        }

        LaunchedEffect(key1 = true)
        {
            coroutineScope.launch {
                loading = true
                val response = try {
                    RetrofitInstance.api.getTodos()
                } catch (e: IOException) {
                    Log.e("JOEY", "IOException, you might now have an internet connection")
                    loading = false
                    return@launch
                } catch (e: HttpException) {
                    Log.e("JOEY", "HttpException, unexpected response. website may be down")
                    loading = false
                    return@launch
                }
                if (response.isSuccessful && response.body() != null) {
                    todos = response.body()!!
                    Log.d("JOEY", "Success ${todos.size}")
                } else {
                    Log.e("JOEY", "Response not successful")
                }
                loading = false
            }
        }

        if (loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp)
                    )
                }
            }
        }
        // TODO ("Use ProgressBar")
        //Text("Hello World")
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            content = {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 25.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text("List of TODOs")
                    }
                }
                items(todos) { todo ->
                    TodoItem(todo)
                }
            })
    }
}
