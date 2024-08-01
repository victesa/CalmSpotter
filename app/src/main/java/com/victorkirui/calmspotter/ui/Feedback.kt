package com.victorkirui.calmspotter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeedbackRoute(){

}

@Composable
fun FeedbackScreen(){

}

@Composable
fun FeedbackCompact(feedbackValue: String,
                    onFeedBackValueChange:(String) -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(16.dp)) {

        Row(modifier = Modifier
            .fillMaxHeight(.1f)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {

            Icon(imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.White)

        }

        Text(text = "Feedback", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)

        Column(modifier = Modifier
            .fillMaxHeight(.8f)
            .fillMaxWidth()) {

            Spacer(modifier = Modifier.fillMaxHeight(.2f))

            TextField(value = feedbackValue,
                onValueChange = {onFeedBackValueChange(it)},
                modifier = Modifier
                    .fillMaxWidth(),
                minLines = 14,
                maxLines = 14,
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White),
                placeholder = {
                    Text(text = "Message")
                }
            )
        }

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6A953))) {
                Text(text = "Submit",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp))
            }
        }

    }

}

@Composable
fun FeedbackMedium(feedbackValue: String, onFeedBackValueChange: (String) -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(horizontal = 24.dp)) {
        Row(modifier = Modifier
            .fillMaxHeight(.1f)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {

            Icon(imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp),
                tint = Color.White)
        }

        Text(text = "Feedback", fontSize = 40.sp, color = Color.White, fontWeight = FontWeight.Bold)

        Column(modifier = Modifier
            .fillMaxHeight(.8f)
            .fillMaxWidth()) {

            Spacer(modifier = Modifier.fillMaxHeight(.2f))

            TextField(value = feedbackValue,
                onValueChange = {onFeedBackValueChange(it)},
                modifier = Modifier
                    .fillMaxWidth(),
                minLines = 14,
                maxLines = 14,
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White),
                placeholder = {
                    Text(text = "Message")
                }
            )
        }

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults
                    .buttonColors(containerColor = Color(0xFFC6A953))) {
                Text(text = "Submit",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp))
            }
        }

    }

}

@Composable
fun FeedbackExpanded(feedbackValue: String, onFeedBackValueChange: (String) -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF06154D))
        .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.White)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Feedback", fontSize = 50.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }


        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.6f)) {

            Column(modifier = Modifier
                .fillMaxHeight(.8f)
                .fillMaxWidth()) {

                Spacer(modifier = Modifier.fillMaxHeight(.2f))

                TextField(value = feedbackValue,
                    onValueChange = {onFeedBackValueChange(it)},
                    modifier = Modifier
                        .fillMaxWidth(),
                    minLines = 14,
                    maxLines = 14,
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White),
                    placeholder = {
                        Text(text = "Message")
                    }
                )
            }

            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults
                        .buttonColors(containerColor = Color(0xFFC6A953))) {
                    Text(text = "Submit",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp))
                }
            }

        }
        
    }

}

@Composable
@Preview(showBackground = true)
fun FeedbackCompactPreview(){
    FeedbackCompact(feedbackValue = "", onFeedBackValueChange = {})
}

@Composable
@Preview(showBackground = true, device = "spec:width=673dp,height=841dp")
fun FeedbackMediumPreview(){
    FeedbackMedium(feedbackValue = "", onFeedBackValueChange = {})
}

@Composable
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
fun FeedbackExpandedPreview(){
    FeedbackExpanded(feedbackValue = "", onFeedBackValueChange = {})
}