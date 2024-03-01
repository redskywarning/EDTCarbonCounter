package com.example.edtcarboncounter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.edtcarboncounter.NavRoutes


@Composable
fun CounterReceipt(navController: NavHostController) {
    Scaffold(topBar = {topBar(navController =navController) },
        bottomBar = {BottomBar(navController=navController)}
    ) {
        //content area
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Carbon Receipt",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(30.dp),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.size(15.dp))
            Box (modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                    //HELP - Katie this is where to call function to download the database etc
                    //You probably also want it to automatically take you back to the home page + I might need it to reset something for}
                    //me in terms of validation so yay!!
                .clickable(onClick = {/*TODO*/ })
                .background(Color.LightGray)
                .height(100.dp),
                contentAlignment = Alignment.Center){
                Text("Save and Download", textAlign = TextAlign.Center, fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.size(40.dp))

        }

    }
}