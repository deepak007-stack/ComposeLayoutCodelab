package com.example.basiclayoutscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiclayoutscodelab.ui.theme.BasicLayoutsCodelabTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicLayoutsCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(activity = this)
                    MyApp(windowSize)
                }
            }
        }
    }
}

@Composable
fun MyApp(windowSize: WindowSizeClass) {

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MyAppPortrait()
        }

        WindowWidthSizeClass.Expanded -> {
            MyAppLandscape()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppPortrait() {

    BasicLayoutsCodelabTheme {
        Scaffold(
            bottomBar = { BottomNavigation(modifier = Modifier) }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }

}

@Composable
fun MyAppLandscape() {
    BasicLayoutsCodelabTheme {
        Row {
            NavigationRail(modifier = Modifier)
            HomeScreen(modifier = Modifier)
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {

        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(modifier = Modifier.padding(horizontal = 16.dp))                        // Search Bar
        HomeSection(                                                                      // Align Your Body Row
            content = { AlignYourBodyRow() },
            title = R.string.align_your_body
        )
        HomeSection(                                                                      // Favorite Collection Grid
            content = { FavoriteCollectionsGrid() },
            title = R.string.favourite_collection
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    @StringRes title: Int
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {

    TextField(value = "",
        onValueChange = {},
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        placeholder = { Text(text = stringResource(R.string.search)) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        })
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int, @StringRes text: Int, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}


@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(modifier = Modifier.width(255.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }
}


@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier,
    data: List<AlignYourBodyItem> = alignYourBodyData
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(data) { item ->
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier,
    data: List<FavoriteCollectionsGridData> = favoriteCollectionsGridData
) {

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(data) { item ->
            FavoriteCollectionCard(item.drawable, item.text, Modifier.height(80.dp))
        }
    }
}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {

//    var value by remember { mutableStateOf(true) }

    NavigationBar(modifier = modifier, containerColor = MaterialTheme.colorScheme.outlineVariant) {
        NavigationBarItem(
            icon = {
                Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "")
            },
            label = {
                Text(text = stringResource(R.string.home))
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = null)
            },
            label = {
                Text(text = stringResource(R.string.profile))
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun NavigationRail(modifier: Modifier = Modifier) {

    NavigationRail(
        modifier = Modifier.padding(end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.home)
                    )
                })
            NavigationRailItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = { Icon(imageVector = Icons.Default.Build, contentDescription = null) },
                label = {
                    Text(
                        text = stringResource(id = R.string.profile)
                    )
                })
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    BasicLayoutsCodelabTheme() {
        Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
//            MyAppPortrait()
            NavigationRail(modifier = Modifier)
        }
    }
}