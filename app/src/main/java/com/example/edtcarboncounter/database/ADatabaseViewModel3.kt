//package com.example.edtcarboncounter.database
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.combine
//import kotlinx.coroutines.flow.flatMapLatest
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class ADatabaseViewModel3(
//    //for mapping all states
//    private val dao: AAAllDao //bring in our dao
//): ViewModel() {
//
//    private val _sortTypeMaterial = MutableStateFlow(ASortType1.MATERIAL_NAME)
//    private val _sortTypeProject = MutableStateFlow(ASortType2.PROJECT_NAME)
//    private val _sortTypeTransport = MutableStateFlow(ASortType3.TRANSPORT_NAME)
//
//    private val _materials = _sortTypeMaterial
//        .flatMapLatest { sortType ->
//            when(sortType) {
//                ASortType1.MATERIAL_NAME -> dao.getMaterialsOrderedByMaterialName()
//                else -> flowOf(emptyList())
//            }
//
//        }
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
//
//    private val _projects = _sortTypeProject
//        .flatMapLatest { sortType ->
//            when(sortType) {
//                ASortType2.PROJECT_NAME -> dao.getProjectsOrderedByProjectName()
//                else -> flowOf(emptyList())
//            }
//        }
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
//
//    private val _transports = _sortTypeTransport
//        .flatMapLatest { sortType ->
//            when(sortType) {
//                ASortType3.TRANSPORT_NAME -> dao.getTransportOrderedByTransportName()
//                else -> flowOf(emptyList())
//            }
//        }
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
//
//    private val _state = MutableStateFlow(ADatabaseState())
//    val state = combine(_state, _projects, _materials, _transports,
//        _sortTypeProject, _sortTypeMaterial, _sortTypeTransport) { state, projects, materials, transports, sortTypeProject, sortTypeMaterial, sortTypeTransport ->
//        state.copy(
//            projects = projects,
//            materials = materials,
//            transports = transports,
//            sortTypeProject = sortTypeProject,
//            sortTypeMaterial = sortTypeMaterial,
//            sortTypeTransport = sortTypeTransport
//        )
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ADatabaseState())
//
//
//    fun onEvent(event: ADatabaseEvent) { //whenever the user does something:
//        when(event) {
//            is ADatabaseEvent.DeleteMaterial -> {
//                viewModelScope.launch {
//                    dao.deleteMaterial(event.material) //since dao routine is suspend fun we need to
//                    //launch coroutine hence viewModelScope.launch
//                }
//            }
//            ADatabaseEvent.HideDialogMaterial -> {
//                _state.update { it.copy(
//                    isAddingMaterial = false //not adding contact so hide dialog box
//                ) }
//            }
//            ADatabaseEvent.SaveMaterial -> { //most complex one to put in
//                val materialName = state.value.materialName
//                val carbonContent = state.value.carbonContent
//
//                if(materialName.isBlank() || carbonContent.isBlank()) {
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
//                    materialName = event.materialName
//                ) }
//            }
//            is ADatabaseEvent.SetCarbonContent -> {
//                _state.update { it.copy(
//                    carbonContent = event.carbonContent
//                ) }
//            }
//            ADatabaseEvent.ShowDialogMaterial -> {
//                _state.update { it.copy(
//                    isAddingContact = true
//                ) }
//            }
//            is ADatabaseEvent.SortMaterials -> {
//                _sortTypeMaterial.value = event.sortTypeMaterial
//            }
//            is ADatabaseEvent.SortProjects -> {
//                _sortTypeMaterial.value = event.sortTypeProject
//            }
//            is ADatabaseEvent.SortTransport -> {
//            _sortTypeMaterial.value = event.sortTypeTransport
//            }
//
//            is ADatabaseEvent.DeleteProjects -> {
//                viewModelScope.launch {
//                    dao.deleteProject(event.project) //since dao routine is suspend fun we need to
//                    //launch coroutine hence viewModelScope.launch
//                }
//            }
//            is ADatabaseEvent.DeleteTransport -> {
//                viewModelScope.launch {
//                    dao.deleteTransport(event.transport) //since dao routine is suspend fun we need to
//                    //launch coroutine hence viewModelScope.launch
//                }
//            }
//
//            ADatabaseEvent.SaveTransport -> { //most complex one to put in
//                val transportName = state.value.transportName
//                val carbonPerKm = state.value.carbonPerKm
//
//                if(transportName.isBlank() || carbonPerKm.isBlank()) {
//                    return
//                }
//
//                val transport = TransportEntity(
//                    transportName = transportName,
//                    carbonPerKm = carbonPerKm
//                )
//                viewModelScope.launch {
//                    dao.upsertMaterial(material)
//                }
//                _state.update { it.copy(
//                    isAddingTransport = false,
//                    transportName = "",
//                    carbonPerKm = ""
//                ) }
//            }
//
//            ADatabaseEvent.HideDialogTransport -> {
//                _state.update { it.copy(
//                    isAddingTransport = false //not adding contact so hide dialog box
//                ) }
//            }
//
//
//
//
//            is ADatabaseEvent.SetKgMaterialUsed -> {
//                _state.update { it.copy(
//                    carbonContent = event.kgMaterialUsed
//                ) }
//            }
//
//
//            is ADatabaseEvent.SetProjectName -> {
//                _state.update { it.copy(
//                    carbonContent = event.projectName
//                ) }
//            }
//            is ADatabaseEvent.SetTransportCarbonPerKm -> {
//                _state.update { it.copy(
//                    carbonContent = event.transportCarbonPerKm
//                ) }
//            }
//            is ADatabaseEvent.SetTransportName -> {
//                _state.update { it.copy(
//                    carbonContent = event.transportName
//                ) }
//            }
//
//            ADatabaseEvent.ShowDialogTransport -> {
//                _state.update {
//                    it.copy(
//                        isAddingTransport = true
//                    )
//                }
//            }
//        }
//    }
//}
//
////    val stateMaterial = combine(_materials, _sortTypeMaterial) { materials, sortType ->
////        ADatabaseState(materials = materials, sortType = sortType)
////    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ADatabaseState())
////
////    val stateProject = combine(_projects, _sortTypeProject) { projects, sortType ->
////        ProjectState(projects = projects, sortType = sortType)
////    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProjectState())
////
////    val stateTransport = combine(_transports, _sortTypeTransport) { transports, sortType ->
////        TransportState(transports = transports, sortType = sortType)
////    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TransportState())
