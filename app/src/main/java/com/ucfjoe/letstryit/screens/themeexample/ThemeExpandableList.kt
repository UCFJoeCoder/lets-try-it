package com.ucfjoe.letstryit.screens.themeexample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ucfjoe.letstryit.R

data class SectionData(val headerText: String, val content: @Composable () -> Unit)

@Composable
fun ExpandableList(sections: List<SectionData>) {
    val isExpandedMap = rememberSavableSnapshotStateMap {
        List(sections.size) { index: Int -> index to true }
            .toMutableStateMap()
    }

    LazyColumn(
        content = {
            sections.onEachIndexed { index, sectionData ->
                Section(
                    sectionData = sectionData,
                    isExpanded = isExpandedMap[index] ?: true,
                    onHeaderClick = {
                        isExpandedMap[index] = !(isExpandedMap[index] ?: true)
                    }
                )
            }
        }
    )
}

private fun LazyListScope.Section(
    sectionData: SectionData,
    isExpanded: Boolean,
    onHeaderClick: () -> Unit
) {
    item {
        SectionHeader(
            text = sectionData.headerText,
            isExpanded = isExpanded,
            onHeaderClick = onHeaderClick
        )
    }
    if (isExpanded) {
        item {
            Column(modifier = Modifier.padding(8.dp)) {
                sectionData.content()
            }
        }
    }
}

@Composable
private fun SectionHeader(text: String, isExpanded: Boolean, onHeaderClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onHeaderClick() }
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier
                .weight(1.0f)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        if (isExpanded) ExpandedChevronIcon() else CollapsedChevronIcon()
    }
}

@Composable
private fun ExpandedChevronIcon() {
    Icon(
        painterResource(R.drawable.expand_more_fill_24),
        "Section open",
        modifier = Modifier.padding(6.dp)
    )
}

@Composable
private fun CollapsedChevronIcon() {
    Icon(
        painterResource(R.drawable.chevron_left_fill_24),
        "Section closed",
        modifier = Modifier.padding(6.dp),

        )
}

private fun <K, V> snapshotStateMapSaver() = Saver<SnapshotStateMap<K, V>, Any>(
    save = { state -> state.toList() },
    restore = { value ->
        @Suppress("UNCHECKED_CAST")
        (value as? List<Pair<K, V>>)?.toMutableStateMap() ?: mutableStateMapOf()
    }
)

@Composable
private fun <K, V> rememberSavableSnapshotStateMap(init: () -> SnapshotStateMap<K, V>): SnapshotStateMap<K, V> =
    rememberSaveable(saver = snapshotStateMapSaver(), init = init)