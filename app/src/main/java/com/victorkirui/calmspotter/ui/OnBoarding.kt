package com.victorkirui.calmspotter.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.calmspotter.R

@Composable
fun OnBoardingRoute(windowWidthSizeClass: WindowWidthSizeClass,
                    navigateToHome: () -> Unit){

    OnBoardingScreen(windowWidthSizeClass = windowWidthSizeClass,
        navigateToHome = navigateToHome)

}

@Composable
fun OnBoardingScreen(windowWidthSizeClass: WindowWidthSizeClass,
                     navigateToHome:() -> Unit){
    var currentScreen by remember {
        mutableStateOf("Journal")
    }

    AnimatedContent(targetState = currentScreen, label = "") {
        when(it){
            "Journal" ->{
                when(windowWidthSizeClass){
                    WindowWidthSizeClass.Compact ->{
                        OnBoardingJournalCompact {
                            currentScreen = "RelaxingMusic"
                        }
                    }

                    WindowWidthSizeClass.Medium ->{
                        OnBoardingJournalMedium {
                            currentScreen = "RelaxingMusic"
                        }
                    }

                    WindowWidthSizeClass.Expanded ->{
                        OnBoardingJournalExpanded {
                            currentScreen = "RelaxingMusic"
                        }
                    }
                }

            }

            "RelaxingMusic" ->{
                when(windowWidthSizeClass){
                    WindowWidthSizeClass.Compact ->{
                        OnBoardingRelaxingMusicCompact {
                            currentScreen = "PanicButton"
                        }
                    }

                    WindowWidthSizeClass.Medium ->{
                        OnBoardingRelaxingMusicMedium {
                            currentScreen = "PanicButton"
                        }
                    }

                    WindowWidthSizeClass.Expanded ->{
                        OnBoardingRelaxingMusicExpanded {
                            currentScreen = "PanicButton"
                        }
                    }
                }

            }

            "PanicButton" ->{
                when(windowWidthSizeClass){
                    WindowWidthSizeClass.Compact ->{
                        OnBoardingPanicButtonCompact {
                            navigateToHome()
                        }
                    }

                    WindowWidthSizeClass.Medium ->{
                        OnBoardingPanicButtonMedium {
                            navigateToHome()
                        }
                    }

                    WindowWidthSizeClass.Expanded ->{
                        OnBoardingPanicButtonExpanded {
                            navigateToHome()
                        }
                    }

                }

            }
        }
    }

}

@Composable
fun OnBoardingJournalCompact(onClick:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {

            Image(painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.size(64.dp))

            Text(text = "CalmSpotter",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.56f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(id = R.drawable.journal_illustration), contentDescription = null)

        }

        Row(modifier = Modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight(.05f),
            horizontalArrangement = Arrangement.SpaceBetween) {
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
            LinearProgressIndicator(progress = 0f, modifier = Modifier.width(70.dp))
            LinearProgressIndicator(progress = 0f, modifier = Modifier.width(70.dp))
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.6f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Journal", fontSize = 26.sp, color = Color.White, fontWeight = FontWeight.Bold)

            Text(text = "Use our journaling feature to express your thoughts and feelings in a private and secure environment.",
                color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
            Button(onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953)),
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Next", color = Color.White,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 17.sp)
            }
        }

    }
}

@Composable
fun OnBoardingJournalMedium(onClick: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {

            Image(painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.size(64.dp))

            Text(text = "CalmSpotter",
                color = Color.White,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.56f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(id = R.drawable.journal_illustration), contentDescription = null)

        }

        Row(modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight(.05f),
            horizontalArrangement = Arrangement.SpaceBetween) {
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
            LinearProgressIndicator(progress = 0f, modifier = Modifier.width(70.dp))
            LinearProgressIndicator(progress = 0f, modifier = Modifier.width(70.dp))
        }

        Column(modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight(.6f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Journal", fontSize = 29.sp, color = Color.White, fontWeight = FontWeight.Bold)

            Text(text = "Use our journaling feature to express your thoughts and feelings in a private and secure environment.",
                color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Button(onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953)),
                modifier = Modifier.fillMaxWidth(.8f)) {
                Text(text = "Next", color = Color.White,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 17.sp)
            }
        }

    }

}

@Composable
fun OnBoardingJournalExpanded(onClick: () -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Row(modifier = Modifier
            .fillMaxWidth(.5f)
            .fillMaxHeight()
            .background(Color(0xFF06154D)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {

            Image(painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.size(70.dp))

            Text(text = "CalmSpotter", fontSize = 25.sp, color = Color.White)

        }

        Divider(modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color(0xFF06154D))
            .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {

                Image(painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp))

                Text(text = "CalmSpotter",
                    color = Color.White,
                    fontSize = 29.sp,
                    fontWeight = FontWeight.Bold)

            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.56f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Image(painter = painterResource(id = R.drawable.journal_illustration), contentDescription = null)

            }

            Row(modifier = Modifier
                .fillMaxWidth(.7f)
                .fillMaxHeight(.05f),
                horizontalArrangement = Arrangement.SpaceBetween) {
                LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
                LinearProgressIndicator(progress = 0f, modifier = Modifier.width(70.dp))
                LinearProgressIndicator(progress = 0f, modifier = Modifier.width(70.dp))
            }

            Column(modifier = Modifier
                .fillMaxWidth(.7f)
                .fillMaxHeight(.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = "Journal", fontSize = 29.sp, color = Color.White, fontWeight = FontWeight.Bold)

                Text(text = "Use our journaling feature to express your thoughts and feelings in a private and secure environment.",
                    color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center)

            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Button(onClick = { onClick() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953)),
                    modifier = Modifier.fillMaxWidth(.7f)) {
                    Text(text = "Next", color = Color.White,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 17.sp)
                }
            }

        }

    }
}

@Composable
fun OnBoardingRelaxingMusicCompact(onClick: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {

            Image(painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.size(64.dp))

            Text(text = "CalmSpotter",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.56f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(id = R.drawable.music), contentDescription = null)

        }

        Row(modifier = Modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight(.05f),
            horizontalArrangement = Arrangement.SpaceBetween) {
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
            LinearProgressIndicator(progress = 0f, modifier = Modifier.width(70.dp))
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.6f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Relaxing Music", fontSize = 26.sp, color = Color.White, fontWeight = FontWeight.Bold)

            Text(text = "Indulge in our curated selection of tranquil melodies designed to calm your mind and soothe your soul.",
                color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
            Button(onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953)),
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Next", color = Color.White,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 17.sp)
            }
        }

    }
}

@Composable
fun OnBoardingRelaxingMusicMedium(onClick: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {

            Image(painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.size(64.dp))

            Text(text = "CalmSpotter",
                color = Color.White,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.56f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(id = R.drawable.journal_illustration), contentDescription = null)

        }

        Row(modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight(.05f),
            horizontalArrangement = Arrangement.SpaceBetween) {
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
            LinearProgressIndicator(progress = 0f, modifier = Modifier.width(70.dp))
        }

        Column(modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight(.6f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Relaxing Music", fontSize = 29.sp, color = Color.White, fontWeight = FontWeight.Bold)

            Text(text = "Indulge in our curated selection of tranquil melodies designed to calm your mind and soothe your soul.",
                color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Button(onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953)),
                modifier = Modifier.fillMaxWidth(.8f)) {
                Text(text = "Next", color = Color.White,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 17.sp)
            }
        }

    }
}

@Composable
fun OnBoardingRelaxingMusicExpanded(onClick: () -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Row(modifier = Modifier
            .fillMaxWidth(.5f)
            .fillMaxHeight()
            .background(Color(0xFF06154D)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {

            Image(painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.size(70.dp))

            Text(text = "CalmSpotter", fontSize = 25.sp, color = Color.White)

        }

        Divider(modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color(0xFF06154D))
            .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {

                Image(painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp))

                Text(text = "CalmSpotter",
                    color = Color.White,
                    fontSize = 29.sp,
                    fontWeight = FontWeight.Bold)

            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.56f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Image(painter = painterResource(id = R.drawable.journal_illustration), contentDescription = null)

            }

            Row(modifier = Modifier
                .fillMaxWidth(.7f)
                .fillMaxHeight(.05f),
                horizontalArrangement = Arrangement.SpaceBetween) {
                LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
                LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
                LinearProgressIndicator(progress = 0f, modifier = Modifier.width(70.dp))
            }

            Column(modifier = Modifier
                .fillMaxWidth(.7f)
                .fillMaxHeight(.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = "Relaxing Music", fontSize = 29.sp, color = Color.White, fontWeight = FontWeight.Bold)

                Text(text = "Indulge in our curated selection of tranquil melodies designed to calm your mind and soothe your soul.",
                    color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center)

            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Button(onClick = { onClick() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953)),
                    modifier = Modifier.fillMaxWidth(.7f)) {
                    Text(text = "Next", color = Color.White,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 17.sp)
                }
            }

        }

    }
}

@Composable
fun OnBoardingPanicButtonCompact(onClick: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {

            Image(painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.size(64.dp))

            Text(text = "CalmSpotter",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.56f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(id = R.drawable.button), contentDescription = null)

        }

        Row(modifier = Modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight(.05f),
            horizontalArrangement = Arrangement.SpaceBetween) {
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.6f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Panic Button", fontSize = 26.sp, color = Color.White, fontWeight = FontWeight.Bold)

            Text(text = "With each gentle click, experience a wave of relaxation as soothing sensations wash over you.",
                color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
            Button(onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953)),
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Finish", color = Color.White,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 17.sp)
            }
        }

    }
}

@Composable
fun OnBoardingPanicButtonMedium(onClick: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {

            Image(painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.size(64.dp))

            Text(text = "CalmSpotter",
                color = Color.White,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.56f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(id = R.drawable.button), contentDescription = null)

        }

        Row(modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight(.05f),
            horizontalArrangement = Arrangement.SpaceBetween) {
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
            LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
        }

        Column(modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight(.6f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Panic Button", fontSize = 29.sp, color = Color.White, fontWeight = FontWeight.Bold)

            Text(text = "With each gentle click, experience a wave of relaxation as soothing sensations wash over you. ",
                color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Center)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Button(onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953)),
                modifier = Modifier.fillMaxWidth(.8f)) {
                Text(text = "Finish", color = Color.White,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 17.sp)
            }
        }

    }
}

@Composable
fun OnBoardingPanicButtonExpanded(onClick: () -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Row(modifier = Modifier
            .fillMaxWidth(.5f)
            .fillMaxHeight()
            .background(Color(0xFF06154D)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {

            Image(painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.size(70.dp))

            Text(text = "CalmSpotter", fontSize = 25.sp, color = Color.White)

        }

        Divider(modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color(0xFF06154D))
            .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {

                Image(painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp))

                Text(text = "CalmSpotter",
                    color = Color.White,
                    fontSize = 29.sp,
                    fontWeight = FontWeight.Bold)

            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.56f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Image(painter = painterResource(id = R.drawable.button), contentDescription = null)

            }

            Row(modifier = Modifier
                .fillMaxWidth(.7f)
                .fillMaxHeight(.05f),
                horizontalArrangement = Arrangement.SpaceBetween) {
                LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
                LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
                LinearProgressIndicator(progress = 1f, modifier = Modifier.width(70.dp), color = Color.Green)
            }

            Column(modifier = Modifier
                .fillMaxWidth(.7f)
                .fillMaxHeight(.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = "Panic Button", fontSize = 29.sp, color = Color.White, fontWeight = FontWeight.Bold)

                Text(text = "With each gentle click, experience a wave of relaxation as soothing sensations wash over you. ",
                    color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center)

            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Button(onClick = { onClick() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953)),
                    modifier = Modifier.fillMaxWidth(.7f)) {
                    Text(text = "Finish", color = Color.White,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 17.sp)
                }
            }

        }

    }
}

@Composable
@Preview(showBackground = true)
fun OnBoardingCompactPreview(){
    OnBoardingRelaxingMusicCompact {

    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun OnBoardingMediumPreview(){
    OnBoardingRelaxingMusicMedium {

    }

}

@Composable
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
fun OnBoardingExpandedPreview(){
    OnBoardingRelaxingMusicExpanded {

    }
}