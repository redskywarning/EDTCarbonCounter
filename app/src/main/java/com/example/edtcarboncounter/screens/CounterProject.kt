package com.example.edtcarboncounter.screens

import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import com.example.edtcarboncounter.NavRoutes
import com.example.edtcarboncounter.data.project
import com.example.edtcarboncounter.data.projectNamesList
import java.util.logging.Logger.global

@Composable
fun CounterProject(navController: NavHostController) {
    Scaffold(topBar = {topBar(navController =navController) },
        bottomBar = {BottomBar(navController=navController)},
//        floatingActionButton = {
//            FloatingActionButton(onClick = Validate(navController = navController, projectName = projectName)) {
//                Icon(Icons.Default.ArrowForward, contentDescription = "Add")
//            }
//        }
    ) {
        //content area
        //IM AWARE THERE IS A PROBLEM WITH FORMATTING AND CENTERING - IM NOT SURE HOW TO FIX
        //SUGGESTIONS WELCOME
        Column(){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Project Name", fontSize = 40.sp, modifier = Modifier
                        .padding(top = 20.dp), textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Please enter your project name", fontSize = 25.sp, modifier = Modifier
                        .padding(top = 20.dp), textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.padding(20.dp))
            var projectName by remember { mutableStateOf("") }
            val onprojectNameChange = { text: String->
                projectName = text
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = projectName,
                    onValueChange = onprojectNameChange,
                    modifier = Modifier
                        .width(250.dp)
                        .padding(horizontal = 10.dp),
                    label = {Text("Project Name")}
                )
            }

            Spacer(modifier = Modifier.padding(20.dp))
            var buttonYes by remember{mutableStateOf(false)}
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {buttonYes = true}, modifier = Modifier.padding(horizontal = 10.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff2EC4B6))) {
                    Text(text = "Choose Name")
                }
            }
            if (buttonYes){
                buttonYes = false
                Validate(navController = navController, projectName = projectName)
            }
        }


    }
}

@Composable
fun Validate(navController: NavHostController, projectName: String) {
    if (projectName == "") {
        val context = LocalContext.current
        Toast.makeText(context, "Please Enter a Project Name", Toast.LENGTH_SHORT).show()
    }
    //To Check no repeats of project name
    else if (projectName in projectNamesList) {
        val context = LocalContext.current
        Toast.makeText(context, "Project Name already Exists", Toast.LENGTH_SHORT).show()
    }
    else {
        project.projectName = projectName
        projectNamesList += projectName
        //HELP
        //KATIE THIS IS WHERE THE PROJECT NAME INPUT IS - ENJOY
        navController.navigate(NavRoutes.CounterMaterial.route)
    }
}