package com.example.carbon_counter.database

//Tells us state of what is currently on screen.
data class ADatabaseState (
    val materials: List<MaterialEntity> = emptyList(),
    val materialName: String = "",
    val carbonContent: Long = 0L,
    val isAddingMaterial: Boolean = false,
    val sortType1: ASortType = ASortType.MATERIAL_NAME,

    val projects: List<ProjectEntity> = emptyList(),
    val materialUsed: String = "",
    val kgMaterialUsed: Long = 0L,
    val isAddingProject: Boolean = false,
    val sortType2: ASortType = ASortType.PROJECT_NAME
)