package com.victorkirui.calmspotter.ui

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.victorkirui.calmspotter.domain.JournalListDetails
import kotlinx.coroutines.flow.update
import java.util.Date

@Composable
    fun JournalListRoute(viewModel: JournalViewModel,
                         windowWidthSizeClass: WindowWidthSizeClass,
                         navigateToJournalItemScreen: (JournalListDetails) -> Unit,
                         navigateToJournalScreen: () -> Unit){
        var context = LocalContext.current

    JournalListScreen(
        windowWidthSizeClass = windowWidthSizeClass,
        navigateToJournalItemScreen = {jd->
            viewModel._journalCurrentSelected.value = jd
            navigateToJournalItemScreen(jd)
                                      viewModel._journalCurrentSelected.update {
                                          it.copy(
                                              id = it.id,
                                              body = it.body,
                                              title = it.title,
                                              date = it.date,
                                              isUpdated = it.isUpdated
                                          )
                                      }},
        allNotesList = viewModel.fetchJournalList().collectAsState(initial = emptyList()).value,
        navigateToJournalScreen = navigateToJournalScreen,
        deleteJournal = {
            viewModel.delete(it)
        }
    )


}

@Composable
fun JournalListScreen(windowWidthSizeClass: WindowWidthSizeClass,
                      navigateToJournalItemScreen: (JournalListDetails) -> Unit,
                      allNotesList: List<JournalListDetails>,
                      navigateToJournalScreen: () -> Unit,
                      deleteJournal: (JournalListDetails) -> Unit){
    when(windowWidthSizeClass){
        WindowWidthSizeClass.Compact ->{
            JournalListCompact(allNotesList = allNotesList,
                navigateToJournalItemScreen = navigateToJournalItemScreen,
                navigateToJournalScreen = navigateToJournalScreen,
                deleteJournal = {
                    deleteJournal(it)
                })
        }

        WindowWidthSizeClass.Medium ->{
            JournalListMedium(allNotesList = allNotesList,
                navigateToJournalItemScreen = navigateToJournalItemScreen,
                navigateToJournalScreen = navigateToJournalScreen,
                deleteJournal = {
                    deleteJournal(it)
                })
        }

        WindowWidthSizeClass.Expanded ->{
            navigateToJournalItemScreen(allNotesList[0])
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JournalListCompact(allNotesList: List<JournalListDetails>,
                       navigateToJournalItemScreen:(JournalListDetails) -> Unit,
                       navigateToJournalScreen: () -> Unit,
                       deleteJournal: (JournalListDetails) -> Unit){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToJournalScreen() },
                containerColor = Color(0xFFC6A953)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        var showDeleteDialog by remember {
            mutableStateOf(false)
        }

        var journalToDelete by remember {
            mutableStateOf(JournalListDetails())
        }

        if (showDeleteDialog){
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()) {

                DeleteDialog(
                    dismissDialog = { showDeleteDialog = false },
                    deleteJournal = {
                        deleteJournal(journalToDelete)
                    })

                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF06154D))
                    .padding(16.dp)
                    .padding(it)) {

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.2f),
                        verticalArrangement = Arrangement.Center) {
                        Text(text = "All Journal", fontSize = 34.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text(text = "${allNotesList.size} notes", fontSize = 16.sp, color = Color.DarkGray)
                    }

                    LazyColumn{
                        items(allNotesList.size){
                            ListItem(headlineContent = { Text(text = allNotesList[it].title) },
                                supportingContent = { Text(text = allNotesList[it].date.toString())},
                                colors = ListItemDefaults.colors(Color.Gray),
                                modifier = Modifier
                                    .combinedClickable(onClick = {
                                        navigateToJournalItemScreen(allNotesList[it])
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
                .fillMaxSize()
                .background(Color(0xFF06154D))
                .padding(16.dp)
                .padding(it)) {

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.2f),
                    verticalArrangement = Arrangement.Center) {
                    Text(text = "All Journal", fontSize = 34.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "${allNotesList.size} notes", fontSize = 16.sp, color = Color.DarkGray)
                }

                LazyColumn{
                    items(allNotesList.size){
                        ListItem(headlineContent = { Text(text = allNotesList[it].title) },
                            supportingContent = { Text(text = allNotesList[it].date.toString())},
                            colors = ListItemDefaults.colors(Color.Gray),
                            modifier = Modifier
                                .combinedClickable(onClick = {
                                    navigateToJournalItemScreen(allNotesList[it])
                                },
                                    onLongClick = {
                                        journalToDelete = allNotesList[it]
                                        showDeleteDialog = true

                                    }))
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JournalListMedium(allNotesList: List<JournalListDetails>,
                      navigateToJournalItemScreen:(JournalListDetails) -> Unit,
                      navigateToJournalScreen: () -> Unit,
                      deleteJournal: (JournalListDetails) -> Unit){
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var journalToDelete by remember {
        mutableStateOf(JournalListDetails())
    }
    Scaffold(floatingActionButton = {
        LargeFloatingActionButton(onClick = { navigateToJournalScreen() }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) {
        if (showDeleteDialog){
           Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

               DeleteDialog(dismissDialog = {showDeleteDialog = false},
                   deleteJournal = {
                       deleteJournal(journalToDelete)
                   })
               Column(modifier = Modifier
                   .fillMaxSize()
                   .background(Color(0xFF06154D))
                   .padding(24.dp)
                   .padding(it)) {

                   Column(modifier = Modifier
                       .fillMaxWidth()
                       .fillMaxHeight(.2f),
                       verticalArrangement = Arrangement.Center) {
                       Text(text = "All Journal", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                       Text(text = "${allNotesList.size} notes", fontSize = 21.sp, color = Color.DarkGray)
                   }

                   LazyColumn{
                       items(allNotesList.size){
                           ListItem(headlineContent = {
                               Text(text = allNotesList[it].title) },
                               supportingContent = {
                                   Text(text = allNotesList[it].date.toString())},
                               colors = ListItemDefaults.colors(containerColor = Color.Gray),
                               modifier = Modifier
                                   .combinedClickable(onClick = {
                                       navigateToJournalItemScreen(allNotesList[it])
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
                .fillMaxSize()
                .background(Color(0xFF06154D))
                .padding(24.dp)
                .padding(it)) {

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.2f),
                    verticalArrangement = Arrangement.Center) {
                    Text(text = "All Journal", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "${allNotesList.size} notes", fontSize = 21.sp, color = Color.DarkGray)
                }

                var context = LocalContext.current

                LazyColumn{
                    items(allNotesList.size){
                        ListItem(headlineContent = {
                            Text(text = allNotesList[it].title) },
                            supportingContent = {
                                Text(text = allNotesList[it].date.toString())},
                            colors = ListItemDefaults.colors(containerColor = Color.Gray),
                            modifier = Modifier
                                .combinedClickable(onClick = {
                                navigateToJournalItemScreen(allNotesList[it])
                            },
                                onLongClick = {
                                    journalToDelete = allNotesList[it]
                                    showDeleteDialog = true
                                    Toast.makeText(context, journalToDelete.id.toString(), Toast.LENGTH_SHORT).show()
                                }))

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteDialog(dismissDialog:() -> Unit,
                 deleteJournal:() -> Unit){
    AlertDialog(onDismissRequest = { dismissDialog() },
        confirmButton = {
            TextButton(onClick = {
                deleteJournal()
            dismissDialog()}) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = { dismissDialog() }) {
                Text(text = "No")
            }
        },
        title = {
            Text(text = "Delete")
        },
        text = {
            Text(text = "Do you want to delete this Journal?")
        },
        icon = {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null,
                tint = Color.Red)
        })
}

@Composable
@Preview(showBackground = true)
fun JournalListCompactPreview(){
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

    JournalListCompact(allNotesList = list, {}, {},{})
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun JournalListMediumPreview(){
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

    JournalListMedium(allNotesList = list, {},{},{})
}

@Composable
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
fun JournalListExpandedPreview(){

}