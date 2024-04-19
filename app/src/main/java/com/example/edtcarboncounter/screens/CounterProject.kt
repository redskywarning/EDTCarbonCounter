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
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.example.edhellotcarboncounter.screens.BottomBar
import com.example.edhellotcarboncounter.screens.topBar
import com.example.edhellotcarboncounter.screens.BottomBar
import com.example.edhellotcarboncounter.screens.topBar

import com.example.edtcarboncounter.NavRoutes
import com.example.edtcarboncounter.data.project
import com.example.edtcarboncounter.data.projectNamesList
import com.example.edtcarboncounter.database.AAAllDao
import com.example.edtcarboncounter.database.AADatabase
import com.example.edtcarboncounter.database.ADatabaseViewModel4
import com.example.edtcarboncounter.database.ProjectEntity
import java.util.logging.Logger.global
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@Composable
fun CounterProject(navController: NavHostController, viewModel: ADatabaseViewModel4) {

//    var projectList = AAAllDao.getAllProjectNames()
    //val projectDao = viewModel.AAAllDao() // Assuming you have a projectDao instance in your viewModel

//    var projectList by remember { mutableStateOf<List<String>>(emptyList()) }
//
//    LaunchedEffect(key1 = Unit) {
//        val dao = AADatabase.getDatabase(context, scope).allDao() // Get the DAO instance using allDao() method
//        projectList = dao.getAllProjectNames()
//    }
    //var projectList = viewModel.allProjectNames

    var projectList by remember { mutableStateOf<List<String>>(emptyList()) }

    // Observe the LiveData and update projectList when data changes
    val observer = remember {
        Observer<List<String>> { newList ->
            projectList = newList
        }
    }

    // Subscribe to the LiveData in the first composition
    DisposableEffect(Unit) {
        val liveData = viewModel.allProjectNames
        liveData.observeForever(observer)

        // Unsubscribe when the composable is disposed
        onDispose {
            liveData.removeObserver(observer)
        }
    }

    Scaffold(topBar = {topBar(navController =navController) },
        bottomBar = {BottomBar(navController=navController)},
    ) {
        // Content
        Box(modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
                Text(
                    "Project Name", fontSize = 40.sp, modifier = Modifier
                        .padding(top = 20.dp), textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    "Please enter your project name", fontSize = 25.sp, modifier = Modifier
                        .padding(top = 20.dp), textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(20.dp))
                // Text field for name entry
                var projectName by remember { mutableStateOf("") }
                val onprojectNameChange = { text: String->
                    projectName = text
                }
                OutlinedTextField(
                    value = projectName,
                    onValueChange = onprojectNameChange,
                    modifier = Modifier
                        .width(250.dp)
                        .padding(horizontal = 10.dp),
                    label = {Text("Project Name")}
                )
                Spacer(modifier = Modifier.padding(20.dp))
                var buttonYes by remember{mutableStateOf(false)}
                Button(onClick = {buttonYes = true}, modifier = Modifier.padding(horizontal = 10.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff2EC4B6))) {
                    Text(text = "Choose Name")
                }
                if (buttonYes){
                    buttonYes = false

                    if (projectName == "") {
                        val context = LocalContext.current
                        Toast.makeText(context, "Please Enter a Project Name", Toast.LENGTH_SHORT).show()
                    }
                    //To Check no repeats of project name
                    else if (projectName in projectList) {
//                    else if (projectList.contains(projectName)) {
                        val context = LocalContext.current
                        Toast.makeText(context, "Project Name already Exists", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        project.projectName = projectName
                        projectNamesList += projectName
                        viewModel.upsertProject(ProjectEntity(projectName,0.0))
                        navController.navigate(NavRoutes.CounterMaterial.route)
                    }

//                    else {
//                        viewModel.getAllProjectNames() { projectList ->
//                            if (projectName in projectList) {
//                                val context = LocalContext.current
//                                Toast.makeText(context, "Project Name already Exists", Toast.LENGTH_SHORT).show()
//                            }
//                            else {
//                                viewModel.upsertProject(ProjectEntity(projectName,0.0))
//                                navController.navigate(NavRoutes.CounterMaterial.route)
//                            }
//                        }
//
//                    }
//                    viewModel.upsertProject(..);
//                    Validate(navController = navController, projectName = projectName)
                }
            }
        }
    }
}

// Ensures project name is entered and is unique to prevent errors
//@Composable
//fun Validate(navController: NavHostController, projectName: String) {
//    if (projectName == "") {
//        val context = LocalContext.current
//        Toast.makeText(context, "Please Enter a Project Name", Toast.LENGTH_SHORT).show()
//    }
//    //To Check no repeats of project name
//    else if (projectName in projectNamesList) {
//        val context = LocalContext.current
//        Toast.makeText(context, "Project Name already Exists", Toast.LENGTH_SHORT).show()
//    }
//    else {
//        project.projectName = projectName
//        projectNamesList += projectName
//        navController.navigate(NavRoutes.CounterMaterial.route)
//    }
//}