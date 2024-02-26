package com.example.carbon_counter.oldDatabase//package com.example.carbon_counter
////package com.plcoding.carbon_counter
//
//sealed interface ZZCarbon_dbEvent {
//    object SaveMaterial: ZZCarbon_dbEvent
//    data class SetMaterialName(val material_name: String) : ZZCarbon_dbEvent
//    data class SetCarbonContent(val carbon_content: Double) : ZZCarbon_dbEvent
//    data class SetMaterialFrom(val material_from: String) : ZZCarbon_dbEvent
//    object ShowDialog: ZZCarbon_dbEvent
//    object HideDialog: ZZCarbon_dbEvent //Diaglogue box used to hide extra box to add material
//    data class SortMaterial(val sortType: ZZSortType): ZZCarbon_dbEvent
//    data class DeleteMaterial(val contact: Carbon_db): ZZCarbon_dbEvent
//}