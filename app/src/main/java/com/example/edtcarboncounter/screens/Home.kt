package com.example.edtcarboncounter.screens

import android.content.res.Resources
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.navigation.NavHostController
import com.example.edtcarboncounter.NavRoutes
import com.google.android.material.color.MaterialColors.getColor

//val purple: Int = resources.getColor(R.color.purple_200)
@Composable
fun Home(navController: NavHostController) {
    Scaffold(topBar = {topBar(navController =navController) },
        bottomBar = {BottomBar(navController=navController)}
    ) {
        //content area
        Box(modifier = Modifier
            .background(Color.White)
            .fillMaxSize()) {}
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Carbon Counter",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(30.dp),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.size(15.dp))
            Box (modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .clickable(onClick = { navController.navigate(NavRoutes.CounterProject.route) })
                .background(Color.LightGray)
                .height(100.dp),
                contentAlignment = Alignment.Center){
                Text("Carbon Counter", textAlign = TextAlign.Center, fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.size(40.dp))
            Box (modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .clickable(onClick = { navController.navigate(NavRoutes.CarbonComparison.route) })
                .background(Color.LightGray)
                .height(100.dp),
                contentAlignment = Alignment.Center){
                Text("Carbon Comparison", textAlign = TextAlign.Center, fontSize = 30.sp)
            }
//            Spacer(modifier = Modifier.size(40.dp))
//            Box (modifier = Modifier
//                .padding(horizontal = 30.dp)
//                .fillMaxWidth()
//                .clickable(onClick = { navController.navigate(NavRoutes.DatabaseHome.route) })
//                .background(Color.LightGray)
//                .height(100.dp),
//                contentAlignment = Alignment.Center){
//                Text("Database", textAlign = TextAlign.Center, fontSize = 30.sp)
//            }
            Spacer(modifier = Modifier.size(40.dp))
            Box (modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .clickable(onClick = { navController.navigate(NavRoutes.ReceiptRecall.route) })
                .background(Color.LightGray)
                .height(100.dp),
                contentAlignment = Alignment.Center){
                Text("History", textAlign = TextAlign.Center, fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.size(40.dp))
            Box (modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .clickable(onClick = { navController.navigate(NavRoutes.InstructionManual.route) })
                .background(Color.LightGray)
                .height(100.dp),
                contentAlignment = Alignment.Center){
                Text("Instruction Manual", textAlign = TextAlign.Center, fontSize = 30.sp)
            }
        }
    }
}

//topBar function
@Composable
fun topBar(navController: NavHostController){
    TopAppBar(
        title = {
            Text(text = "")

        },
//        navigationIcon = {
//            //navigate up takes you back
//            IconButton(onClick = {navController.navigateUp()}) {
//                Icon(Icons.Filled.ArrowBack, "backIcon")
//            }
//        },
        backgroundColor = Color.LightGray,
        contentColor = Color.Black,
        elevation = 10.dp,
    )
}

//bottom bar function
@Composable
fun BottomBar(navController: NavHostController) {
    val selectedIndex = remember { mutableStateOf(4) }
    BottomNavigation(elevation = 10.dp, backgroundColor = Color.LightGray, contentColor = Color.Black) {
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"")
        },
            label = { Text(text = "Home") },
            selected = (selectedIndex.value == 0),
            onClick = {
                navController.navigate(NavRoutes.Home.route)
            })
    }
}