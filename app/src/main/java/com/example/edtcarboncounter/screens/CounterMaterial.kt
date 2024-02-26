package com.example.edtcarboncounter.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.edtcarboncounter.NavRoutes
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
//import com.example.carbon_counter.screens.BottomBar
//import com.example.carbon_counter.screens.topBar
//import org.jetbrains.annotations.ApiStatus
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



    }
}

@Composable
fun materialCards() {
    Card(
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
    ) {
        Row() {
            Text("New Material", textAlign = TextAlign.Left, fontSize = 15.sp)
        }
        Row() {
            var mExpanded by remember { mutableStateOf(false) }
//            DropdownMenu() {
//
//            }
        }
//        Text(
//            text = "Filled",
//            modifier = Modifier
//                .padding(16.dp),
//            textAlign = TextAlign.Center,
//        )
    }
}

////@Composable
////fun Demo_ExposedDropdownMenuBox() {
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
////    var mExpanded by remember { mutableStateOf(false) }
////
////    // Create a list of cities
////    val mCities = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")
////
////    // Create a string value to store the selected city
////    var mSelectedText by remember { mutableStateOf("") }
////
////    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
////
////    // Up Icon when expanded and down icon when collapsed
////    val icon = if (mExpanded)
////        Icons.Filled.KeyboardArrowUp
////    else
////        Icons.Filled.KeyboardArrowDown
////
////    Column(Modifier.padding(20.dp)) {
////
////        // Create an Outlined Text Field
////        // with icon and not expanded
////        OutlinedTextField(
////            value = mSelectedText,
////            onValueChange = { mSelectedText = it },
////            modifier = Modifier
////                .fillMaxWidth()
////                .onGloballyPositioned { coordinates ->
////                    // This value is used to assign to
////                    // the DropDown the same width
////                    mTextFieldSize = coordinates.size.toSize()
////                },
////            label = {Text("Label")},
////            trailingIcon = {
////                Icon(icon,"contentDescription",
////                    Modifier.clickable { mExpanded = !mExpanded })
////            }
////        )
////
////        // Create a drop-down menu with list of cities,
////        // when clicked, set the Text Field text as the city selected
////        DropdownMenu(
////            expanded = mExpanded,
////            onDismissRequest = { mExpanded = false },
////            modifier = Modifier
////                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
////        ) {
////            mCities.forEach { label ->
////                DropdownMenuItem(onClick = {
////                    mSelectedText = label
////                    mExpanded = false
////                }) {
////                    Text(text = label)
////                }
////            }
////        }
////    }
////}
//
//
//public fun <T> listOf(vararg elements: T): List<T> = if (elements.size > 0) elements.asList() else emptyList()
//
//public expect fun <T> Array<out T>.asList(): List<T>
