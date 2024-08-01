package com.victorkirui.calmspotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.victorkirui.calmspotter.navigation.AppNav
import com.victorkirui.calmspotter.navigation.AppRoutes
import com.victorkirui.calmspotter.ui.JournalViewModel
import com.victorkirui.calmspotter.ui.MainViewModel
import com.victorkirui.calmspotter.ui.PanicButtonViewModel
import com.victorkirui.calmspotter.ui.theme.CalmSpotterTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            val windowSize = calculateWindowSizeClass(activity = this)
            CalmSpotterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Wesee(windowWidthSizeClass = windowSize.widthSizeClass)
                }
            }
        }
    }
}

@Composable
fun Wesee(viewModel: JournalViewModel = hiltViewModel(),
          panicButtonViewModel: PanicButtonViewModel = hiltViewModel(),
          windowWidthSizeClass: WindowWidthSizeClass,
          mainViewModel: MainViewModel = hiltViewModel()){
    val firstDestination = if (mainViewModel.userName.collectAsState().value.isNullOrEmpty()){
        AppRoutes.onBoarding
    }else{
        AppRoutes.home
    }

    AppNav(firstDestination = firstDestination,
        windowWidthSizeClass = windowWidthSizeClass,
        viewModel = viewModel,
        panicButtonViewModel = panicButtonViewModel,
        ratedToday = mainViewModel.isRatedToday().collectAsState(initial = false).value!!
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}