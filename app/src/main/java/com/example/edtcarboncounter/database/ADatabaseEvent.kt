//package com.example.edtcarboncounter.database
//
////Contains different events that can happen => this relates to the SQL functions in the Dao files.
//sealed interface ADatabaseEvent {
//    //////////////////////////////////////////
//    object SaveMaterial: ADatabaseEvent
//    data class SetMaterialName(val materialName: String): ADatabaseEvent
//    data class SetCarbonContent(val carbonContent: Long): ADatabaseEvent
//    data class SortMaterials(val sortType: ASortType1): ADatabaseEvent
//    data class DeleteMaterial(val material: MaterialEntity): ADatabaseEvent
//    ////////////////////////////////////////////////////////////////////////////////
//    object ShowDialogMaterial: ADatabaseEvent
//    object HideDialogMaterial: ADatabaseEvent
//
//    object ShowDialogTransport: ADatabaseEvent
//    object HideDialogTransport: ADatabaseEvent
//    ////////////////////////////////////////////////////////////////////////////////////
//    object SaveProject: ADatabaseEvent
//    data class SetProjectName(val projectName: String): ADatabaseEvent
//    data class SortProjects(val sortType: ASortType2): ADatabaseEvent
//    data class DeleteProjects(val project: ProjectEntity): ADatabaseEvent
//    ////////////////////////////////////////////////////////////////////////////////////
//    data class SetKgMaterialUsed(val kgMaterialUsed: Long): ADatabaseEvent
//    ////////////////////////////////////////////////////////////////////////////////////
//    object SaveTransport: ADatabaseEvent
//    data class SetTransportName(val transportName: String): ADatabaseEvent
//    data class SetTransportCarbonPerKm(val carbonPerKm: String): ADatabaseEvent
//    data class SortTransport(val sortType: ASortType3): ADatabaseEvent
//    data class DeleteTransport(val transport: TransportEntity): ADatabaseEvent
//    ////////////////////////////////////////////////////////////////////////////////////
//
//}