package com.example.carbon_counter.oldDatabase//package com.example.carbon_counter
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.combine
//import kotlinx.coroutines.flow.flatMapLatest
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.flow.*
//
//
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class ZZCarbon_dbViewModel (
//    private val dao: ZZDatabasesDao
//): ViewModel() {
//
//    private val _sortType = MutableStateFlow(ZZSortType.MATERIAL_NAME)
//    private val _materials = _sortType
//        .flatMapLatest { sortType ->
//            when(sortType){
//                ZZSortType.MATERIAL_NAME -> dao.getMaterialsOrderedByName()
//                ZZSortType.CARBON_CONTENT -> dao.getMaterialsOrderedByCarbonContent()
//                ZZSortType.MATERIAL_FROM -> dao.getMaterialsOrderedByPlace()//These flows allow for reactivity coroutines.
//
//            }
//
//        }
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
//    private val _state = MutableStateFlow(ZZCarbon_dbState())
//    val state = combine(_state,_sortType,_materials) { state, sortType, materials ->
//        state.copy(
//            materials = materials,
//            sortType = sortType
//        )
//
//    }
//
//    fun onEvent(event: ZZCarbon_dbEvent){
//        when(event){
//            is ZZCarbon_dbEvent.DeleteMaterial -> {
//                viewModelScope.launch {
//                    dao.delete_material(event.contact)
//                }
//            }
//
//            ZZCarbon_dbEvent.HideDialog -> {
//                _state.update{ it.copy(
//                    isAddingMaterial = false
//                )
//
//                }
//            }
//            ZZCarbon_dbEvent.SaveMaterial -> {
//                val material_name = state.value.material_name
//                val carbon_content = state.value.carbon_content
//                val material_from = state.value.material_from
//
//                if (material_name.isBlank() || carbon_content.isBlank() || material_from.isBlank()){
//                    return
//                }
//                val material = Carbon_db(
//                    material_name = material_name,
//                    carbon_content = carbon_content,
//                    material_from = material_from
//                )
//                viewModelScope.launch{
//                    dao.upsert_material(material)
//                }
//                _state.update { it.copy(
//                    isAddingMaterial = false,
//                    material_name = "",
//                    carbon_content = 0.0,
//                    material_from = ""
//                )
//
//                }
//
//            }
//            is ZZCarbon_dbEvent.SetCarbonContent -> {
//                _state.update { it.copy(
//                    carbon_content = event.carbon_content
//                )}
//            }
//            is ZZCarbon_dbEvent.SetMaterialName -> {
//                _state.update { it.copy(
//                    material_name = event.material_name
//                )}
//            }
//            is ZZCarbon_dbEvent.SetMaterialFrom -> {
//                _state.update { it.copy(
//                    material_from = event.material_from
//                )}
//            }
//            ZZCarbon_dbEvent.ShowDialog -> {
//                _state.update { it.copy(
//                    isAddingMaterial = true
//                )}
//            }
//            is ZZCarbon_dbEvent.SortMaterial -> {
//                _sortType.value = event.sortType
//            }
//        }
//    }
//}