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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vipul.dev.mypupilmesh.presentation.navigation.FaceRecognitionDest
import com.vipul.dev.mypupilmesh.presentation.navigation.MangaDest
import com.vipul.dev.mypupilmesh.presentation.ui.screens.face.FaceRecognitionScreen
import com.vipul.dev.mypupilmesh.presentation.ui.screens.manga.mangaDetails.MangaDetailsScreen
import com.vipul.dev.mypupilmesh.presentation.ui.screens.manga.mangaGridList.MangaListScreen
import com.vipul.dev.mypupilmesh.presentation.utils.navItems
import com.vipul.dev.mypupilmesh.presentation.utils.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class NavItem(
    val label: String,
    val route: String
)

@Composable
fun DashboardScreen() {

    val navController = rememberNavController()

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
        },
    ) { innerPadding ->
//        when (selectedItem) {
//            0 -> MangaScreen(navController, innerPadding)
//            1 -> FaceScreen(navController, innerPadding)

        NavHost(
            navController = navController!!,
            startDestination = "manga",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("manga") {
                MangaListScreen(){id->
                    navController.navigate("manga_details?id=${id}")
                }
            }

            composable("manga_details?id={id}", arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    nullable=false
                }
            )) {backstackEntry->
                val id  = backstackEntry.arguments?.getInt("id")
                MangaDetailsScreen(mangaId = id)
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