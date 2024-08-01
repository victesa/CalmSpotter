package com.victorkirui.calmspotter.ui

import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.calmspotter.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun HomeRoute(viewModel: HomeViewModel = hiltViewModel(),
              windowWidthSizeClass: WindowWidthSizeClass,
              navigateToPanicButtonScreen:() -> Unit,
              navigateToAffirmationsScreen:() -> Unit,
              navigateToJournalScreen:() -> Unit,
              navigateToFeedbackScreen:() -> Unit){

    val anxietyRate by viewModel.getAverageAnxietyRate().collectAsState()
    val userName by viewModel.userName.collectAsState()



    if (!userName.isNullOrEmpty()){
        HomeScreen(
            windowWidthSizeClass = windowWidthSizeClass,
            name = userName!!,
            anxietyRate = anxietyRate,
            navigateToPanicButtonScreen = navigateToPanicButtonScreen,
            navigateToAffirmationsScreen = navigateToAffirmationsScreen,
            navigateToJournalScreen = navigateToJournalScreen,
            navigateToFeedbackScreen = navigateToFeedbackScreen
        )
    }else{
        Box(modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.Center){
            HomeScreen(
                windowWidthSizeClass = windowWidthSizeClass,
                name = "",
                anxietyRate = anxietyRate,
                navigateToPanicButtonScreen = navigateToPanicButtonScreen,
                navigateToAffirmationsScreen = navigateToAffirmationsScreen,
                navigateToJournalScreen = navigateToJournalScreen,
                navigateToFeedbackScreen = navigateToFeedbackScreen
            )

            var name by remember {
                mutableStateOf("")
            }

            Dialog(onDismissRequest = { /*TODO*/ }) {
                Column(modifier = Modifier
                    .background(Color.White)
                    .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Enter your name", fontSize = 20.sp, color = Color.Black)
                    Spacer(modifier = Modifier.height(30.dp))
                    OutlinedTextField(value = name,
                        onValueChange = {name = it},
                        placeholder = {
                            Text(text = "Name") },
                        textStyle = TextStyle(color = Color.Black)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    val context = LocalContext.current

                    Button(onClick = { if (name.isEmpty()){
                        Toast.makeText(context, "Provide Name", Toast.LENGTH_SHORT).show()
                    }else{
                        viewModel.saveUserName(name.replaceFirstChar {
                            it.uppercase()
                        })

                    } }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Save")
                    }
                }
            }
        }

    }




}

@Composable
fun HomeScreen(windowWidthSizeClass: WindowWidthSizeClass,
               name: String,
               anxietyRate: Int?,
               navigateToPanicButtonScreen:() -> Unit,
               navigateToAffirmationsScreen:() -> Unit,
               navigateToJournalScreen:() -> Unit,
               navigateToFeedbackScreen:() -> Unit){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            HomeCompact(
                name = name,
                anxietyRate = anxietyRate,
                navigateToPanicButtonScreen = navigateToPanicButtonScreen,
                navigateToAffirmationsScreen = navigateToAffirmationsScreen,
                navigateToJournalScreen = navigateToJournalScreen,
                navigateToFeedbackScreen = navigateToFeedbackScreen
            )
        }

        WindowWidthSizeClass.Medium ->{
            HomeMedium(
                name = name,
                anxietyRate = anxietyRate,
                navigateToPanicButtonScreen = navigateToPanicButtonScreen,
                navigateToAffirmationsScreen = navigateToAffirmationsScreen,
                navigateToJournalScreen = navigateToJournalScreen,
                navigateToFeedbackScreen = navigateToFeedbackScreen
            )
        }

        WindowWidthSizeClass.Expanded ->{
            HomeExpanded(
                name = name,
                anxietyRate = anxietyRate,
                navigateToPanicButtonScreen = navigateToPanicButtonScreen,
                navigateToAffirmationsScreen = navigateToAffirmationsScreen,
                navigateToJournalScreen = navigateToJournalScreen,
                navigateToFeedbackScreen = navigateToFeedbackScreen
            )
        }
    }

}

@Composable
fun HomeCompact(name: String,
                anxietyRate: Int?,
                navigateToPanicButtonScreen:() -> Unit,
                navigateToAffirmationsScreen:() -> Unit,
                navigateToJournalScreen:() -> Unit,
                navigateToFeedbackScreen:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF06154D))
        .padding(24.dp)) {

        WelcomeTopPart(name = name,
            heightPercentage = .15f,
            fontSize = 23.sp,
            subTitleFontSize = 14.sp)

        AnxietyPart(
            heightPercentage = .2f,
            percentageFontSize = 20.sp,
            textFontSize = 22.sp,
            strokeWidth = 14.dp,
            anxietyRate = anxietyRate
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.2f),
            verticalArrangement = Arrangement.Center) {
            MusicPart(musicName = "Oceans Sounds",
                titleFontSize = 16.sp,
                durationFontSize = 14.sp)
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 32.dp)) {

            Text(text = "Featured", fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                IndividualButton(
                    backgroundColor = Color(0xFF3EA587),
                    featureName = "Panic Button",
                    featureIcon = R.drawable.button_icon,
                    buttonSize = 148.dp,
                    onClick = {navigateToPanicButtonScreen()}
                )
                Spacer(modifier = Modifier.width(8.dp))

                IndividualButton(
                    backgroundColor = Color(0xFFC6A953),
                    featureName = "Quotes",
                    featureIcon = R.drawable.calm,
                    buttonSize = 148.dp,
                    onClick = {navigateToAffirmationsScreen()}
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                IndividualButton(
                    backgroundColor = Color(0xFFCB9272),
                    featureName = "Journal",
                    featureIcon = R.drawable.journal,
                    buttonSize = 148.dp,
                    onClick = {navigateToJournalScreen()}
                )

            }
        }

    }

}

@Composable
fun HomeMedium(name: String,
               anxietyRate: Int?,
               navigateToPanicButtonScreen:() -> Unit,
               navigateToAffirmationsScreen:() -> Unit,
               navigateToJournalScreen:() -> Unit,
               navigateToFeedbackScreen:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF06154D))
        .padding(32.dp)) {

        WelcomeTopPart(name = name,
            heightPercentage = .15f,
            fontSize = 30.sp,
            subTitleFontSize = 16.sp)

        AnxietyPart(
            heightPercentage = .2f,
            percentageFontSize = 24.sp,
            textFontSize = 32.sp,
            strokeWidth = 16.dp,
            anxietyRate = anxietyRate
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.2f),
            verticalArrangement = Arrangement.Center) {
            MusicPart(musicName = "Ocean Sounds",
                titleFontSize = 18.sp,
                durationFontSize = 15.sp,)
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 32.dp)) {

            Text(text = "Featured", fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                IndividualButton(
                    backgroundColor = Color(0xFF3EA587),
                    featureName = "Panic Button",
                    featureIcon = R.drawable.button_icon,
                    buttonSize = 170.dp,
                    onClick = {navigateToPanicButtonScreen()}
                )

                IndividualButton(
                    backgroundColor = Color(0xFFC6A953),
                    featureName = "Quotes",
                    featureIcon = R.drawable.calm,
                    buttonSize = 170.dp,
                    onClick = {navigateToAffirmationsScreen()}
                )

                IndividualButton(
                    backgroundColor = Color(0xFFCB9272),
                    featureName = "Journal",
                    featureIcon = R.drawable.journal,
                    buttonSize = 170.dp,
                    onClick = {navigateToJournalScreen()}
                )
            }


        }

    }

}

@Composable
fun HomeExpanded(name: String,
                 anxietyRate: Int?,
                 navigateToPanicButtonScreen:() -> Unit,
                 navigateToAffirmationsScreen:() -> Unit,
                 navigateToJournalScreen:() -> Unit,
                 navigateToFeedbackScreen:() -> Unit){
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF06154D))
        .padding(horizontal = 40.dp)) {

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.5f)
            .padding(end = 40.dp)) {

            WelcomeTopPart(
                name = name,
                heightPercentage = .15f,
                fontSize = 26.sp,
                subTitleFontSize = 23.sp
            )

            AnxietyPart(
                heightPercentage = .2f,
                percentageFontSize = 23.sp,
                textFontSize = 32.sp,
                strokeWidth = 18.dp,
                anxietyRate = anxietyRate
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.25f),
                verticalArrangement = Arrangement.Center) {
                MusicPart(
                    musicName = "Oceans Sound",
                    titleFontSize = 24.sp,
                    durationFontSize = 18.sp
                )
            }

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp)
                .background(
                    color = Color(0xFFFC869A),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(horizontal = 60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = "\"Life is 10% what happens to us and 90% how we react to it.\" ",
                    fontSize = 24.sp, textAlign = TextAlign.Center,
                    color = Color.White)

                Text(text = "~Charles R. Swindoll", color = Color.White,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 30.dp))

            }

        }

        Divider(modifier = Modifier
            .fillMaxHeight()
            .width(2.dp))

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 32.dp)
                .padding(horizontal = 40.dp)) {

                Text(text = "Featured", fontSize = 26.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold)

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    IndividualButton(
                        backgroundColor = Color(0xFF3EA587),
                        featureName = "Panic Button",
                        featureIcon = R.drawable.button_icon,
                        buttonSize = 148.dp,
                        onClick = {
                            navigateToPanicButtonScreen()
                        }
                    )

                    IndividualButton(
                        backgroundColor = Color(0xFFC6A953),
                        featureName = "Affirmations",
                        featureIcon = R.drawable.calm,
                        buttonSize = 148.dp,
                        onClick = {
                            navigateToAffirmationsScreen()
                        }
                    )

                    IndividualButton(
                        backgroundColor = Color(0xFFCB9272),
                        featureName = "Journal",
                        featureIcon = R.drawable.journal,
                        buttonSize = 148.dp,
                        onClick = {
                            navigateToJournalScreen()
                        }
                    )


                }


            }


        }

    }
}

@Composable
fun WelcomeTopPart(name: String,
                   heightPercentage: Float,
                   fontSize: TextUnit,
                   subTitleFontSize: TextUnit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(heightPercentage)
        .background(Color.Transparent),
        verticalArrangement = Arrangement.Center) {
        Text(text = "Welcome Back, $name",
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            color = Color.White)

        Text(text = "Anxiety is just for a moment!",
            color = Color.White,
            fontSize = subTitleFontSize)
    }
}

@Composable
fun AnxietyPart(
    heightPercentage: Float,
    percentageFontSize: TextUnit,
    textFontSize: TextUnit,
    strokeWidth: Dp,
    anxietyRate: Int?
) {
    // Local variable to hold the text
    val text = remember { mutableStateOf("0") }
    val anxietyRateNumber = remember {
        mutableFloatStateOf(0f)
    }

    // Update the text with a delay when the anxiety rate changes
    LaunchedEffect(anxietyRate) {
        text.value = "Rate your anxiety"
        anxietyRateNumber.floatValue = 0f
        delay(200) // Delay for 8 milli seconds
        anxietyRateNumber.floatValue = (anxietyRate?.toFloat() ?: 0f) / 10f
        text.value = getComment(anxietyRate) ?: "Rate your anxiety"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(heightPercentage),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = anxietyRateNumber.floatValue,
                trackColor = Color(0xFFEAEAEA),
                color = Color(0xFF34A853),
                modifier = Modifier.size(95.dp),
                strokeWidth = strokeWidth
            )

            Text(
                text = "${anxietyRateNumber.floatValue * 10}/10",
                color = Color.White,
                fontSize = percentageFontSize,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = text.value, // Use the local variable here
            fontSize = textFontSize,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 24.dp)
        )
    }
}



fun getComment(anxietyRate: Int?): String {
    return when (anxietyRate) {
        in 1..3 -> "You're doing great!"
        in 4..6 -> "Remember to breathe and stay present."
        in 7..8 -> "Practice self-care and reach out if needed."
        in 9..10 -> "You're not alone. Consider seeking support."
        else -> ""
    }
}


@Composable
fun MusicPart(musicName: String,
              titleFontSize: TextUnit,
              durationFontSize: TextUnit, ){
    val context = LocalContext.current

    var media: MediaPlayer? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {
        media = MediaPlayer.create(context, R.raw.relax_ocean_sounds)

        onDispose {
            media?.release()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFFC869A),
                shape = RoundedCornerShape(10.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(vertical = 16.dp)
                .padding(start = 16.dp)
        ) {
            Text(
                text = musicName,
                fontSize = titleFontSize,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Duration: ${media?.duration?.toDuration(DurationUnit.SECONDS)}",
                color = Color.White,
                fontSize = durationFontSize
            )
        }

        var playing by remember {
            mutableStateOf(false)
        }

        IconButton(
            onClick = {
                media?.let {
                    if (it.isPlaying) {
                        it.stop()
                        it.prepare() // Reset the MediaPlayer to be ready for next play
                        playing = false
                    } else {
                        it.start()
                        playing = true
                    }
                }
            },
            colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xFF505BF4)),
            modifier = Modifier.padding(end = 16.dp)
        ) {
            if (playing){
                Icon(painter = painterResource(id = R.drawable.pause), contentDescription = null)
            }
            else{
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
            }
        }
    }

}

@Composable
fun IndividualButton(backgroundColor: Color,
                     featureName: String,
                     featureIcon: Int,
                     buttonSize: Dp,
                     onClick: () -> Unit){
    Column(modifier = Modifier
        .background(color = backgroundColor, shape = RoundedCornerShape(20.dp))
        .size(buttonSize)
        .padding(8.dp)
        .clickable {
            onClick()
        }) {

        Text(text = featureName, fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.fillMaxHeight(.7f))

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = featureIcon),
                contentDescription = null,
                modifier = Modifier.size(34.dp),
            )

            Icon(imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(.7f))
        }

    }
}

@Composable
@Preview(showBackground = true)
fun HomeCompactPreview(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF06154D)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        HomeCompact(name = "Phillip",5,{},{},{},{})

    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun HomeMediumPreview(){
    HomeMedium("Phillip",2,{},{},{},{})
}

@Composable
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
fun HomeExpandedPreview(){
    HomeExpanded("Phillip",2,{},{},{},{})
}