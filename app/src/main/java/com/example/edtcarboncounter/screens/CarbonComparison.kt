package com.example.edtcarboncounter.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.example.edtcarboncounter.data.project





@Composable
fun CarbonComparison(navController: NavHostController) {
    var selectedMaterial1 by remember { mutableStateOf("") }
    var selectedMaterial2 by remember { mutableStateOf("") }

    Scaffold(
        topBar = { topBar(navController = navController) },
        bottomBar = { BottomBar(navController = navController) },
        floatingActionButton = {
            var nextPage by remember { mutableStateOf(false) }
            FloatingActionButton(onClick = { nextPage = true }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Add")
            }
            if (nextPage) {
                Validation(navController = navController)
            }
        }
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(
                "Materials", fontSize = 40.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            var comparisonNum by remember { mutableStateOf(0) }
            var comparisonAddYes by remember { mutableStateOf(0) }
            Button(
                onClick = { comparisonAddYes += 1; comparisonNum += 1 },
                modifier = Modifier.padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff2EC4B6))
            ) {
                Text(text = "Add Comparison")
            }
//            Surface() {
//                for (comparison in 1..comparisonAddYes) {
//                    comparisonCard(onDeleteClicked = {}, comparisonNum = comparison)
//                }
//            }
//            Spacer(modifier = Modifier.padding(20.dp))
            Surface() {
                var showCard by remember {mutableStateOf( true)}
                if(showCard) {
                    comparisonCard(
                        onDeleteClicked = { showCard = false },
                        comparisonNum = 0,
                        selectedMaterial1 = selectedMaterial1,
                        selectedMaterial2 = selectedMaterial2,
                        // Pass the callback functions to comparisonCard
                        onMaterial1Selected = { material -> selectedMaterial1 = material },
                        onMaterial2Selected = { material -> selectedMaterial2 = material }
                    )
                }
                else {
                    Card () {

                    }
                    project.materials[0].deleted = 1

                }
            }
            for (comparison in 1..comparisonAddYes) {
                var showCard1 by remember {mutableStateOf( true)}
                if(showCard1) {
                    comparisonCard(onDeleteClicked = { showCard1 = false }, comparisonNum = comparison, selectedMaterial1 = selectedMaterial1,
                        selectedMaterial2 = selectedMaterial2,
                        // Pass the callback functions to comparisonCard
                        onMaterial1Selected = { material -> selectedMaterial1 = material },
                        onMaterial2Selected = { material -> selectedMaterial2 = material })


                }
                else {
                    Card () {
                    }
                    project.materials[comparison].deleted = 1
                }


            }

        }
    }
}
@Composable
fun MaterialDropdownMenu(label: String,onMaterial1Selected: (String) -> Unit, // Callback function for Material 1
                         onMaterial2Selected: (String) -> Unit) {
    var mExpanded by remember { mutableStateOf(false) }
    var noMaterialFound: Int = 0
    var materialItemSelected: Int = 0
    val mMaterials = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")
    var mSelectedText by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(horizontal = 10.dp)) {
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .width(250.dp)
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text(label) },
            trailingIcon = {
                Icon(
                    icon, "contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded }
                )
            }
        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            properties = PopupProperties(focusable = false),
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            for (material in mMaterials) {
                if (mSelectedText.uppercase() in material.uppercase()) {
                    noMaterialFound = 1
                    DropdownMenuItem(onClick = {

                        mSelectedText = material
                        mExpanded = false
                        materialItemSelected = 1
                        if (label == "Material 1") {
                            onMaterial1Selected(material)
                        } else {
                            onMaterial2Selected(material)
                        }

                    }) {
                        Text(text = material)
                    }
                }
            }
            if(noMaterialFound == 0) {
                //Add in option to add to database
                DropdownMenuItem(onClick = {
                    /*TODO*/
                    //HELP KATIE
                    //Call function to create pop up to add to database
                }) {
                    Text(text = "Add to Database")
                }
            }
        }
    }
}


@Composable
fun comparisonCard(
    onDeleteClicked: () -> Unit,
    comparisonNum: Int,
    selectedMaterial1: String,
    selectedMaterial2: String,
    onMaterial1Selected: (String) -> Unit, // Callback function for Material 1
    onMaterial2Selected: (String) -> Unit
) {
    val values = listOf(30, 45, 50, 76, 80, 20, 40)

    // State variable to store percentage difference
    var percentageDifference by remember { mutableStateOf<Double?>(null) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 20.dp)
            .border(border = BorderStroke(1.dp, Color.Black))
    ) {
        Column {
            Row {
                // Dropdown menus for selecting materials
                Column {
                    IconButton(onClick = onDeleteClicked) {
                        Icon(Icons.Default.Delete, contentDescription = "Localized description")
                    }
                    Text(
                        "Comparison $comparisonNum:",
                        textAlign = TextAlign.Left,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 15.dp, start = 10.dp, end = 10.dp)
                    )
                    MaterialDropdownMenu("Material 1",onMaterial1Selected,onMaterial2Selected)
                }

                Column {
                    IconButton(onClick = onDeleteClicked) {
                        Icon(Icons.Default.Delete, contentDescription = "Localized description")
                    }
                    Text(
                        "Comparison $comparisonNum:",
                        textAlign = TextAlign.Left,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 15.dp, start = 10.dp, end = 10.dp)
                    )
                    MaterialDropdownMenu("Material 2",onMaterial1Selected, onMaterial2Selected)
                }
            }

            // Button to calculate percentage difference
            Button(

                onClick = {
                    // Calculate index of selectedMaterial1 and selectedMaterial2 in the values list
                    val index1 = mMaterials.indexOf(selectedMaterial1)
                    val index2 = mMaterials.indexOf(selectedMaterial2)
                    Log.d("SelectedMaterials", "Material1: $selectedMaterial1, Material2: $selectedMaterial2")
// Check if both materials are found in the mMaterials list
                    if (index1 != -1 || index2 != -1) {
                        // Retrieve the corresponding numbers from the values list
                        val value1 = values[index1].toDouble()
                        val value2 = values[index2].toDouble()

                        // Calculate percentage difference
                        percentageDifference = (value2 - value1) / value1 * 100

                    }


                },


                modifier = Modifier.padding(top = 10.dp)
            )

            {
                Text(text = "Calculate")
            }
            Row{
                Text(
                    "Percentage Difference: %.2f%%".format(percentageDifference ),
                    textAlign = TextAlign.Left,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 15.dp, start = 10.dp, end = 10.dp)
                )
            }

            // Display percentage difference if calculated



        }
    }
}






