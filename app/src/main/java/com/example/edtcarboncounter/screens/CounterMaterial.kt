package com.example.edtcarboncounter.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.edtcarboncounter.NavRoutes
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.PopupProperties
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import java.util.Collections.emptyList


@Composable
fun CounterMaterial(navController: NavHostController) {
    Scaffold(topBar = {topBar(navController =navController) },
        bottomBar = {BottomBar(navController=navController)},
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate(NavRoutes.CounterRecycled.route)}) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Add")
            }
        }

    ) {
        //content area
        materialCards()


    }
}

@Composable
fun materialCards() {
    Card(
        modifier = Modifier
            .height(height = 200.dp)
            .fillMaxWidth()
            .padding(all = 20.dp)
            //.background(color = Color(0xFFBB0189))
    ) {
        Column() {
            Row() {
                Text("New Material", textAlign = TextAlign.Left, fontSize = 15.sp)
            }
            Row() {
                var mExpanded by remember { mutableStateOf(false) }
                Demo_ExposedDropdownMenuBox()
            }
        }

//        Text(
//            text = "Filled",
//            modifier = Modifier
//                .padding(16.dp),
//            textAlign = TextAlign.Center,
//        )
    }
}

@Composable
fun Demo_ExposedDropdownMenuBox() {

    var mExpanded by remember { mutableStateOf(false) }
    var noMaterialFound: Int = 0
    var materialItemSelected: Int = 0
    // Create a list of cities
    val mMaterials = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it
                            mExpanded = true
                            },
            modifier = Modifier
                .width(250.dp)
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {Text("Material (Source)")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            properties = PopupProperties(focusable = false),
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            for (Material in mMaterials) {
                if (mSelectedText.uppercase() in Material.uppercase()) {
                    noMaterialFound = 1
                    DropdownMenuItem(onClick = {
                        mSelectedText = Material
                        mExpanded = false
                        materialItemSelected = 1
                    }) {
                        Text(text = Material)
                    }
                }
            }
            if(noMaterialFound == 0) {
                //Add in option to add to database
                DropdownMenuItem(onClick = {
                    //Call function to create pop up to add to database
                }) {
                    Text(text = "Add to Database")
                }
            }
//            mMaterials.forEach { label ->
//                DropdownMenuItem(onClick = {
//                    mSelectedText = label
//                    mExpanded = false
//                }) {
//                    Text(text = label)
//                }
//            }
        }
    }
}


//    @Composable
//    fun DropdownDemo() {
//        var expanded by remember { mutableStateOf(false) }
//        val items = listOf("A", "B", "C", "D", "E", "F")
//        val disabledValue = "B"
//        var selectedIndex by remember { mutableStateOf(0) }
//        Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.TopStart)) {
//            Text(items[selectedIndex],modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded = true }).background(
//                Color.Gray))
//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false },
//                modifier = Modifier.fillMaxWidth().background(
//                    Color.Red)
//            ) {
//                items.forEachIndexed { index, s ->
//                    DropdownMenuItem(onClick = {
//                        selectedIndex = index
//                        expanded = false
//                    }) {
//                        val disabledText = if (s == disabledValue) {
//                            " (Disabled)"
//                        } else {
//                            ""
//                        }
//                        Text(text = s + disabledText)
//                    }
//                }
//            }
//        }
//    }
