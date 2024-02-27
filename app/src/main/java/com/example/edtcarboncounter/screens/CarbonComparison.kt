package com.example.edtcarboncounter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController




// Remove unnecessary imports

@Composable
fun CarbonComparison(navController: NavHostController) {
    Scaffold(
        topBar = { topBar(navController = navController) },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Carbon Comparison",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(30.dp),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.size(15.dp))

            // Display the table
            CarbonTable()
        }
    }
}

@Composable
fun CarbonTable() {
    Column(modifier = Modifier.padding(16.dp)) {
        // Header row
        Row {
            Text(
                text = "Metal",
                modifier = Modifier.weight(1f),
                style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold) // Adjust font size and style
            )
            Text(
                text = "Percentage",
                modifier = Modifier.weight(1f),
                style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold) // Adjust font size and style
            )
        }
        // Data rows
        Row {
            Text(
                text = "Steel",
                modifier = Modifier.weight(1f),
                style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold) // Adjust font size
            )
            Text(
                text = "80%",
                modifier = Modifier.weight(1f),
                style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold) // Adjust font size
            )
        }
        // Add more rows as needed
    }
}


