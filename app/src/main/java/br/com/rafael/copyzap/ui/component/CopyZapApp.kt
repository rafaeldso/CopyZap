package br.com.rafael.copyzap.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        CopyZapNavHost(
            clipboardViewModel = clipboardViewModel,
            navController = navController
        )
    }
}

@Composable
fun CopyZapNavHost(navController: NavHostController, clipboardViewModel: ClipboardViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(clipboardViewModel = clipboardViewModel, onNavigateToResult = {
                navController.navigate("result")
            })
        }
        composable(
            "result"
        ) { backStackEntry ->
            ResultScreen(clipboardViewModel = clipboardViewModel, onBackToStack = {
                navController.popBackStack()
            })
        }
    }
}
