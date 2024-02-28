package com.example.edtcarboncounter.database

//Contains different events that can happen => this relates to the SQL functions in the Dao files.
sealed interface ADatabaseEvent {
    //////////////////////////////////////////
    object SaveMaterial: ADatabaseEvent
    data class SetMaterialName(val materialName: String): ADatabaseEvent
    data class SetCarbonContent(val carbonContent: Long): ADatabaseEvent
    data class SortMaterials(val sortType: ASortType): ADatabaseEvent
    data class DeleteMaterial(val material: MaterialEntity): ADatabaseEvent
    ////////////////////////////////////////////////////////////////////////////////
    object ShowDialog: ADatabaseEvent
    object HideDialog: ADatabaseEvent
    ////////////////////////////////////////////////////////////////////////////////////
    object SaveProject: ADatabaseEvent
    data class SetProjectName(val projectName: String): ADatabaseEvent
    data class SortProjects(val sortType: ASortType): ADatabaseEvent
    data class DeleteProjects(val project: ProjectEntity): ADatabaseEvent
    ////////////////////////////////////////////////////////////////////////////////////
    data class SetKgMaterialUsed(val kgMaterialUsed: Long): ADatabaseEvent
}