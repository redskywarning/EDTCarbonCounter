package com.example.carbon_counter.screens

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.carbon_counter.NavRoutes

@Composable
fun CounterRecycled(navController: NavHostController) {
    Scaffold(topBar = {topBar(navController =navController) },
        bottomBar = {BottomBar(navController=navController)},
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate(NavRoutes.CounterReceipt.route)}) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Add")
            }
        }
    ) {
        //content area



    }
}