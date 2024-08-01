package com.victorkirui.calmspotter.navigation

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.victorkirui.calmspotter.ui.AffirmationsRoute
import com.victorkirui.calmspotter.ui.FeedbackRoute
import com.victorkirui.calmspotter.ui.HomeRoute
import com.victorkirui.calmspotter.ui.JournalListRoute
import com.victorkirui.calmspotter.ui.JournalRoute
import com.victorkirui.calmspotter.ui.JournalViewModel
import com.victorkirui.calmspotter.ui.MainViewModel
import com.victorkirui.calmspotter.ui.MusicListRoute
import com.victorkirui.calmspotter.ui.OnBoardingRoute
import com.victorkirui.calmspotter.ui.PanicButtonRoute
import com.victorkirui.calmspotter.ui.PanicButtonViewModel
import com.victorkirui.calmspotter.ui.SpecificJournalRoute
import com.victorkirui.calmspotter.ui.components.RateDialogRoute

@Composable
fun AppNav(firstDestination: String,
           windowWidthSizeClass: WindowWidthSizeClass,
           viewModel: JournalViewModel,
           panicButtonViewModel: PanicButtonViewModel,
           ratedToday: Boolean){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = firstDestination){
        composable(route = AppRoutes.onBoarding){
            OnBoardingRoute(windowWidthSizeClass = windowWidthSizeClass,
                navigateToHome = {
                    navController.navigate(AppRoutes.home)
                    navController.clearBackStack(AppRoutes.onBoarding)
                })
        }

        composable(route = AppRoutes.home){
            HomeRoute(
                windowWidthSizeClass = windowWidthSizeClass,
                navigateToPanicButtonScreen = { navController.navigate(AppRoutes.panicButton) },
                navigateToAffirmationsScreen = { navController.navigate(AppRoutes.affirmations) },
                navigateToJournalScreen = { navController.navigate(AppRoutes.journalList) },
                navigateToFeedbackScreen = {}
            )
        }

        composable(route = AppRoutes.affirmations){
            AffirmationsRoute(windowWidthSizeClass = windowWidthSizeClass,
                navigateBackToHome = {
                    if (ratedToday){
                        navController.popBackStack()
                    }else{
                        navController.navigate(AppRoutes.anxietyRate)
                    }
                }
            )
            HandleBackPressInNavController(navController, ratedToday)
        }

        composable(route = AppRoutes.feedback){
            FeedbackRoute()
        }

        composable(route = AppRoutes.journal){
            JournalRoute(navigateBack = {
                navController.popBackStack()},
                windowWidthSizeClass = windowWidthSizeClass)
        }

        composable(route = AppRoutes.journalList){
            HandleBackPressInNavController(navController, ratedToday)
            JournalListRoute(windowWidthSizeClass = windowWidthSizeClass,
                navigateToJournalScreen = {navController.navigate(AppRoutes.journal)},
                navigateToJournalItemScreen = {navController.navigate(AppRoutes.specificJournal)},
                viewModel = viewModel)
        }

        composable(route = AppRoutes.panicButton){
            PanicButtonRoute(windowWidthSizeClass = windowWidthSizeClass,
                navigateToMusicList = {navController.navigate(AppRoutes.musicList)},
                viewModel = panicButtonViewModel)

            HandleBackPressInNavController(navController, ratedToday)
        }

        composable(route = AppRoutes.musicList){
            MusicListRoute(
                windowWidthSizeClass = windowWidthSizeClass,
                navigateBackToPanicButton = { navController.navigate(AppRoutes.panicButton) },
                popBack = {navController.popBackStack()},
                viewModel = panicButtonViewModel)
        }

        composable(route = AppRoutes.specificJournal){
            SpecificJournalRoute(viewModel = viewModel,
                navigateBack = {navController.popBackStack()},
                windowWidthSizeClass = windowWidthSizeClass)
        }

        composable(route = AppRoutes.anxietyRate){
            RateDialogRoute(
                windowWidthSizeClass = windowWidthSizeClass,
                navigateBackToHome = {
                    navController.navigate(AppRoutes.home)
                    navController.clearBackStack(AppRoutes.anxietyRate)
                })
        }
    }

}

@Composable
fun HandleBackPressInNavController(navController: NavController, ratedToday: Boolean) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val onBackPressedDispatcherOwner = LocalOnBackPressedDispatcherOwner.current

    DisposableEffect(onBackPressedDispatcherOwner) {
        val backDispatcher = onBackPressedDispatcherOwner?.onBackPressedDispatcher
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (ratedToday){
                    navController.popBackStack()
                }else{
                    navController.navigate(AppRoutes.anxietyRate)
                }
            }
        }

        backDispatcher?.addCallback(lifecycleOwner, callback)

        onDispose {
            callback.remove()
        }
    }
}

