package com.example.edtcarboncounter.database
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material.AlertDialog
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.material.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.edtcarboncounter.database.ADatabaseState
//
//@Composable
//fun AddContactDialog(
//    state: ADatabaseState,
//    onEvent: (ADatabaseEvent) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    AlertDialog(
//        modifier = modifier,
//        onDismissRequest = {
//            onEvent(ADatabaseEvent.HideDialog)
//        },
//        title = { Text(text = "Add material") },
//        text = {
//            Column(
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                TextField(
//                    value = state.materialName,
//                    onValueChange = {
//                        onEvent(ADatabaseEvent.SetMaterialName(it))
//                    },
//                    placeholder = {
//                        Text(text = "Material name and where carbon content data is from")
//                    }
//                )
//                TextField(
//                    value = state.carbonContent,
//                    onValueChange = {
//                        onEvent(ADatabaseEvent.SetCarbonContent(it))
//                    },
//                    placeholder = {
//                        Text(text = "Carbon Content in kgCO2 per 1kg of material")
//                    }
//                )
//            }
//        },
//        buttons = {
//            Box(
//                modifier = Modifier.fillMaxWidth(),
//                contentAlignment = Alignment.CenterEnd
//            ) {
//                Button(onClick = {
//                    onEvent(ADatabaseEvent.SaveMaterial)
//                }) {
//                    Text(text = "Save")
//                }
//            }
//        }
//    )
//}