package com.ucfjoe.letstryit.marvel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucfjoe.letstryit.marvel.apidata.Character
import com.ucfjoe.letstryit.marvel.apidata.ComicList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp),
        onClick = { expanded = !expanded }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        {
            Text(character.name ?: "Unknown Name")
            if (expanded && !character.description.isNullOrBlank()) {
                Text(character.description)
            }
            if (expanded && character.comics != null && (character.comics.available ?: 0) > 0) {
                ExpandedComicContent(comicList = character.comics)
            }
        }
    }
}

@Composable
fun ExpandedComicContent(
    comicList: ComicList
) {
    Spacer(modifier = Modifier.height(10.dp))
    Text("Number of Comics: ${comicList.available}")
    comicList.items!!.forEach {
        Text(it.name ?: "[Missing Comic Name]")
    }
}
