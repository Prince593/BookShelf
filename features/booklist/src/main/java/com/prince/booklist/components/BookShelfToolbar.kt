package com.prince.booklist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.prince.booklist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfToolbar(
    onLogoutClick: () -> Unit = {},
    tabs: List<String> = listOf(),
    selectedTabIndex: Int = 0,
    onTabSelected: (Int) -> Unit = {},
) {
    Column {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            actions = {
                IconButton(onClick = {
                    onLogoutClick()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = null
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            )
        )
        if (tabs.isNotEmpty()) {
            YearTabView(selectedTabIndex, tabs) {
                onTabSelected(it)
            }
        }
    }
}


// Previews
@Preview(showBackground = true)
@Composable
private fun BookShelfToolbarPreview() {
    BookShelfToolbar()
}


@Preview(showBackground = true)
@Composable
private fun BookShelfToolbarWithTabsPreview() {
    BookShelfToolbar(tabs = listOf("2021", "2020", "2019", "2018", "2017"))
}