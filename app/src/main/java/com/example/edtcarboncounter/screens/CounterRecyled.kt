package com.example.edtcarboncounter.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.example.edhellotcarboncounter.screens.BottomBar
import com.example.edhellotcarboncounter.screens.topBar

import com.example.edtcarboncounter.NavRoutes
import com.example.edtcarboncounter.data.materialObject
import com.example.edtcarboncounter.data.project
import com.example.edtcarboncounter.data.transportObject
import com.example.edtcarboncounter.database.ADatabaseViewModel4

//List of Materials for iteration and selection -> Will be replaced by using data from database

val rMaterials = listOf("Steel", "Aluminium", "Concrete", "Iron" )
var rDelList = mutableListOf<Int>()

@Composable
fun CounterRecycled(navController: NavHostController, viewModel: ADatabaseViewModel4) {

    var materialList by remember { mutableStateOf<List<String>>(kotlin.collections.emptyList()) }
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

    var transportList by remember { mutableStateOf<List<String>>(kotlin.collections.emptyList()) }

    // Observe the LiveData and update projectList when data changes
    val observer2 = remember {
        Observer<List<String>> { newList ->
            transportList = newList
        }
    }

    // Subscribe to the LiveData in the first composition
    DisposableEffect(Unit) {
        val liveData = viewModel.allTransportNames
        liveData.observeForever(observer2)

        // Unsubscribe when the composable is disposed
        onDispose {
            liveData.removeObserver(observer2)
        }
    }


    //Sets up Layout of Page
    Scaffold(topBar = {topBar(navController =navController) },
        bottomBar = {BottomBar(navController=navController)},
        floatingActionButton = {
            //Takes to next page + calls validation
            var nextPage by remember {mutableStateOf(false)}
            FloatingActionButton(onClick = {nextPage = true}) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Add")
            }
            if(nextPage) {
                rValidation(navController = navController, materialList = materialList, transportList = transportList)
                nextPage = false
            }
        }
    ) {
        //content area
        Column(modifier = Modifier.verticalScroll(rememberScrollState())){
            Text(
                "Recycled Materials", fontSize = 40.sp, modifier = Modifier.padding(top = 20.dp).align(
                    Alignment.CenterHorizontally)
            )
            //Add new recyclable material
            var materialNum by remember { mutableStateOf(0) }
            var materialAddYes by remember { mutableStateOf(0) }
            Button(onClick = {materialAddYes += 1; materialNum += 1}, modifier = Modifier.padding(horizontal = 10.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff2EC4B6))) {
                Text(text = "Add Recycled Material")
            }
            // Sets up with one material on page already --> also allows card to be deleted
            Surface() {
                var showCard by remember { mutableStateOf( true) }
                if(showCard) {
                    rDelList.add(0,0)
                    materialCards(onDeleteClicked = { showCard = false }, materialNum = 0, navController = navController, materialList = materialList, transportList = transportList)
                }
                else {
                    Card () {

                    }
                    rDelList[0] = 1
                    project.recyclableMats[0].deleted = 1

                }
            }
            // for each time button pressed, adds material card
            for (material in 1..materialAddYes) {
                var showCard1 by remember { mutableStateOf( true) }
                if(showCard1) {
                    rDelList.add(material, 0)
                    rmaterialCards(onDeleteClicked = { showCard1 = false }, materialNum = material)
                }
                else {
                    Card () {
                    }
                    rDelList[0] = 1
                    project.recyclableMats[material].deleted = 1
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))
        }


    }
}

// Card function
@Composable
fun rmaterialCards(onDeleteClicked: () -> Unit, materialNum: Int)                                                                                                                                                                                                                                                                                                              {

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
                Text("Recycled Material:", textAlign = TextAlign.Left, fontSize = 15.sp, modifier = Modifier.padding(top = 15.dp, start = 10.dp, end = 10.dp))

            }
            //Sets up material object to be edited
            project.recyclableMats += materialObject(material = "",Smkg = "", Lmkg = 0.0, transports = mutableListOf<transportObject>(),recyclable = 1, deleted = 0, transportsDelList = mutableListOf<Int>())
            //Main Material Content
            Row() {
                //MaterialDropdownMenuBox()
                var mExpanded by remember { mutableStateOf(false) }
                var noMaterialFound: Int = 0
                var materialItemSelected: Int = 0
                var mSelectedText by remember { mutableStateOf("") }
                var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
                project.recyclableMats[materialNum].material = mSelectedText
                // Up Icon when expanded and down icon when collapsed
                val icon = if (mExpanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown

                Column(Modifier.padding(horizontal = 10.dp)) {

                    // Create an Outlined Text Field with icon and not expanded
                    OutlinedTextField(
                        value = mSelectedText,
                        onValueChange = { mSelectedText = it
                            mExpanded = true
                            project.recyclableMats[materialNum].material = mSelectedText
                        },
                        modifier = Modifier
                            .width(250.dp)
                            .onGloballyPositioned { coordinates ->
                                // This value is used to assign the DropDown the same width
                                mTextFieldSize = coordinates.size.toSize()
                            },
                        label = {Text("Material (Source)")},
                        trailingIcon = {
                            Icon(icon,"contentDescription",
                                Modifier.clickable { mExpanded = !mExpanded })
                        }
                    )

                    // Dropdown menu created
                    DropdownMenu(
                        expanded = mExpanded,
                        onDismissRequest = { mExpanded = false },
                        properties = PopupProperties(focusable = false),
                        modifier = Modifier
                            .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
                    ) {
                        for (Material in rMaterials) {
                            if (mSelectedText.uppercase() in Material.uppercase()) {
                                noMaterialFound = 1
                                DropdownMenuItem(onClick = {
                                    mSelectedText = Material
                                    project.recyclableMats[materialNum].material = mSelectedText
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
                // Adds data to material data
                var cmaterialWeight by remember { mutableStateOf("")}
                val onMaterialWeightChange = { text: String->
                    cmaterialWeight = text
                    project.recyclableMats[materialNum].Smkg = cmaterialWeight
                }
                project.recyclableMats[materialNum].Smkg = cmaterialWeight
                // Weight input
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
            // Same as for material -> allows transport to be deleted
            for (transport in 0..transportAddYes) {
                var showCardt by remember {mutableStateOf( true)}
                if(showCardt) {
                    project.recyclableMats[materialNum].transportsDelList.add(transport, 0)
                    rTransportAdd(onDeleteClicked = { showCardt = false }, materialNum = materialNum, transportNum = transport)
                }
                else {
                    Card () {

                    }
                    project.recyclableMats[materialNum].transportsDelList[tranport] = 1
                    project.recyclableMats[materialNum].transports[transport].deleted = 1
                    //transportAddYes -= 1
                }
            }
            Spacer(modifier = Modifier.padding(2.5.dp))
        }
    }
}

// Function for transport content
@Composable
fun rTransportAdd(onDeleteClicked: () -> Unit, materialNum: Int, transportNum: Int) {

    Card () {
        //Dropdown menu code - same as for materials
        Spacer(modifier = Modifier.padding(2.5.dp))
        Row() {
            var mExpanded by remember { mutableStateOf(false) }
            var noTransportFound: Int = 0
            var transportSelected: Int = 0
            project.recyclableMats[materialNum].transports += transportObject(type = "", Sdistance = "", Ldistance = 0.0, deleted = 0)

            var tSelectedText by remember { mutableStateOf("") }
            var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
            project.recyclableMats[materialNum].transports[transportNum].type = tSelectedText
            // Up Icon when expanded and down icon when collapsed
            val icon = if (mExpanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            Column(Modifier.padding(horizontal = 10.dp)) {

                // Create an Outlined Text Field with icon and not expanded
                OutlinedTextField(
                    value = tSelectedText,
                    onValueChange = { tSelectedText = it
                        mExpanded = true
                        project.recyclableMats[materialNum].transports[transportNum].type = tSelectedText
                    },
                    modifier = Modifier
                        .width(250.dp)
                        .onGloballyPositioned { coordinates ->
                            // This value is used to assign the DropDown the same width
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
                                project.recyclableMats[materialNum].transports[transportNum].type = tSelectedText
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
            var transportDistance by remember { mutableStateOf("")}
            project.recyclableMats[materialNum].transports[transportNum].Sdistance = transportDistance

            val onMaterialWeightChange = { text: String->
                transportDistance = text
                project.recyclableMats[materialNum].transports[transportNum].Sdistance = transportDistance
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


// Validation function that checks all data inputted is in correct form
@Composable
fun rValidation(navController: NavHostController, materialList: List<String>, transportList: List<String>) {
    var allValid = true
    for (i in rDelList) {
        if (i == 0) {
            if (project.recyclableMats[i].material == "") {
                allValid = false
                val context = LocalContext.current
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show() //TO DO
            }
            if (project.recyclableMats[i].Smkg == "") {
                allValid = false
                val context = LocalContext.current
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show() //TO DO
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
            for ( k in materialList) {
                if (project.recyclableMats[i].material == k) {
                    matIn = 1
                    break
                }
            }
            if(matIn == 0){
                allValid = false
                val context = LocalContext.current
                Toast.makeText(context, "Please select a material in the database", Toast.LENGTH_SHORT).show() //TO DO
                //return
            }
            if(project.recyclableMats[i].Smkg.toDoubleOrNull() == null) {
                allValid = false
                val context = LocalContext.current
                Toast.makeText(context, "Material Weights must be numbers", Toast.LENGTH_SHORT).show() //TO DO
                //return
            }
            if(project.recyclableMats[i].Smkg.toDoubleOrNull() != null) {
                project.recyclableMats[i].Lmkg = project.recyclableMats[i].Smkg.toDouble()
            }
            for (k in project.recyclableMats[i].transportsDelList) {
                if (k == 0) {
                    if (project.recyclableMats[i].transports[k].type == "") {
                        allValid = false
                        val context = LocalContext.current
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    }
                    if (project.recyclableMats[i].transports[k].Sdistance == "") {
                        allValid = false
                        val context = LocalContext.current
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    }
                    var tranIn = 0
                    for(l in transportList) {
                        if(project.recyclableMats[i].transports[k].type == l) {
                            tranIn = 1
                            break
                        }
                    }
                    if(tranIn == 0){
                        allValid = false
                        val context = LocalContext.current
                        Toast.makeText(context, "Please select a transport type in the database", Toast.LENGTH_SHORT).show()
                        //return
                    }
                    if(project.recyclableMats[i].transports[k].Sdistance.toDoubleOrNull() == null) {
                        allValid = false
                        val context = LocalContext.current
                        Toast.makeText(context, "Distances must be numbers", Toast.LENGTH_SHORT).show() //TO DO
                        //return
                    }
                    if(project.recyclableMats[i].transports[k].Sdistance.toDoubleOrNull() != null) {
                        project.recyclableMats[i].transports[k].Ldistance = project.recyclableMats[i].transports[k].Sdistance.toDouble()
                    }
                }
            }



        }
    }
//    for (material in project.recyclableMats) {
//        if (material.deleted ==1) {
//            project.recyclableMats.remove(material)
//            return
//        }
//        if (material.material == "") {
//            allValid = false
//            val context = LocalContext.current
//            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
//        }
//        if (material.Smkg == "") {
//            allValid = false
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
//        var matIn = 0
//        for ( i in rMaterials) {
//            if (material.material == i) {
//                matIn = 1
//            }
//        }
//        if(matIn == 0){
//            allValid = false
//            val context = LocalContext.current
//            Toast.makeText(context, "Please select a material in the database", Toast.LENGTH_SHORT).show()
//            //return
//        }
//        if(material.Smkg.toLongOrNull() == null) {
//            allValid = false
//            val context = LocalContext.current
//            Toast.makeText(context, "Material Weights must be numbers", Toast.LENGTH_SHORT).show()
//            //return
//        }
//        if(material.Smkg.toDoubleOrNull() != null) {
//            material.Lmkg = material.Smkg.toDouble()
//        }
//        for (transport in material.transports) {
//            if (transport.deleted ==1) {
//                material.transports.remove(transport)
//                return
//            }
//            if (transport.type == "") {
//                allValid = false
//                val context = LocalContext.current
//                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
//            }
//            if (transport.Sdistance == "") {
//                allValid = false
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
//                allValid = false
//                val context = LocalContext.current
//                Toast.makeText(context, "Please select a transport type in the database", Toast.LENGTH_SHORT).show()
//                //return
//            }
//            if(transport.Sdistance.toLongOrNull() == null) {
//                allValid = false
//                val context = LocalContext.current
//                Toast.makeText(context, "Distances must be numbers", Toast.LENGTH_SHORT).show()
//                //return
//            }
//            if(transport.Sdistance.toDoubleOrNull() != null) {
//                transport.Ldistance = transport.Sdistance.toDouble()
//            }
//        }
//    }
    if (allValid) {
        navController.navigate(NavRoutes.CounterReceipt.route)
    }
    else {
        return
    }
    //return
}
