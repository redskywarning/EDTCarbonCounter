//package com.example.edtcarboncounter.database
//
//import java.util.Collections.emptyList
//
////Tells us state of what is currently on screen.
//data class ADatabaseState(
//    val materials: List<MaterialEntity> = emptyList(), //default on screen
//    //val materialNames: List<String> = emptyList(), //String = "",
//    //val materialName: String = "",
//    val carbonContent: Double = 0.0,
//    val isAddingMaterial: Boolean = false,
//    val sortTypeMaterial: ASortType1 = ASortType1.MATERIAL_NAME,
//
//    val projects: List<ProjectEntity> = emptyList(),
//    //val projectNames: List<String> = emptyList(), //String = "",
//    val projectName: String = "",
//    val kgMaterialUsed: Double = 0.0,
//    val sortTypeProject: ASortType2 = ASortType2.PROJECT_NAME,
//
//    val transports: List<TransportEntity> = emptyList(),
//    //val transportNames: List<String> = emptyList(), //String = "",
//    val transportName: String = "",
//    val carbonPerKm: Double = 0.0,
//    val isAddingTransport: Boolean = false,
//    val sortTypeTransport: ASortType3 = ASortType3.TRANSPORT_NAME,
//
//    val projectMaterial: List<ProjectMaterial> = emptyList(),
//    //val materialWithCarbonCounts: List<MaterialWithCarbonCount> = emptyList(),
//    val projectsWithMaterialAndCarbonCountAndTransport: List<ProjectWithMaterialAndCarbonCountAndTransport> = emptyList()
//)