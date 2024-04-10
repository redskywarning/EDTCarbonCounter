//package com.example.edtcarboncounter.database
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class ADatabaseViewModel2(
//    //for mapping all states
//    private val dao: AAAllDao //bring in our dao
//): ViewModel() {
//
//    private val _sortTypeMaterial = MutableStateFlow(ASortType1.MATERIAL_NAME)
//    private val _sortTypeProject = MutableStateFlow(ASortType1.PROJECT_NAME)
//    private val _sortTypeTransport = MutableStateFlow(ASortType1.TRANSPORT_NAME)
//    private val _materials = _sortType //holds the contacts in relation to sortType
//    private val _projects = _sortType2
//        .flatMapLatest { sortType ->
//            when(sortType) {
//                ASortType1.MATERIAL_NAME -> dao.getMaterialsOrderedByMaterialName()
//                ASortType1.PROJECT_NAME -> dao.getProjectsOrderedByProjectName()
//                ASortType1.TRANSPORT_NAME -> dao.getTransportOrderedByTransportName()
//            }
//        }
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
//
//    private val _state = MutableStateFlow(ADatabaseState()) //ViewModel hosts state
//    val state = combine(_state, _sortType1, _sortType2, _materials, _projects) { state, sortType1, sortType2, materials, projects ->
//        state.copy(
//            materials = _materials,
//            projects = _projects,
//            sortType1 = _sortType1,
//            sortType2 = _sortType2
//        )
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ADatabaseState())
//
//    fun onEvent(event: ADatabaseEvent) { //whenever the user does something:
//        when(event) {
//            is ADatabaseEvent.DeleteMaterial -> {
//                viewModelScope.launch {
//                    dao.deleteMaterial(event.material) //since dao routine is suspend fun we need to
//                    //launch coroutine hence viewModelScope.launch
//                }
//            }
//            ADatabaseEvent.HideDialog -> {
//                _state.update { it.copy(
//                    isAddingMaterial = false //not adding contact so hide dialog box
//                ) }
//            }
//            ADatabaseEvent.SaveMaterial -> { //most complex one to put in
////                val materialName = state.value.materialNames
////                val carbonContent = state.value.carbonContent
////
////                if(materialName.isBlank() || carbonContent.isBlank() ) {
////                    return
////                }
////
////                val material = MaterialEntity(
////                    materialName = materialName,
////                    carbonContent = carbonContent
////                )
////                viewModelScope.launch {
////                    dao.upsertContact(contact)
////                }
////                _state.update { it.copy(
////                    isAddingContact = false,
////                    firstName = "",
////                    lastName = "",
////                    phoneNumber = ""
////                ) }
//                val materialName = state.value.materialName
//                val carbonContent = state.value.carbonContent
//
//                if(materialName.isBlank() || carbonContent.isEmpty()) {
//                    return
//                }
//
//                val material = MaterialEntity(
//                    materialName = materialName,
//                    carbonContent = carbonContent
//                )
//                viewModelScope.launch {
//                    dao.upsertMaterial(material)
//                }
//                _state.update { it.copy(
//                    isAddingMaterial = false,
//                    materialName = "",
//                    carbonContent = ""
//                ) }
//            }
//            is ADatabaseEvent.SetMaterialName -> {
//                _state.update { it.copy(
//                    materialName = event.lastName
//                ) }
//            }
//            is ADatabaseEvent.SetCarbonContent -> {
//                _state.update { it.copy(
//                    carbonContent = event.phoneNumber
//                ) }
//            }
//            ADatabaseEvent.ShowDialog -> {
//                _state.update { it.copy(
//                    isAddingMaterials = true
//                ) }
//            }
//            is ADatabaseEvent.SortMaterials -> {
//                _sortType1.value = event.sortType1
//            }
//            is ADatabaseEvent.SortProjects -> {
//                _sortType2.value = event.sortType2
//            }
//
//            is ADatabaseEvent.DeleteProjects -> TODO()
//            ADatabaseEvent.SaveProject -> TODO()
//            is ADatabaseEvent.SetKgMaterialUsed -> TODO()
//            is ADatabaseEvent.SetProjectName -> TODO()
//            is ADatabaseEvent.DeleteTransport -> TODO()
//            ADatabaseEvent.SaveTransport -> TODO()
//            is ADatabaseEvent.SetTransportCarbonPerKm -> TODO()
//            is ADatabaseEvent.SetTransportName -> TODO()
//            is ADatabaseEvent.SortTransport -> {
//                _sortType3.value = event.sortType3
//            }
//        }
//    }
//}