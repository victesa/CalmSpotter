package com.victorkirui.calmspotter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun rememberAppState(): AppState{

    val context = LocalContext.current

    return remember() {
        AppState(context)
    }

}

class AppState(
    context: Context
){



}