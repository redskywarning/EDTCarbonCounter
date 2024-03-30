package com.example.edtcarboncounter.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.edtcarboncounter.data.materialObject
import com.example.edtcarboncounter.data.project
import com.example.edtcarboncounter.data.transportObject
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import java.util.Collections.emptyList

//List of Materials/Transport for iteration and selection -> Will be replaced by using data from database

val mMaterials = listOf("Steel", "Aluminium", "Concrete", "Iron" )
val mTransport = listOf("Car", "Lorry", "Boat", "Plane ", "Train")

//Main Function
@Composable
fun CounterMaterial(navController: NavHostController) {
    // Sets up layout of Page
    Scaffold(topBar = {topBar(navController =navController) },
        bottomBar = {BottomBar(navController=navController)},
        floatingActionButton = {
            //Button takes to next page -> calls validation to check all fields are filled in correctly
            var nextPage by remember {mutableStateOf(false)}
            FloatingActionButton(onClick = {nextPage = true}) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Forwards")
            }
            if(nextPage) {
                Validation(navController = navController)
                nextPage = false
            }
        }

    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())){
            Text(
                "Materials", fontSize = 40.sp, modifier = Modifier.padding(top = 20.dp).align(Alignment.CenterHorizontally)
            )
            // Allows more materials to be added
            var materialNum by remember {mutableStateOf(0)}
            var materialAddYes by remember { mutableStateOf(0)}
            Button(onClick = {materialAddYes += 1; materialNum += 1}, modifier = Modifier.padding(horizontal = 10.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff2EC4B6))) {
                Text(text = "Add Material")
            }
            // Creates starter material card on screen + allows it to be deleted
            Surface() {
                var showCard by remember {mutableStateOf( true)}
                if(showCard) {
                    materialCards(onDeleteClicked = { showCard = false }, materialNum = 0)
                }
                else {
                    Card () {

                    }
                    project.materials[0].deleted = 1

                }
            }
            // Adds more material cards for every time the button is clicked until they are deleted/user moves on
            for (material in 1..materialAddYes) {
                var showCard1 by remember {mutableStateOf( true)}
                if(showCard1) {
                    materialCards(onDeleteClicked = { showCard1 = false }, materialNum = material)
                }
                else {
                    Card () {
                    }
                    project.materials[material].deleted = 1
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))
        }
    }
}

// Function for Material Content
@Composable
fun materialCards(onDeleteClicked: () -> Unit, materialNum: Int)                                                                                                                                                                                                                                                                                                              {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 20.dp)
            .border(border = BorderStroke(1.dp, Color.Black)),

    ) {
        Column {
            Row() {
                IconButton(onClick = onDeleteClicked) {
                    Icon(Icons.Default.Delete, contentDescription = "Localized description")
                }
                Text("Material:", textAlign = TextAlign.Left, fontSize = 15.sp, modifier = Modifier.padding(top = 15.dp, start = 10.dp, end = 10.dp))

            }
            project.materials += materialObject(material = "",Smkg = "", Lmkg = 0, transports = mutableListOf<transportObject>(),recyclable = 0, deleted = 0)
            //Main Material Content - Dropdown menu
            Row() {
                //MaterialDropdownMenuBox()
                var mExpanded by remember { mutableStateOf(false) }
                var noMaterialFound: Int = 0
                var materialItemSelected: Int = 0
                var mSelectedText by remember { mutableStateOf("") }
                var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
                project.materials[materialNum].material = mSelectedText
                // Up Icon when expanded and down icon when collapsed
                val icon = if (mExpanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown

                Column(Modifier.padding(horizontal = 10.dp)) {

                    // Create an Outlined Text Field
                    // with icon and not expanded
                    OutlinedTextField(
                        value = mSelectedText,
                        onValueChange = { mSelectedText = it
                            mExpanded = true
                            project.materials[materialNum].material = mSelectedText
                        },
                        modifier = Modifier
                            .width(250.dp)
                            .onGloballyPositioned { coordinates ->
                                mTextFieldSize = coordinates.size.toSize()
                            },
                        label = {Text("Material (Source)")},
                        trailingIcon = {
                            Icon(icon,"contentDescription",
                                Modifier.clickable { mExpanded = !mExpanded })
                        }
                    )

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
                                    project.materials[materialNum].material = mSelectedText
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
                    }
                }
                // Weight content of material
                var cmaterialWeight by remember { mutableStateOf("")}
                val onMaterialWeightChange = { text: String->
                    cmaterialWeight = text
                    project.materials[materialNum].Smkg = cmaterialWeight
                }
                project.materials[materialNum].Smkg = cmaterialWeight

                OutlinedTextField(
                    value = cmaterialWeight,
                    onValueChange = onMaterialWeightChange,
                    modifier = Modifier
                        .width(250.dp)
                        .padding(horizontal = 10.dp),
                    label = {Text("Material Mass / kg")}
                )
                //project.materials += materialObject(material = mSelectedText,Smkg = cmaterialWeight, Lmkg = 0, transports = mutableListOf<transportObject>(),recyclable = 0, deleted = 0)
            }
            //Transport Content
            var transportAddYes by remember { mutableStateOf(-1)}
            Button(onClick = {transportAddYes += 1}, modifier = Modifier.padding(horizontal = 10.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff2EC4B6))) {
                Text(text = "Add transport")
            }

            for (transport in 0..transportAddYes) {
                var showCardt by remember {mutableStateOf( true)}
                if(showCardt) {
                    TransportAdd(onDeleteClicked = { showCardt = false }, materialNum = materialNum, transportNum = transport)
                }
                else {
                    Card () {

                    }
                    project.materials[materialNum].transports[transport].deleted = 1
                    //transportAddYes -= 1
                }
            }
            Spacer(modifier = Modifier.padding(2.5.dp))
        }
    }
}

@Composable
fun TransportAdd(onDeleteClicked: () -> Unit, materialNum: Int, transportNum: Int) {
    // Transport Cotent
    Card () {

        Spacer(modifier = Modifier.padding(2.5.dp))
        Row() {
            var mExpanded by remember { mutableStateOf(false) }
            var noTransportFound: Int = 0
            var transportSelected: Int = 0
            project.materials[materialNum].transports += transportObject(type = "", Sdistance = "", Ldistance = 0, deleted = 0)

            var tSelectedText by remember { mutableStateOf("") }
            var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
            project.materials[materialNum].transports[transportNum].type = tSelectedText
            // Up Icon when expanded and down icon when collapsed
            val icon = if (mExpanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            Column(Modifier.padding(horizontal = 10.dp)) {

                // Create an Outlined Text Field
                // with icon and not expanded
                OutlinedTextField(
                    value = tSelectedText,
                    onValueChange = { tSelectedText = it
                        mExpanded = true
                        project.materials[materialNum].transports[transportNum].type = tSelectedText
                    },
                    modifier = Modifier
                        .width(250.dp)
                        .onGloballyPositioned { coordinates ->
                            // This value is used to assign to
                            // the DropDown the same width
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    label = {Text("Transport")},
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { mExpanded = !mExpanded })
                    }
                )

                // Create a drop-down menu with list of mats,
                // when clicked, set the Text Field text as the mat selected
                DropdownMenu(
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                    properties = PopupProperties(focusable = false),
                    modifier = Modifier
                        .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
                ) {
                    for (Transport in mTransport) {
                        if (tSelectedText.uppercase() in Transport.uppercase()) {
                            noTransportFound = 1
                            DropdownMenuItem(onClick = {
                                tSelectedText = Transport
                                project.materials[materialNum].transports[transportNum].type = tSelectedText
                                mExpanded = false
                                transportSelected = 1
                            }) {
                                Text(text = Transport)
                            }
                        }
                    }
                    if(noTransportFound == 0) {
                        //Add in option to add to database
                        DropdownMenuItem(onClick = {
                            //Call function to create pop up to add to database
                        }) {
                            Text(text = "Add to Transport Database")
                        }
                    }
                }
            }
            // Distance content
            var transportDistance by remember { mutableStateOf("")}
            project.materials[materialNum].transports[transportNum].Sdistance = transportDistance

            val onMaterialWeightChange = { text: String->
                transportDistance = text
                project.materials[materialNum].transports[transportNum].Sdistance = transportDistance
            }
            OutlinedTextField(
                value = transportDistance,
                onValueChange = onMaterialWeightChange,
                modifier = Modifier
                    .width(250.dp)
                    .padding(horizontal = 10.dp),
                label = {Text("Distance / km")}
            )
            IconButton(onClick = onDeleteClicked) {
                Icon(Icons.Default.Delete, contentDescription = "Localized description")
            }
            //project.materials[materialNum].transports += transportObject(type = tSelectedText, Sdistance = transportDistance, Ldistance = 0, deleted = 0)
        }
        Spacer(modifier = Modifier.padding(2.5.dp))
    }
}
// Validation before page moves on to ensure content entered is all correct
@Composable
fun Validation(navController: NavHostController) {
    var allValid = true
    for (material in project.materials) {
        if (material.deleted ==1) {
            project.materials.remove(material)
            return
        }
        if (material.material == "") {
            allValid = false
            val context = LocalContext.current
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
        if (material.Smkg == "") {
            allValid = false
            val context = LocalContext.current
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
        //var context = LocalContext.current
        //Toast.makeText(context, ":" +material.material, Toast.LENGTH_SHORT).show()
//        var matInList = material.material in mMaterials
//        var matNum = 1
//        if (matInList) {
//            matNum = 0
//        }
//        context = LocalContext.current
//        Toast.makeText(context, matNum, Toast.LENGTH_SHORT).show()

        //Text("a" + material.material, textAlign = TextAlign.Left, fontSize = 15.sp, modifier = Modifier.padding(top = 15.dp, start = 10.dp, end = 10.dp))
        //Text(material.Smkg, textAlign = TextAlign.Left, fontSize = 15.sp, modifier = Modifier.padding(top = 15.dp, start = 10.dp, end = 10.dp))
        var matIn = 0
        for ( i in mMaterials) {
            if (material.material == i) {
                matIn = 1
            }
        }
        if(matIn == 0){
            allValid = false
            val context = LocalContext.current
            Toast.makeText(context, "Please select a material in the database", Toast.LENGTH_SHORT).show()
            //return
        }
        if(material.Smkg.toLongOrNull() == null) {
            allValid = false
            val context = LocalContext.current
            Toast.makeText(context, "Material Weights must be numbers", Toast.LENGTH_SHORT).show()
            //return
        }
        if(material.Smkg.toLongOrNull() != null) {
            material.Lmkg = material.Smkg.toLong()
        }
        for (transport in material.transports) {
            if (transport.deleted ==1) {
                material.transports.remove(transport)
                return
            }
            if (transport.type == "") {
                allValid = false
                val context = LocalContext.current
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
            if (transport.Sdistance == "") {
                allValid = false
                val context = LocalContext.current
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
            var tranIn = 0
            for(i in mTransport) {
                if(transport.type == i) {
                    tranIn = 1
                }
            }
            if(tranIn == 0){
                allValid = false
                val context = LocalContext.current
                Toast.makeText(context, "Please select a transport type in the database", Toast.LENGTH_SHORT).show()
                //return
            }
            if(transport.Sdistance.toLongOrNull() == null) {
                allValid = false
                val context = LocalContext.current
                Toast.makeText(context, "Distances must be numbers", Toast.LENGTH_SHORT).show()
                //return
            }
            if(transport.Sdistance.toLongOrNull() != null) {
                transport.Ldistance = transport.Sdistance.toLong()
            }
        }
    }
    if (allValid) {
        navController.navigate(NavRoutes.CounterRecycled.route)
    }
    else {
        return
    }
    //return
}

//
//@Composable
//fun Validation(navController: NavHostController) {
//    var allValid = true
//    for (material in project.materials) {
//        var valid = true
////        if (material.deleted ==1) {
////            project.materials.remove(material)
////            return
////            //continue
////        }
//        //empty checks
//        if (material.material == "") {
//            valid = false
//            val context = LocalContext.current
//            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
//
//        }
//        if (material.Smkg == "") {
//            valid = false
//            val context = LocalContext.current
//            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
//        }
//        //var context = LocalContext.current
//        //Toast.makeText(context, ":" +material.material, Toast.LENGTH_SHORT).show()
////        var matInList = material.material in mMaterials
////        var matNum = 1
////        if (matInList) {
////            matNum = 0
////        }
////        context = LocalContext.current
////        Toast.makeText(context, matNum, Toast.LENGTH_SHORT).show()
//
//        //Text("a" + material.material, textAlign = TextAlign.Left, fontSize = 15.sp, modifier = Modifier.padding(top = 15.dp, start = 10.dp, end = 10.dp))
//        //Text(material.Smkg, textAlign = TextAlign.Left, fontSize = 15.sp, modifier = Modifier.padding(top = 15.dp, start = 10.dp, end = 10.dp))
//        //check valid mat
//        var matIn = 0
//        for ( i in mMaterials) {
//            if (material.material == i) {
//                matIn = 1
//            }
//        }
//        if(matIn == 0){
//            valid = false
//            val context = LocalContext.current
//            Toast.makeText(context, "Please select a material in the database", Toast.LENGTH_SHORT).show()
//            //return
//        }
//        //check long num weight
//        if(material.Smkg.toLongOrNull() == null) {
//            valid = false
//            val context = LocalContext.current
//            Toast.makeText(context, "Material Weights must be numbers", Toast.LENGTH_SHORT).show()
//            //return
//        }
//        if(material.Smkg.toLongOrNull() != null) {
//            material.Lmkg = material.Smkg.toLong()
//        }
//        //check transports
//        for (transport in material.transports) {
//            var validT = true
////            if (transport.deleted ==1) {
////                //material.transports.remove(transport)
////                continue
////            }
//            if (transport.type == "") {
//                validT = false
//                val context = LocalContext.current
//                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
//            }
//            if (transport.Sdistance == "") {
//                validT = false
//                val context = LocalContext.current
//                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
//            }
//            var tranIn = 0
//            for(i in mTransport) {
//                if(transport.type == i) {
//                    tranIn = 1
//                }
//            }
//            if(tranIn == 0){
//                validT = false
//                val context = LocalContext.current
//                Toast.makeText(context, "Please select a transport type in the database", Toast.LENGTH_SHORT).show()
//                //return
//            }
//            if(transport.Sdistance.toLongOrNull() == null) {
//                validT = false
//                val context = LocalContext.current
//                Toast.makeText(context, "Distances must be numbers", Toast.LENGTH_SHORT).show()
//                //return
//            }
//            if(transport.Sdistance.toLongOrNull() != null) {
//                transport.Ldistance = transport.Sdistance.toLong()
//            }
//
//            if (transport.deleted ==1) {
//                //material.transports.remove(transport)
//                validT = true
//            }
//            if (!validT) {
//                allValid = false
//            }
//        }
////        for(transport in material.transports) {
////            if(transport.deleted == 1){
////                material.transports.remove(transport)
////            }
////        }
//        if (material.deleted ==1) {
//            valid = true
//        }
//        if(!valid) {
//            allValid = false
//        }
//    }
//    for (material in project.materials) {
//        if (material.deleted == 1) {
//            project.materials.remove(material)
//        }
//        for (transport in material.transports) {
//            if (transport.deleted ==1) {
//                material.transports.remove(transport)
//            }
//        }
//    }
//    if (allValid) {
//        navController.navigate(NavRoutes.CounterRecycled.route)
//    }
//    else {
//        //return
//    }
//    //return
//}
