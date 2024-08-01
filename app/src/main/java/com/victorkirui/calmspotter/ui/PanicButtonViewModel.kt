package com.victorkirui.calmspotter.ui

import androidx.lifecycle.ViewModel
import com.victorkirui.calmspotter.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PanicButtonViewModel @Inject constructor(): ViewModel() {

    val _currentMusic = MutableStateFlow(R.raw.click_box_check)
    val currentMusic = _currentMusic.asStateFlow()

    val _highlight = MutableStateFlow(0)
    val highlight = _currentMusic.asStateFlow()


}