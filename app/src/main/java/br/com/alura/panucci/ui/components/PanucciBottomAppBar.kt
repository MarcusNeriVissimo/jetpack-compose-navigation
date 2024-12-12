package br.com.alura.panucci.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.panucci.ui.theme.PanucciTheme

sealed class BottomAppBarItem(
    val label: String,
    val icon: ImageVector
) {
    object highlightsList : BottomAppBarItem(
        label = "Destaques",
        icon = Icons.Filled.AutoAwesome
    )

    object menu : BottomAppBarItem(
        label = "Menu",
        icon = Icons.Filled.RestaurantMenu
    )

    object drinks : BottomAppBarItem(
        label = "Bebidas",
        icon = Icons.Filled.LocalBar
    )
}

@Composable
fun PanucciBottomAppBar(
    item: BottomAppBarItem,
    modifier: Modifier = Modifier,
    items: List<BottomAppBarItem> = emptyList(),
    onItemChange: (BottomAppBarItem) -> Unit = {}
) {
    NavigationBar(modifier) {
        items.forEach {
            val label = it.label
            val icon = it.icon
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) },
                selected = item.label == label,
                onClick = {
                    onItemChange(it)
                }
            )
        }
    }
}

@Preview
@Composable
fun PanucciBottomAppBarPreview() {
    PanucciTheme {
        PanucciBottomAppBar(
            item = bottomAppBarItem.first(),
            items = bottomAppBarItem
        )
    }
}

val bottomAppBarItem = listOf(
    BottomAppBarItem.highlightsList,
    BottomAppBarItem.menu,
    BottomAppBarItem.drinks
)