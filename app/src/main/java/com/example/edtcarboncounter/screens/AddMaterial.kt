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

import com.example.edtcarboncounter.NavRoutes
import com.example.edtcarboncounter.data.project
import com.example.edtcarboncounter.data.projectNamesList
import com.example.edtcarboncounter.database.ADatabaseViewModel4
import com.example.edtcarboncounter.database.ProjectEntity
import com.example.edtcarboncounter.data.materialObject
import com.example.edtcarboncounter.database.MaterialEntity
import java.util.logging.Logger.global
import com.example.edtcarboncounter.data.newMaterialObject


@Composable
fun AddMaterial(navController: NavHostController, viewModel: ADatabaseViewModel4) {


    var materialList by remember { mutableStateOf<List<String>>(emptyList()) }

    // Observe the LiveData and update projectList when data changes
    val observer = remember {
        Observer<List<String>> { newList ->
            materialList = newList
        }
    }

    // Subscribe to the LiveData in the first composition
    DisposableEffect(Unit) {
        val liveData = viewModel.allMaterialNames
        liveData.observeForever(observer)

        // Unsubscribe when the composable is disposed
        onDispose {
            liveData.removeObserver(observer)
        }
    }


    Scaffold(
        topBar = { topBar(navController = navController) },
        bottomBar = { BottomBar(navController = navController) },
    ) {
        // Content
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Add Material", fontSize = 40.sp, modifier = Modifier
                        .padding(top = 20.dp), textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    "Please enter material details", fontSize = 25.sp, modifier = Modifier
                        .padding(top = 20.dp), textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(20.dp))
                // Text field for material name entry
                var newMaterialName by remember { mutableStateOf("") }
                val onmaterialNameChange = { text: String ->
                    newMaterialName = text
                }
                OutlinedTextField(
                    value = newMaterialName,
                    onValueChange = onmaterialNameChange,
                    modifier = Modifier
                        .width(250.dp)
                        .padding(horizontal = 10.dp),
                    label = { Text("Material Name") }
                )

                Spacer(modifier = Modifier.padding(20.dp))
                // Text field for material carbon per kg entry
                var SnewCarbonPerKg by remember { mutableStateOf("") }
                val onmassNameChange = { text: String ->
                    SnewCarbonPerKg = text
                }
                OutlinedTextField(
                    value = SnewCarbonPerKg,
                    onValueChange = onmassNameChange,
                    modifier = Modifier
                        .width(250.dp)
                        .padding(horizontal = 10.dp),
                    label = { Text("kg of Carbon Per 1kg of material") }
                )

                Spacer(modifier = Modifier.padding(20.dp))
                var buttonYes by remember { mutableStateOf(false) }
                Button(
                    onClick = { buttonYes = true },
                    modifier = Modifier.padding(horizontal = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff2EC4B6))
                ) {
                    Text(text = "Add Material")
                }
                if (buttonYes) {
                    buttonYes = false

                    if (newMaterialName == "") {
                        val context = LocalContext.current
                        Toast.makeText(context, "Please Enter a Material Name", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        if (SnewCarbonPerKg == "") {
                            val context = LocalContext.current
                            Toast.makeText(
                                context,
                                "Please Enter a kg Carbon per 1kg material quantity.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (SnewCarbonPerKg.toDoubleOrNull() != null) {
//                        var DnewCarbonPerKg = 0.0
                            var DnewCarbonPerKg = SnewCarbonPerKg.toDouble()
                            viewModel.upsertMaterial(MaterialEntity(newMaterialName, DnewCarbonPerKg))
                            navController.navigate(NavRoutes.CounterMaterial.route)
                        }
                        else{
                            val context = LocalContext.current
                            Toast.makeText(context, "Please Enter a kg Carbon per 1kg material quantity.", Toast.LENGTH_SHORT).show()
                        }
                    }


//                    viewModel.upsertMaterial(MaterialEntity(newMaterialName, DnewCarbonPerKg))
//                    navController.navigate(NavRoutes.CounterMaterial.route)
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