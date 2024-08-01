package com.victorkirui.calmspotter.ui

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.calmspotter.R

@Composable
fun PanicButtonRoute(windowWidthSizeClass: WindowWidthSizeClass,
                     navigateToMusicList: () -> Unit,
                     viewModel: PanicButtonViewModel){

    PanicButtonScreen(
        windowWidthSizeClass = windowWidthSizeClass,
        navigateToMusicList = { navigateToMusicList() },
        musicToPlay = currentMusic
    )

}

@Composable
fun PanicButtonScreen(windowWidthSizeClass: WindowWidthSizeClass,
                      navigateToMusicList: () -> Unit,
                      musicToPlay: Int){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            PanicButtonCompact(navigateToMusicList = { navigateToMusicList()},
                musicToPlay = musicToPlay)
        }

        WindowWidthSizeClass.Medium ->{
            PanicButtonMedium(navigateToMusicList = { navigateToMusicList() },
                musicToPlay = musicToPlay)
        }

        WindowWidthSizeClass.Expanded ->{
            PanicButtonExpanded()
        }
    }

}

@Composable
fun PanicButtonCompact(navigateToMusicList:()-> Unit,
                       musicToPlay: Int){
    val context = LocalContext.current

    var media: MediaPlayer? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {
        media = MediaPlayer.create(context, musicToPlay)

        onDispose {
            media?.release()
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(modifier = Modifier.fillMaxHeight(.8f),
            verticalArrangement = Arrangement.Center) {
            RoundPanicButton(buttonSize = 250.dp,
                fontSize = 36.sp,
                onClick = {
                    media?.let {
                        if (it.isPlaying) {
                            it.stop()
                            it.prepare() // Reset the MediaPlayer to be ready for next play
                        } else {
                            it.start()
                        }
                    }
                })
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {

            IconButton(onClick = { navigateToMusicList() },
                modifier = Modifier.border(width = 2.dp,
                    shape = CircleShape,
                    color = Color.Gray)) {
                Icon(painter = painterResource(id = R.drawable.list),
                    contentDescription = null,
                    tint = Color.White)
            }

        }
    }

}

@Composable
fun PanicButtonMedium(navigateToMusicList:()-> Unit,
                      musicToPlay: Int){
    val context = LocalContext.current

    var media: MediaPlayer? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {
        media = MediaPlayer.create(context, musicToPlay)

        onDispose {
            media?.release()
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(modifier = Modifier.fillMaxHeight(.8f),
            verticalArrangement = Arrangement.Center) {
            RoundPanicButton(buttonSize = 350.dp,
                fontSize = 36.sp,
                onClick = {
                    media?.let {
                        if (it.isPlaying) {
                            it.stop()
                            it.prepare() // Reset the MediaPlayer to be ready for next play
                        } else {
                            it.start()
                        }
                    }
                })
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {

            IconButton(onClick = { navigateToMusicList() },
                modifier = Modifier
                    .size(60.dp)
                    .border(width = 2.dp, shape = CircleShape, color = Color.Gray)) {
                Icon(painter = painterResource(id = R.drawable.list),
                    contentDescription = null,
                    tint = Color.White)
            }

        }
    }

}

@Composable
fun PanicButtonExpanded(){
    val musicList = listOf(R.raw.click_box_check to "CheckBox Click Sound",
        R.raw.double_click_mouse to "Double Click Mouse Sound",
        R.raw.interface_click to "Interface Click Sound",
    )

    var currentClickedButton by remember {
        mutableIntStateOf(0)
    }

    val currentMusic by remember {
        mutableIntStateOf(musicList[currentClickedButton].first)
    }

    val context = LocalContext.current

    var media: MediaPlayer? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {
        media = MediaPlayer.create(context, currentMusic)

        onDispose {
            media?.release()
        }
    }

    Row(modifier = Modifier
        .background(Color(0xFF06154D))
        .fillMaxSize()) {

        Column(modifier = Modifier
            .fillMaxWidth(.5f)
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            RoundPanicButton(buttonSize = 300.dp, fontSize = 32.sp,
                onClick = {
                    media?.let {
                        if (it.isPlaying) {
                            it.stop()
                            it.prepare() // Reset the MediaPlayer to be ready for next play
                        } else {
                            it.start()
                        }
                    }
                })

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 16.dp)) {

            Text(text = "Sound List",
                fontSize = 40.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold)



            LazyColumn(modifier = Modifier
                .padding(end = 24.dp)
                .padding(vertical = 40.dp)){
                items(3){
                    ListItem(headlineContent = {
                        Text(text = musicList[it].second, color = if (currentClickedButton == it){Color.Black}else{Color.White})},
                        modifier = Modifier
                            .clickable { currentClickedButton = it }
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
}

@Composable
fun RoundPanicButton(buttonSize: Dp,
                     fontSize: TextUnit,
                     onClick:() -> Unit){
    ElevatedButton(onClick = { onClick() },
        shape = CircleShape,
        modifier = Modifier.size(buttonSize),
        elevation = ButtonDefaults.buttonElevation(50.dp),
        colors = ButtonDefaults.buttonColors(Color.White)) {

        Text(text = "Click", fontSize = fontSize, color = Color.Black, fontWeight = FontWeight.Bold)

    }
}

@Composable
@Preview(showBackground = true)
fun PanicButtonCompactPreview(){
    PanicButtonCompact({}, 0 )
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun PanicButtonMediumPreview(){
    PanicButtonMedium({}, 0)
}

@Composable
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
fun PanicButtonExpandedPreview(){
    PanicButtonExpanded()

}