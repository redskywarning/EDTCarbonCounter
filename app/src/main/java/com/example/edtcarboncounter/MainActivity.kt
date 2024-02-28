package com.example.edtcarboncounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.edtcarboncounter.screens.*
import com.example.edtcarboncounter.ui.theme.EDTCarbonCounterTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    //TEST
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EDTCarbonCounterTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController=rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        composable(NavRoutes.Home.route) {
            Home(navController = navController)
        }
        composable(NavRoutes.InstructionManual.route){
            InstructionManual(navController = navController)
        }
        composable(NavRoutes.ReceiptRecall.route){
            ReceiptRecall(navController = navController)
        }
        composable(NavRoutes.CounterMaterial.route){
            CounterMaterial(navController = navController)
        }
        composable(NavRoutes.CounterReceipt.route){
            CounterReceipt(navController = navController)
        }
        composable(NavRoutes.CounterRecycled.route){
            CounterRecycled(navController = navController)
        }
        composable(NavRoutes.DatabaseAdd.route){
            DatabaseAdd(navController = navController)
        }
        composable(NavRoutes.DatabaseHome.route){
            DatabaseHome(navController = navController)
        }
        composable(NavRoutes.DatabaseSearch.route){
            DatabaseSearch(navController = navController)
        }
        composable(NavRoutes.DatabaseUpdate.route){
            DatabaseUpdate(navController = navController)
        }
        composable(NavRoutes.CarbonComparison.route){
            CarbonComparison(navController = navController)
        }
    }
}