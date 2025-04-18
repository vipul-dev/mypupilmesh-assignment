package com.vipul.dev.mypupilmesh.presentation.ui.screens.manga.mangaGridList

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vipul.dev.mypupilmesh.data.remote.model.MangaData

@Composable
fun MangaListScreen(
    viewModel: MangaGridViewModel = hiltViewModel(), onItemClick: (MangaData) -> Unit
) {

    val mangaList = viewModel.mangaList
    val isLoading = viewModel.isLoading.collectAsState()
    val listState = rememberLazyListState()
    val isConnected = viewModel.isConnected.collectAsState()
    val isError = viewModel.isError.collectAsState()


    remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index > mangaList.size - 3
        }
    }


    if (isError.value==true) {
        Toast.makeText(LocalContext.current, viewModel.error.value, Toast.LENGTH_LONG).show()
    }

    if (isLoading.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {

        Column {
            if (!isConnected.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = "Offline mode",
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(
                    16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(mangaList.size) { index ->
                    ImageCard(mangaList[index]) { mangaData ->
                        onItemClick(mangaData)
                    }

                    if (index == mangaList.size - 1) {
                        LaunchedEffect(Unit) {
                            viewModel.getManga()
                        }
                    }
                }

                item {
                    if (viewModel.isPaging) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }

            }
        }
    }

}

@Composable
fun ImageCard(manga: MangaData, onItemClick: (MangaData) -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(manga.thumb).crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    onItemClick(manga)
                },
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMangaListScreen() {
    MangaListScreen {

    }
}