package com.vipul.dev.mypupilmesh.presentation.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vipul.dev.mypupilmesh.presentation.ui.screens.face.FaceRecognitionScreen
import com.vipul.dev.mypupilmesh.presentation.ui.screens.manga.mangaDetails.MangaDetailsScreen
import com.vipul.dev.mypupilmesh.presentation.ui.screens.manga.mangaGridList.MangaListScreen
import com.vipul.dev.mypupilmesh.presentation.utils.SharedViewModel
import com.vipul.dev.mypupilmesh.presentation.utils.navItems


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {

    val navController = rememberNavController()
    val viewModel : SharedViewModel = hiltViewModel()

    val currentBackStackEntry by navController!!.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route


    Scaffold(
        topBar = {
            TopAppBar(
                expandedHeight = 50.dp,
                title = {
                    Text(
                        text = when(currentRoute){
                            "manga"-> "Manga"
                            "manga_details"-> "Manga Details"
                            "face" -> "Face Recognition"
                            else -> "Manga"
                        },
                        /*color = Color.White*/
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                ),
                navigationIcon = {
                    if (currentRoute != "manga"){
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "navigation"/*,tint=Color.White*/)
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF1E1E1E), contentColor = Color.White) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        alwaysShowLabel = true,
                        label = { Text(text = item.label, color = Color.White) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo("manga") { inclusive = false }
                                    launchSingleTop = true
                                    restoreState=true
                                }
                            }
                        },
                        icon = { Box {} })
                }
            }
        }, containerColor = Color.Black
    ) { innerPadding ->

        NavHost(
            navController = navController!!,
            startDestination = "manga",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("manga") {
                MangaListScreen { manga ->
                    viewModel.setSelectedMangaData(manga)
                    navController.navigate("manga_details")
                }
            }

            composable(
                "manga_details"
            ) {
                val mangaData = viewModel.selectedMangaData.value
                MangaDetailsScreen(mangaData)
            }

            composable("face") {
                FaceRecognitionScreen()
            }
        }


    }

}

@Composable
@Preview(showBackground = true)
fun PreviewDashboardScreen() {
    DashboardScreen()
}