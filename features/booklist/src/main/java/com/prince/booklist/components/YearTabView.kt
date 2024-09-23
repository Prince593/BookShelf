package com.prince.booklist.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun YearTabView(selectedTabIndex: Int, tabs: List<String>, onTabSelected : (Int) -> Unit = {}) {
    ScrollableTabRow(
        edgePadding = 0.dp,
        selectedTabIndex = selectedTabIndex
    ) {
        // Years Tabs
        tabs.forEachIndexed { index, tabTitle ->
            Tab(
                modifier = Modifier.defaultMinSize(minHeight = 40.dp),
                unselectedContentColor = Color.Gray,
                selected = selectedTabIndex == index,
                onClick = {
                    onTabSelected(index)
                }
            ) {
                Text(text = tabTitle, style = if(selectedTabIndex == index) MaterialTheme.typography.titleMedium else MaterialTheme.typography.titleSmall)
            }
        }
    }
}

//Previews

@Preview(showBackground = true)
@Composable
fun YearTabViewPreview() {
    YearTabView(selectedTabIndex = 0, tabs = listOf("2024", "2023", "2022", "2021", "2020", "2019"))
}