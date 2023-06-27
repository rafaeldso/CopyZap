package br.com.rafael.copyzap.ui.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.rafael.copyzap.viewModel.ClipboardViewModel

@Composable
fun CopyZapApp(clipboardViewModel: ClipboardViewModel) {
    val navController = rememberNavController()
    CopyZapNavHost(
        clipboardViewModel = clipboardViewModel,
        navController = navController
    )
}

@Composable
fun CopyZapNavHost(navController: NavHostController, clipboardViewModel: ClipboardViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home"){
            HomeScreen(clipboardViewModel = clipboardViewModel, onNavigateToResult = { formatText ->
                navController.navigate("result/${formatText}")
            })
        }
        composable(
            "result/{formatText}",
            arguments = listOf(navArgument("formatText") { type = NavType.StringType })
        ) { backStackEntry ->
            ResultScreen(formatText = backStackEntry.arguments?.getString("formatText"), clipboardViewModel = clipboardViewModel, onBackToStack = {
                navController.popBackStack()
            })
        }
    }
}
