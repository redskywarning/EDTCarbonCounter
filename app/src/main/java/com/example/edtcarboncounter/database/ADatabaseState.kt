package com.example.edtcarboncounter.database

import java.util.Collections.emptyList

//Tells us state of what is currently on screen.
data class ADatabaseState(
    val materials: List<MaterialEntity> = emptyList(),
    //val materialNames: List<String> = emptyList(), //String = "",
    val materialNames: String = "",
    val carbonContent: Long = 0L,
    val isAddingMaterial: Boolean = false,
    val sortType1: ASortType = ASortType.MATERIAL_NAME,

    val projects: List<ProjectEntity> = emptyList(),
    //val projectNames: List<String> = emptyList(), //String = "",
    val projectNames: String = "",
    val kgMaterialUsed: Long = 0L,
    val isAddingProject: Boolean = false,
    val sortType2: ASortType = ASortType.PROJECT_NAME,

    val projectMaterial: List<ProjectMaterial> = emptyList(),
    val materialWithCarbonCounts: List<MaterialWithCarbonCount> = emptyList(),
    val projectsWithMaterialAndCarbonCount: List<ProjectWithMaterialAndCarbonCount> = emptyList(),
)