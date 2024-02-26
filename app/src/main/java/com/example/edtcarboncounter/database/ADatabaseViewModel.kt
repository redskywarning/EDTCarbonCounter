package com.example.edtcarboncounter.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carbon_counter.database.ASortType
import com.example.carbon_counter.oldDatabase.ZZSortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//Map all of states
@OptIn(ExperimentalCoroutinesApi::class)
class ADatabaseViewModel(
    private val dao: AACarbonDao
) : ViewModel(){

    private val _sortType1 = MutableStateFlow(ASortType.MATERIAL_NAME)

    private val _materials = _sortType1 //holds the contacts in relation to sortType
        .flatMapLatest { sortType ->
            // flatMapLatest takes a flow aka our sortType.
            // whenever we change between sortType we change between flows
            // a flow is a coroutine i.e. asynchronous programming so many things happen at once.
            // thus if sort type changes materials change
            when(sortType) {
                ASortType.MATERIAL_NAME -> dao.getMaterialsOrderedByMaterialName()
                ZZSortType.CARBON_CONTENT -> dao.getMaterialsOrderedByCarbonContent()
                ASortType.PROJECT_NAME -> dao.getProjectsOrderedByProjectName()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
        //only happens if there is something in database, otherwise default is emptyList

    private val _state = MutableStateFlow(ADatabaseState())

    fun onEvent(event: ADatabaseEvent){
        when(event) {
            is ADatabaseEvent.DeleteMaterial -> {
                viewModelScope.launch {
                    dao.deleteMaterial(event.material)
                }
            }
//            is ADatabaseEvent.DeleteProjects -> {
//                viewModelScope.launch {
//                    dao.deleteProject(event.project)
//                }
//            }
            ADatabaseEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingMaterial = false //not adding contact so hide dialog box
                ) }
            }
            ADatabaseEvent.SaveMaterial -> TODO()
            //ADatabaseEvent.SaveProject -> TODO()
            is ADatabaseEvent.SetCarbonContent -> {
                _state.update { it.copy(
                    carbonContent = event.carbonContent
                ) }
            }
            //is ADatabaseEvent.SetKgMaterialUsed -> TODO()
            is ADatabaseEvent.SetMaterialName -> {
                _state.update { it.copy(
                    materialName = event.materialName
                ) }
            }
            //is ADatabaseEvent.SetMaterialUsed -> TODO()
            //is ADatabaseEvent.SetProjectName -> TODO()
            ADatabaseEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingMaterial = true // adding contact so don't dialog box
                ) }
            }

            is ADatabaseEvent.SortMaterials -> {
                _sortType1.value = event.sortType
            }
            //is ADatabaseEvent.SortProjects -> TODO()
        }

    }


}