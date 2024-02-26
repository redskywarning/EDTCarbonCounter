package com.example.edtcarboncounter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import android.app.Activity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


//import com.example.carbon_counter.screens.BottomBar
//import com.example.carbon_counter.screens.topBar
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a TableLayout
        val tableLayout = TableLayout(this)
        val layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        tableLayout.layoutParams = layoutParams

        // Create a header row for column names
        val headerRow = TableRow(this)
        val headerParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        headerRow.layoutParams = headerParams

        // Column headers
        val metalHeader = TextView(this)
        metalHeader.text = "Metal"
        val metalHeaderParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        metalHeader.layoutParams = metalHeaderParams
        headerRow.addView(metalHeader)

        val percentageHeader = TextView(this)
        percentageHeader.text = "Percentage"
        val percentageHeaderParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        percentageHeader.layoutParams = percentageHeaderParams
        headerRow.addView(percentageHeader)

        // Add the header row to the tableLayout
        tableLayout.addView(headerRow)

        // Create a row for data
        val dataRow = TableRow(this)
        val dataParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        dataRow.layoutParams = dataParams

        // Row data
        val metalData = TextView(this)
        metalData.text = "Steel"
        val metalDataParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        metalData.layoutParams = metalDataParams
        dataRow.addView(metalData)

        val percentageData = TextView(this)
        percentageData.text = "80%"
        val percentageDataParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        percentageData.layoutParams = percentageDataParams
        dataRow.addView(percentageData)

        // Add the data row to the tableLayout
        tableLayout.addView(dataRow)

        // Set the content view to the TableLayout
        setContentView(tableLayout)
    }
}

@Composable
fun CarbonComparison(navController: NavHostController) {
    Scaffold(topBar = {topBar(navController =navController) },
        bottomBar = {BottomBar(navController=navController)}
    ) {
        //content area
        //content area
        Box(modifier = Modifier
            .background(Color.White)
            .fillMaxSize()) {}
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Carbon Comparison",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(30.dp),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.size(15.dp))


        }

    }
}


