package com.vipul.dev.mypupilmesh.presentation.ui.screens.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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


@Composable
fun DashboardScreen() {

    val navController = rememberNavController()
    val viewModel : SharedViewModel = hiltViewModel()

    val currentBackStackEntry by navController!!.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route


    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF1E1E1E), contentColor = Color.White) {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        label = { Text(text = item.label, color = Color.White) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo("manga") { inclusive = false }
                                    launchSingleTop = true
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
                MangaDetailsScreen(mangaData){
                    navController.navigateUp()
                }
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