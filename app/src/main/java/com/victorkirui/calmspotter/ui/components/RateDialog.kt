package com.victorkirui.calmspotter.ui.components

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.calmspotter.ui.MainViewModel
import java.util.Calendar

@Composable
fun RateDialogRoute(windowWidthSizeClass: WindowWidthSizeClass,
                    navigateBackToHome: () -> Unit,
                    viewModel: MainViewModel = hiltViewModel()){

    RateDialogScreen(
        windowWidthSizeClass = windowWidthSizeClass,
        navigateBackToHome = { navigateBackToHome() },
        saveRate = {
            viewModel.saveAnxiety(date = Calendar.getInstance().time,
                anxietyRate = it)
            navigateBackToHome()
        }
    )

}

@Composable
fun RateDialogScreen(windowWidthSizeClass: WindowWidthSizeClass,
                     navigateBackToHome: () -> Unit,
                     saveRate: (Int) -> Unit){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            RateDialogCompact(
                navigateBackToHome = {
                    navigateBackToHome()
                },
                saveRate = {
                    saveRate(it)
                }
            )
        }

        WindowWidthSizeClass.Medium ->{
            RateDialogMedium(
                navigateBackToHome = {
                    navigateBackToHome()
                },
                saveRate = {
                    saveRate(it)
                }
            )
        }

        WindowWidthSizeClass.Expanded ->{
            RateDialogExpanded(
                navigateBackToHome = {

                },
                saveRate = {
                    saveRate(it)
                }
            )
        }
    }
}

@Composable
fun RateDialogCompact(navigateBackToHome:() -> Unit,
                      saveRate: (Int) -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.12f),
            verticalAlignment = Alignment.CenterVertically) {

            Icon(imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navigateBackToHome() })

        }

        Text(text = "Rate your Anxiety",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth())

        val rateScale = remember {
            mutableStateListOf(1,2,3,4,5,6,7,8,9,10)
        }

        var currentNumber by remember {
            mutableIntStateOf(0)
        }


        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.8f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            LazyRow {
                items(5){
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .background(
                            color = if (currentNumber == (it + 1)) {
                                Color.White
                            } else {
                                Color.Transparent
                            }
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { currentNumber = (it + 1) }){
                        Text(text = rateScale[it].toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = if (currentNumber == (it + 1)){Color(0xFF06154D)}else{Color.White},
                            modifier = Modifier.padding(20.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyRow {
                items(5){
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            color = if (currentNumber == (it + 6)) {
                                Color.White
                            } else {
                                Color.Transparent
                            }
                        )
                        .clickable { currentNumber = (it + 6) }){
                        Text(text = rateScale[it + 5].toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = if (currentNumber == (it + 6)){Color(0xFF06154D)}else{Color.White},
                            modifier = Modifier.padding(20.dp))
                    }
                }
            }
        }

        val context = LocalContext.current

        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            Button(onClick = {
                             if (currentNumber == 0){
                                 Toast.makeText(context, "Rate your anxiety", Toast.LENGTH_SHORT).show()
                             }else{
                                 saveRate(currentNumber)
                             }
            },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953))) {
                Text(text = "Save",
                    modifier = Modifier.padding(8.dp), fontSize = 17.sp)
            }
        }

    }
}

@Composable
fun RateDialogMedium(navigateBackToHome: () -> Unit,
                     saveRate: (Int) -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.12f),
            verticalAlignment = Alignment.CenterVertically) {

            Icon(imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navigateBackToHome() })

        }

        Text(text = "Rate your Anxiety",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth())

        val rateScale = remember {
            mutableStateListOf(1,2,3,4,5,6,7,8,9,10)
        }

        var currentNumber by remember {
            mutableIntStateOf(0)
        }


        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.8f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            LazyRow {
                items(5){
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .background(
                            color = if (currentNumber == (it + 1)) {
                                Color.White
                            } else {
                                Color.Transparent
                            }
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { currentNumber = (it + 1) }){
                        Text(text = (rateScale[it]).toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 29.sp,
                            color = if (currentNumber == (it + 1)){Color(0xFF06154D)}else{Color.White},
                            modifier = Modifier.padding(20.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyRow {
                items(5){
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .background(
                            color = if (currentNumber == (it + 6)) {
                                Color.White
                            } else {
                                Color.Transparent
                            }
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { currentNumber = (it + 6) }){
                        Text(text = (rateScale[it] + 5).toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 29.sp,
                            color = if (currentNumber == (it + 6)){Color(0xFF06154D)}else{Color.White},
                            modifier = Modifier.padding(20.dp))
                    }
                }
            }
        }

        val context = LocalContext.current

        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            Button(onClick = {
                if (currentNumber == 0){
                    Toast.makeText(context, "Rate your anxiety", Toast.LENGTH_SHORT).show()
                }else{
                    saveRate(currentNumber)
                }
            },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953))) {
                Text(text = "Save",
                    modifier = Modifier.padding(8.dp), fontSize = 17.sp)
            }
        }

    }
}

@Composable
fun RateDialogExpanded(navigateBackToHome: () -> Unit,
                       saveRate:(Int) -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.12f),
            verticalAlignment = Alignment.CenterVertically) {

            Icon(imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { navigateBackToHome() })

        }



        Text(text = "Rate your Anxiety",
            color = Color.White,
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth())

        val rateScale = remember {
            mutableStateListOf(1,2,3,4,5,6,7,8,9,10)
        }

        var currentNumber by remember {
            mutableIntStateOf(0)
        }


        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.8f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            LazyRow {
                items(5){
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .background(
                            color = if (currentNumber == (it + 1)) {
                                Color.White
                            } else {
                                Color.Transparent
                            }
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { currentNumber = (it + 1) }){
                        Text(text = rateScale[it].toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp,
                            color = if (currentNumber == (it + 1)){Color.Transparent}else{Color.White},
                            modifier = Modifier.padding(20.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyRow {
                items(5){
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .background(
                            color = if (currentNumber == (it + 1)) {
                                Color.White
                            } else {
                                Color.Transparent
                            }
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { currentNumber = (it + 5) }){
                        Text(text = (rateScale[it] + 5).toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp,
                            color = if (currentNumber == (it + 1)){Color.Transparent}else{Color.White},
                            modifier = Modifier.padding(20.dp))
                    }
                }
            }
        }

        val context = LocalContext.current

        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                if (currentNumber == 0){
                    Toast.makeText(context, "Rate your anxiety", Toast.LENGTH_SHORT).show()
                }else{
                    saveRate(currentNumber)
                }
            },
                modifier = Modifier
                    .fillMaxWidth(.7f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953))) {
                Text(text = "Save",
                    modifier = Modifier.padding(10.dp), fontSize = 20.sp)
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun RateDialogCompactPreview(){
    RateDialogCompact({},{})
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun RateDialogMediumPreview(){
    RateDialogMedium({},{})
}

@Composable
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
fun RateDialogExpandedPreview(){
    RateDialogExpanded({},{})
}