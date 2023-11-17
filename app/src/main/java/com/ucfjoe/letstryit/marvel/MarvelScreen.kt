package com.ucfjoe.letstryit.marvel

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucfjoe.letstryit.marvel.apidata.CharacterDataWrapper
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelScreen() {
    var loading by remember { mutableStateOf(false) }
    val showMessageDialog = remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("Thor") }
    var characterDataWrapper by remember {
        mutableStateOf(getEmptyCharacterData())
    }

    val coroutineScope = rememberCoroutineScope()

    var errorMessage = ""

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    )
    { paddingValues ->

        when {
            showMessageDialog.value -> {
                MessageDialog(
                    onDismissRequest = { showMessageDialog.value = false },
                    onConfirmation = { showMessageDialog.value = false },
                    dialogTitle = "Error using Marvel API",
                    dialogText = errorMessage,
                    icon = Icons.Default.Warning
                )
            }
        }

        if (Constants.API_KEY.isBlank()) {
            errorMessage =
                "Developer must register for api key with developer.marvel.com\n\n" +
                        "Developer must create apikey.properties file at the root of the project\n\n" +
                        "Developer must add MARVEL_API_KEY and MARVEL_PRIVATE_KEY to apikey.properties file"
            showMessageDialog.value = true
        }

        Column(
            modifier = Modifier.padding(paddingValues)
        )
        {
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
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Name") }
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = Constants.API_KEY.isNotBlank(),
                onClick = {
                    if (searchText.isNotBlank()) {
                        coroutineScope.launch {
                            loading = true
                            characterDataWrapper = getEmptyCharacterData()
                            val response = getCharactersStartsWith(searchText)
                            if (response.isSuccess()) {
                                characterDataWrapper = response.result!!
                            } else {
                                errorMessage = response.error ?: "Unknown error"
                                showMessageDialog.value = true
                            }
                            loading = false
                        }
                    }
                }
            ) {
                Text("Search")
            }
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
                            Text("List of Characters")
                        }
                    }
                    if (characterDataWrapper.data != null) {
                        items(characterDataWrapper.data?.results!!) {
                            CharacterCard(it)
                        }
                    }
                }
            )
        }
    }
}

fun getEmptyCharacterData(): CharacterDataWrapper {
    return CharacterDataWrapper(
        "",
        "",
        0,
        "",
        null,
        "",
        ""
    )
}