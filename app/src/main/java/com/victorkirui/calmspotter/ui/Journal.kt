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
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.Calendar

@Composable
fun JournalRoute(viewModel: JournalViewModel = hiltViewModel(),
                 windowWidthSizeClass: WindowWidthSizeClass,
                 navigateBack: () -> Unit){

    JournalScreen(
        windowWidthSizeClass = windowWidthSizeClass,
        title = viewModel.journalState.collectAsState().value.title,
        onTitleChange = { viewModel.titleChange(it) },
        bodyValue = viewModel.journalState.collectAsState().value.body,
        bodyValueChange = {viewModel.bodyChange(it)},
        navigateBack = { viewModel.bodyChange("")
            viewModel.titleChange("")
            navigateBack() },
        saveJournal = {
            navigateBack()
            viewModel.insert(Calendar.getInstance().time)})

}

@Composable
fun JournalScreen(windowWidthSizeClass: WindowWidthSizeClass,
                  title: String,
                  onTitleChange:(String) -> Unit,
                  bodyValue: String,
                  bodyValueChange:(String) -> Unit,
                  navigateBack:() -> Unit,
                  saveJournal:() -> Unit){

    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            JournalCompact(
                title = title,
                onTitleChange = onTitleChange,
                bodyValue = bodyValue,
                bodyValueChange = bodyValueChange,
                navigateBack = navigateBack,
                saveJournal = saveJournal
            )
        }

        WindowWidthSizeClass.Medium ->{
            JournalMedium(
                title = title,
                onTitleChange = onTitleChange,
                bodyValue = bodyValue,
                bodyValueChange = bodyValueChange,
                navigateBack = navigateBack,
                saveJournal = saveJournal
            )
        }

        WindowWidthSizeClass.Expanded ->{
            JournalExpanded(
                title = title,
                onTitleChange = onTitleChange,
                bodyValue = bodyValue,
                onBodyValueChange = bodyValueChange,
                saveJournal = saveJournal,
                navigateBack = navigateBack
            )
        }
    }

}

@Composable
fun JournalCompact(title: String,
                   onTitleChange:(String) -> Unit,
                   bodyValue: String,
                   bodyValueChange:(String) -> Unit,
                   navigateBack:() -> Unit,
                   saveJournal:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = { navigateBack() },
                modifier = Modifier
                    .padding(10.dp)) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White)
            }

            IconButton(onClick = { saveJournal() },
                modifier = Modifier.padding(10.dp)) {
                Icon(imageVector = Icons.Default.Done,
                    contentDescription = null,
                    tint = Color.White)
            }
        }

        TextField(value = title,
            onValueChange = {onTitleChange(it)},
            placeholder = {
                Text(text = "Heading", fontSize = 32.sp)
            },
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.25f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF06154D),
                unfocusedContainerColor = Color.Transparent,
                unfocusedPlaceholderColor = Color.Gray),
            textStyle = TextStyle(fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White)

        )

        TextField(value = bodyValue,
            onValueChange = {bodyValueChange(it)},
            maxLines = 5,
            placeholder = {
                Text(text = "Body", fontSize = 20.sp)
            },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF06154D),
                unfocusedContainerColor = Color.Transparent,
                unfocusedPlaceholderColor = Color.Gray),
            textStyle = TextStyle(color = Color.White)
        )

    }

}

@Composable
fun JournalMedium(title: String,
                  onTitleChange:(String) -> Unit,
                  bodyValue: String,
                  bodyValueChange:(String) -> Unit,
                  navigateBack:() -> Unit,
                  saveJournal:() -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = { navigateBack() },
                modifier = Modifier
                    .padding(10.dp)
                    .size(40.dp)) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize())
            }

            IconButton(onClick = { saveJournal() },
                modifier = Modifier
                    .padding(10.dp)) {
                Icon(imageVector = Icons.Default.Done,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize())
            }
        }

        TextField(value = title,
            onValueChange = {onTitleChange(it)},
            placeholder = {
                Text(text = "Heading", fontSize = 50.sp)
            },
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.25f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF06154D),
                unfocusedContainerColor = Color.Transparent,
                unfocusedPlaceholderColor = Color.Gray),
            textStyle = TextStyle(fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White)

        )

        TextField(value = bodyValue,
            onValueChange = {bodyValueChange(it)},
            maxLines = 5,
            placeholder = {
                Text(text = "Body", fontSize = 26.sp)
            },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF06154D),
                unfocusedContainerColor = Color.Transparent,
                unfocusedPlaceholderColor = Color.Gray)
        )
    }

}

@Composable
fun JournalExpanded(title: String,
                    onTitleChange: (String) -> Unit,
                    bodyValue: String,
                    onBodyValueChange:(String) -> Unit,
                    saveJournal: () -> Unit,
                    navigateBack: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.2f)
            .padding(horizontal = 20.dp)
            .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = { navigateBack() },
                modifier = Modifier
                    .padding(10.dp)
                    .size(50.dp)) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize())
            }

            IconButton(onClick = { saveJournal() },
                modifier = Modifier
                    .padding(10.dp)) {
                Icon(imageVector = Icons.Default.Done,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize())
            }
        }

        TextField(value = title,
            onValueChange = {onTitleChange(it)},
            placeholder = {
                Text(text = "Heading", fontSize = 50.sp)
            },
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.25f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF06154D),
                unfocusedContainerColor = Color.Transparent,
                unfocusedPlaceholderColor = Color.Gray),
            textStyle = TextStyle(fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White)

        )

        TextField(value = bodyValue,
            onValueChange = {onBodyValueChange(it)},
            maxLines = 5,
            placeholder = {
                Text(text = "Body", fontSize = 26.sp)
            },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF06154D),
                unfocusedContainerColor = Color.Transparent,
                unfocusedPlaceholderColor = Color.Gray)
        )
    }

}

@Composable
@Preview(showBackground = true)
fun JournalCompactPreview(){
    JournalCompact(title = "", onTitleChange = {},"", {},{},{})
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun JournalMediumPreview(){
    JournalMedium(title = "", onTitleChange = {}, bodyValue = "", bodyValueChange = {},{},{})
}

@Composable
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
fun JournalExpandedPreview(){
    JournalExpanded(title = "", onTitleChange = {}, bodyValue = "", onBodyValueChange = {}, {}, {})
}