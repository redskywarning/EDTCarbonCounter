package com.example.edtcarboncounter.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.edhellotcarboncounter.screens.BottomBar
import com.example.edhellotcarboncounter.screens.topBar

@Composable
fun DatabaseUpdate(navController: NavHostController) {
    Scaffold(topBar = {topBar(navController =navController) },
        bottomBar = {BottomBar(navController=navController)}
    ) {
        //content area



    }
}