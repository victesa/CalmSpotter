package com.victorkirui.calmspotter.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.calmspotter.R
import kotlinx.coroutines.flow.update

var currentButtonClicked = 0
var currentMusic = R.raw.click_box_check

@Composable
fun MusicListRoute(viewModel: PanicButtonViewModel,
                   windowWidthSizeClass: WindowWidthSizeClass,
                   navigateBackToPanicButton: () -> Unit,
                   popBack:() -> Unit){


    MusicListScreen(windowWidthSizeClass = windowWidthSizeClass,
        musicToPlay = {
            currentMusic = it.first
            currentButtonClicked = it.second
            popBack()},
        navigateBackToPanicButton = {navigateBackToPanicButton()},
        currentClickedButton = currentButtonClicked)




}

@Composable
fun MusicListScreen(windowWidthSizeClass: WindowWidthSizeClass,
                    musicToPlay: (
                        Pair<Int, Int>) -> Unit,
                    navigateBackToPanicButton:() -> Unit,
                    currentClickedButton: Int){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            MusicListCompact(musicToPlay = musicToPlay, currentClickedButton = currentClickedButton)
        }

        WindowWidthSizeClass.Medium ->{
            MusicListMedium(musicToPlay = musicToPlay)
        }

        WindowWidthSizeClass.Expanded ->{
            navigateBackToPanicButton()
        }
    }

}

@Composable
fun MusicListCompact(musicToPlay:(Pair<Int, Int>) -> Unit,
                     currentClickedButton: Int){


    val musicList = listOf(
        R.raw.click_box_check to "CheckBox Click Sound",
        R.raw.double_click_mouse to "Double Click Mouse Sound",
        R.raw.interface_click to "Interface Click Sound",
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color(0xFF06154D))
        .padding(vertical = 16.dp)
        .padding(horizontal = 16.dp)) {

        Text(text = "Sound List",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold)


        var context = LocalContext.current

        LazyColumn(modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 40.dp)){
            items(3){
                ListItem(headlineContent = {
                    Text(text = musicList[it].second, color = if (currentClickedButton == it){
                        Color.Black}else{
                        Color.White}, fontWeight = FontWeight.Bold)
                },
                    colors = ListItemDefaults.colors(containerColor = if (currentClickedButton == it) {
                        Color.White
                    } else {
                        Color.Gray
                    }),
                    modifier = Modifier
                        .clickable {
                            musicToPlay(Pair(musicList[it].first, it))}
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }

}

@Composable
fun MusicListMedium(musicToPlay: (Pair<Int, Int>) -> Unit){
    val currentClickedButton by remember {
        mutableIntStateOf(0)
    }


    val musicList = listOf(
        R.raw.click_box_check to "CheckBox Click Sound",
        R.raw.double_click_mouse to "Double Click Mouse Sound",
        R.raw.interface_click to "Interface Click Sound",
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color(0xFF06154D))
        .padding(vertical = 24.dp)) {

        Text(text = "Sound List",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold)



        LazyColumn(modifier = Modifier
            .padding(end = 24.dp)
            .padding(vertical = 40.dp)){
            items(3){
                ListItem(headlineContent = {
                    Text(text = musicList[it].second, color = if (currentClickedButton == it){
                        Color.Black}else{
                        Color.White})
                },
                    modifier = Modifier
                        .clickable { musicToPlay(Pair(musicList[it].first, it)) }
                        .background(
                            color = if (currentClickedButton == it) {
                                Color.White
                            } else {
                                Color.Transparent
                            }
                        ))

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }

}

@Composable
@Preview(showBackground = true)
fun MusicListCompactPreview(){

}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun MusicListMediumPreview(){

}