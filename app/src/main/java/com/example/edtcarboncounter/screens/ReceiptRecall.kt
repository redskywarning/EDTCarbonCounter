package com.example.edtcarboncounter.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@Composable
fun ReceiptRecall(navController: NavHostController) {
    Scaffold(topBar = {topBar(navController =navController) },
        bottomBar = {BottomBar(navController=navController)}
    ) {
        //content area



    }
}