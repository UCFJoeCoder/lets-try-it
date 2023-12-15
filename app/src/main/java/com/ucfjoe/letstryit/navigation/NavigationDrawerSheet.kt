package com.ucfjoe.letstryit.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ucfjoe.letstryit.DrawerParams

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerSheet(
    selectedItemIndex: Int,
    onItemSelected: (Int, NavigationItem) -> Unit
) {
    ModalDrawerSheet {
        NavigationDrawerHeader()
        DrawerParams.drawerNavigationItems.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = { Text(text = item.title) },
                selected = index == selectedItemIndex,
                onClick = {
                    onItemSelected(index, item)
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = item.title
                    )
                },
                badge = {
                    item.badgeCount?.let {
                        Text(text = item.badgeCount.toString())
                    }
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}