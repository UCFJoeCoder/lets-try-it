package com.ucfjoe.letstryit.screens.marvel

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ucfjoe.letstryit.screens.marvel.apidata.Character
import com.ucfjoe.letstryit.screens.marvel.apidata.ComicList

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
            if (character.thumbnail != null) {
                AsyncImage(
                    model = MarvelApiInstance.getMarvelImage(
                        character.thumbnail,
                        LocalContext.current
                    ),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.height(60.dp).width(80.dp),
                    contentDescription = "character",
                    onError = {
                        Log.e("AsyncImage", "${it.result.throwable.message}")
                    }
                )
            }
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
