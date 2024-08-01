package com.victorkirui.calmspotter.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.calmspotter.domain.JournalListDetails
import kotlinx.coroutines.flow.update
import java.util.Calendar
import java.util.Date

@Composable
fun SpecificJournalRoute(viewModel: JournalViewModel,
                         windowWidthSizeClass: WindowWidthSizeClass,
                         navigateBack: () -> Unit){

    SpecificJournalScreen(
        windowWidthSizeClass = windowWidthSizeClass,
        title = viewModel.journalCurrentSelected.collectAsState().value.title,
        onTitleChange = { string->
            viewModel._journalCurrentSelected.update { it.copy(title = string, isUpdated = true) }},
        bodyValue = viewModel._journalCurrentSelected.collectAsState().value.body,
        bodyValueChange = {string ->
                          viewModel._journalCurrentSelected.update { it.copy(body = string, isUpdated = true) }
        },
        navigateBack = { navigateBack() },
        saveJournal = { viewModel.update(Calendar.getInstance().time)
                      navigateBack()},
        isChanged = viewModel.journalCurrentSelected.collectAsState().value.isUpdated,
        allNotesList = viewModel.allJournal.collectAsState().value,
        clickedItem = { jdl->
            viewModel._journalCurrentSelected.update { it
                .copy(id = jdl.id,
                    title = jdl.title,
                    body = jdl.body,
                    date = jdl.date,
                    isUpdated = jdl.isUpdated) } },
        deleteJournal = {
            viewModel.delete(it)
        }
    )

}

@Composable
fun SpecificJournalScreen(windowWidthSizeClass: WindowWidthSizeClass,
                          title: String,
                          onTitleChange:(String) -> Unit,
                          bodyValue: String,
                          bodyValueChange:(String) -> Unit,
                          navigateBack:() -> Unit,
                          saveJournal:() -> Unit,
                          isChanged: Boolean,
                          allNotesList: List<JournalListDetails>,
                          clickedItem: (JournalListDetails) -> Unit,
                          deleteJournal: (JournalListDetails) -> Unit){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            SpecificJournalCompact(
                title = title,
                onTitleChange = onTitleChange,
                bodyValue = bodyValue ,
                bodyValueChange = bodyValueChange,
                navigateBack = { navigateBack() },
                saveJournal = { saveJournal() },
                isChanged = isChanged
            )
        }

        WindowWidthSizeClass.Medium ->{
            SpecificJournalMedium(
                title = title,
                onTitleChange = onTitleChange,
                bodyValue = bodyValue ,
                bodyValueChange = bodyValueChange,
                navigateBack = { navigateBack() },
                saveJournal = { saveJournal() },
                isChanged = isChanged
            )
        }

        WindowWidthSizeClass.Expanded ->{
            SpecificJournalExpanded(
                title = title,
                onTitleChange = onTitleChange,
                bodyValue = bodyValue ,
                bodyValueChange = bodyValueChange,
                navigateBack = { navigateBack() },
                saveJournal = { saveJournal() },
                isChanged = isChanged,
                allNotesList = allNotesList,
                clickedItem = clickedItem,
                deleteJournal = {
                    deleteJournal(it)
                }
            )
        }
    }


}

@Composable
fun SpecificJournalCompact(title: String,
                           onTitleChange:(String) -> Unit,
                           bodyValue: String,
                           bodyValueChange:(String) -> Unit,
                           navigateBack:() -> Unit,
                           saveJournal:() -> Unit,
                           isChanged: Boolean){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))) {

        if (isChanged){
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
        }else{
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.1f),
                horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = { navigateBack() },
                    modifier = Modifier
                        .padding(10.dp)) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White)
                }
            }
        }

        TextField(value = title,
            onValueChange = {onTitleChange(it)},
            minLines = 5,
            maxLines = 5,
            placeholder = {
                Text(text = "Heading", fontSize = 50.sp)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF06154D),
                unfocusedContainerColor = Color.Transparent,
                unfocusedPlaceholderColor = Color.Gray)
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
                unfocusedPlaceholderColor = Color.Gray)
        )

    }
}

@Composable
fun SpecificJournalMedium(title: String,
                          onTitleChange:(String) -> Unit,
                          bodyValue: String,
                          bodyValueChange:(String) -> Unit,
                          navigateBack:() -> Unit,
                          saveJournal:() -> Unit,
                          isChanged: Boolean){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))) {

        if (isChanged){
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
        }else{
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.1f),
                horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = { navigateBack() },
                    modifier = Modifier
                        .padding(10.dp)) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White)
                }
            }
        }

        TextField(value = title,
            onValueChange = {onTitleChange(it)},
            minLines = 5,
            maxLines = 5,
            placeholder = {
                Text(text = "Heading", fontSize = 50.sp)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF06154D),
                unfocusedContainerColor = Color.Transparent,
                unfocusedPlaceholderColor = Color.Gray)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpecificJournalExpanded(title: String,
                            onTitleChange:(String) -> Unit,
                            bodyValue: String,
                            bodyValueChange:(String) -> Unit,
                            navigateBack:() -> Unit,
                            saveJournal:() -> Unit,
                            isChanged: Boolean,
                            allNotesList: List<JournalListDetails>,
                            clickedItem:(JournalListDetails) -> Unit,
                            deleteJournal:(JournalListDetails) -> Unit){

    Row(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        var showDeleteDialog by remember {
            mutableStateOf(false)
        }

        var journalToDelete by remember {
            mutableStateOf(JournalListDetails())
        }

        if (showDeleteDialog){
            Box {

                DeleteDialog(dismissDialog = { showDeleteDialog = false },
                    deleteJournal = {
                        deleteJournal(journalToDelete)
                    })

                Column(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.45f)
                    .background(Color(0xFF06154D))
                    .padding(24.dp)) {

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.2f),
                        verticalArrangement = Arrangement.Center) {
                        Text(text = "All Journal", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text(text = "${allNotesList.size} notes", fontSize = 21.sp, color = Color.DarkGray)
                    }

                    LazyColumn(){
                        items(allNotesList.size){
                            ListItem(headlineContent = {
                                Text(text = allNotesList[it].title) },
                                supportingContent = {
                                    Text(text = allNotesList[it].date.toString())},
                                colors = ListItemDefaults.colors(containerColor = Color.Gray),
                                modifier = Modifier.combinedClickable(onClick = {
                                    clickedItem(allNotesList[it])
                                },
                                    onLongClick = {
                                        showDeleteDialog = true
                                        journalToDelete = allNotesList[it]
                                    }))

                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }else{
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(.45f)
                .background(Color(0xFF06154D))
                .padding(24.dp)) {

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.2f),
                    verticalArrangement = Arrangement.Center) {
                    Text(text = "All Journal", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "${allNotesList.size} notes", fontSize = 21.sp, color = Color.DarkGray)
                }

                LazyColumn(){
                    items(allNotesList.size){
                        ListItem(headlineContent = {
                            Text(text = allNotesList[it].title) },
                            supportingContent = {
                                Text(text = allNotesList[it].date.toString())},
                            colors = ListItemDefaults.colors(containerColor = Color.Gray),
                            modifier = Modifier
                                .combinedClickable(onClick = {
                                    clickedItem(allNotesList[it])
                            },
                                onLongClick = {
                                    showDeleteDialog = true
                                    journalToDelete = allNotesList[it]
                                }))

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth(.91f)
            .background(Color(0xFF06154D))) {

            if (isChanged){
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
            }else{
                Spacer(modifier = Modifier.fillMaxHeight(.1f))
            }

            TextField(value = title,
                onValueChange = {onTitleChange(it)},
                minLines = 5,
                maxLines = 5,
                placeholder = {
                    Text(text = "Heading", fontSize = 50.sp)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF06154D),
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.Gray)
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
}

@Composable
@Preview(showBackground = true)
fun SpecificJournalCompactPreview(){
    SpecificJournalCompact(
        title = "",
        onTitleChange = {},
        bodyValue = "",
        bodyValueChange = {},
        navigateBack = { /*TODO*/ },
        saveJournal = { /*TODO*/ },
        isChanged = false
    )
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun SpecificJournalMediumPreview(){
    SpecificJournalMedium(
        title = "",
        onTitleChange = {},
        bodyValue = "",
        bodyValueChange = {},
        navigateBack = { /*TODO*/ },
        saveJournal = { /*TODO*/ },
        isChanged = false
    )
}

@Composable
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
fun SpecificJournalExpandedPreview(){
    val list = listOf(
        JournalListDetails(
            title = "I MESSED UP",
            body = "Sfvmkfdvmkfmkvmkdsvmdskfbmgksg bk'dbd'bd'",
            date = Date(),
            id = 0
        ),
        JournalListDetails(
            title = "I MESSED UP",
            body = "Sfvmkfdvmkfmkvmkdsvmdskfbmgksg bk'dbd'bd'",
            date = Date(),
            id = 0
        ),
        JournalListDetails(
            title = "I MESSED UP",
            body = "Sfvmkfdvmkfmkvmkdsvmdskfbmgksg bk'dbd'bd'",
            date = Date(),
            id = 0
        )
        ,JournalListDetails(
            title = "I MESSED UP",
            body = "Sfvmkfdvmkfmkvmkdsvmdskfbmgksg bk'dbd'bd'",
            date = Date(),
            id = 0
        ),
        JournalListDetails(
            title = "I MESSED UP",
            body = "Sfvmkfdvmkfmkvmkdsvmdskfbmgksg bk'dbd'bd'",
            date = Date(),
            id = 0
        ),
        JournalListDetails(
            title = "I MESSED UP",
            body = "Sfvmkfdvmkfmkvmkdsvmdskfbmgksg bk'dbd'bd'",
            date = Date(),
            id = 0
        ),
        JournalListDetails(
            title = "I MESSED UP",
            body = "Sfvmkfdvmkfmkvmkdsvmdskfbmgksg bk'dbd'bd'",
            date = Date(),
            id = 0
        ),
        JournalListDetails(
            title = "I MESSED UP",
            body = "Sfvmkfdvmkfmkvmkdsvmdskfbmgksg bk'dbd'bd'",
            date = Date(),
            id = 0
        ),
        JournalListDetails(
            title = "I MESSED UP",
            body = "Sfvmkfdvmkfmkvmkdsvmdskfbmgksg bk'dbd'bd'",
            date = Date(),
            id = 0
        )
    )
    SpecificJournalExpanded(
        title = "",
        onTitleChange = {},
        bodyValue = "",
        bodyValueChange = {},
        navigateBack = { /*TODO*/ },
        saveJournal = { /*TODO*/ },
        isChanged = false,
        list,{},{}
    )
}