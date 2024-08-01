package com.victorkirui.calmspotter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AffirmationsRoute(viewModel: AffirmationsViewModel = hiltViewModel(),
                      windowWidthSizeClass: WindowWidthSizeClass,
                      navigateBackToHome: () -> Unit){

    var currentList by remember {
        mutableStateOf(viewModel.quotesAndAuthors.random())
    }

    val currentQuote = currentList.first

    val currentAuthor = currentList.second

    val listOfColors = listOf(Color(0xFF06154D),
        Color(0xFF3EA587),
        Color(0xFFC6A953),
        Color(0xFFCB9272),
        Color(0xFFAEB4FE))

    var currentBackGroundColor by remember {
        mutableStateOf(listOfColors.random())
    }

    AffirmationsScreen(
        windowWidthSizeClass = windowWidthSizeClass,
        quote = currentQuote,
        author = currentAuthor,
        backgroundColor = currentBackGroundColor,
        navigateBackToHome = { navigateBackToHome() },
        getNextQuote = { currentList = viewModel.quotesAndAuthors.random()
        currentBackGroundColor = listOfColors.random()})

}

@Composable
fun AffirmationsScreen(windowWidthSizeClass: WindowWidthSizeClass,
                       quote: String,
                       author: String,
                       backgroundColor: Color,
                       navigateBackToHome: () -> Unit,
                       getNextQuote: () -> Unit){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            AffirmationsCompact(quote = quote,
                author = author,
                backgroundColor = backgroundColor,
                navigateBackToHome = {navigateBackToHome()},
                getNextQuote = {getNextQuote()})
        }

        WindowWidthSizeClass.Medium ->{
            AffirmationsMedium(quote = quote,
                author = author,
                backgroundColor = backgroundColor,
                navigateBackToHome = {navigateBackToHome()},
                getNextQuote = {getNextQuote()})
        }

        WindowWidthSizeClass.Expanded ->{
            AffirmationsExpanded(quote = quote,
                author = author,
                backgroundColor = backgroundColor,
                navigateBackToHome = {navigateBackToHome()},
                getNextQuote = {getNextQuote()})
        }
    }

}

@Composable
fun AffirmationsCompact(quote: String,
                        author: String,
                        backgroundColor: Color,
                        navigateBackToHome:()-> Unit,
                        getNextQuote:()-> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(backgroundColor)
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f)
            .padding(top = 10.dp)) {
            IconButton(onClick = { navigateBackToHome() }) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp))
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = quote, color = Color.White,
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)

            Text(text = author, color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 30.dp))
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(),
            verticalArrangement = Arrangement.Center) {

            Button(onClick = { getNextQuote() }, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                Text(text = "Next", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

        }

    }

}

@Composable
fun AffirmationsMedium(quote: String,
                       author: String,
                       backgroundColor: Color,
                       navigateBackToHome: () -> Unit,
                       getNextQuote: () -> Unit){

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(backgroundColor)
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f)
            .padding(top = 20.dp)) {
            IconButton(onClick = { navigateBackToHome() }) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp))
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = quote, color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)

            Text(text = author, color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 30.dp))

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(),
            verticalArrangement = Arrangement.Center) {

            Button(onClick = { getNextQuote() }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                Text(text = "Next", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp))
            }

        }

    }


}

@Composable
fun AffirmationsExpanded(quote: String,
                         author: String,
                         backgroundColor: Color,
                         navigateBackToHome: () -> Unit,
                         getNextQuote: () -> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(backgroundColor)
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f)
            .padding(top = 30.dp, start = 20.dp)) {
            IconButton(onClick = { navigateBackToHome() }) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(50.dp))
            }
        }

        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.6f)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                Text(text = quote, color = Color.White,
                    fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)

                Text(text = author, color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 30.dp))
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(),
                verticalArrangement = Arrangement.Center) {

                Button(onClick = { getNextQuote() }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                    Text(text = "Next", color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp))
                }

            }
        }

    }

}

@Composable
@Preview(showBackground = true)
fun AffirmationsCompactPreview(){
    AffirmationsCompact(quote = "\"Life is 10% what happens to us and 90% how we react to it.\" ",
        author = "~Charles R. Swindoll",
        backgroundColor = Color(0xFF06154D),{},{}
    )
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun AffirmationsMediumPreview(){
    AffirmationsMedium(
        quote = "\"Life is 10% what happens to us and 90% how we react to it.\"",
        author = "~Charles R. Swindoll",
        backgroundColor = Color(0xFF06154D),{},{}
    )

}

@Composable
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
fun AffirmationsExpandedPreview(){
    AffirmationsExpanded(
        quote = "\"Life is 10% what happens to us and 90% how we react to it.\"",
        author = "~Charles R. Swindoll",
        backgroundColor = Color(0xFF06154D),{},{}
    )

}