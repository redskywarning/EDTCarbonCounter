package com.example.carbon_counter.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.edtcarboncounter.database.AAAllDao
import com.example.edtcarboncounter.database.ADatabaseEvent
import com.example.edtcarboncounter.database.ADatabaseState
import com.example.edtcarboncounter.database.ASortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//Map all of states
@OptIn(ExperimentalCoroutinesApi::class)
class ADatabaseViewModel(
    private val dao: AAAllDao
) : ViewModel(){

    private val _sortType1 = MutableStateFlow(ASortType.MATERIAL_NAME)
    private val _sortType2 = MutableStateFlow(ASortType.PROJECT_NAME)

    private val _materials = _sortType1 //holds the contacts in relation to sortType
    private val _projects = _sortType2

        .flatMapLatest { sortType ->
            // flatMapLatest takes a flow aka our sortType.
            // whenever we change between sortType we change between flows
            // a flow is a coroutine i.e. asynchronous programming so many things happen at once.
            // thus if sort type changes materials change
            when(sortType) {
                ASortType.MATERIAL_NAME -> dao.getMaterialsOrderedByMaterialName()
                ASortType.PROJECT_NAME -> dao.getProjectsOrderedByProjectName()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
        //only happens if there is something in database, otherwise default is emptyList

    private val _state = MutableStateFlow(ADatabaseState())
    val state = combine(_state, _sortType1, _sortType2, _materials, _projects) { state, sortType1, sortType2, materials, projects ->
        state.copy(
            materials = materials, // Update materials list
            projects = projects,   // Update projects list
            sortType1 = sortType1, // Update sort type for materials
            sortType2 = sortType2  // Update sort type for projects
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ADatabaseState())


}

    fun onEvent(event: ADatabaseEvent){
        when(event) {
            is ADatabaseEvent.DeleteMaterial -> {
                viewModelScope().launch {
                    dao.deleteMaterial(event.material)
                }
            }

            ADatabaseEvent.SaveMaterial -> TODO()

            /////////////////////////////////////////////////////////////////////
            is ADatabaseEvent.SetMaterialName -> {
                _state.update { it.copy(
                    materialName = event.materialName
                ) }
            }

            is ADatabaseEvent.SetCarbonContent -> {
                _state.update { it.copy(
                    carbonContent = event.carbonContent
                ) }
            }
            /////////////////////////////////////////////
            ADatabaseEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingMaterial = true // adding contact so don't dialog box
                ) }
            }

            ADatabaseEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingMaterial = false //not adding contact so hide dialog box
                ) }
            }
            ////////////////////////////////////////////////
            is ADatabaseEvent.SortMaterials -> {
                _sortType1.value = event.sortType
            }
            is ADatabaseEvent.SortProjects -> {
                _sortType2.value = event.sortType
            }
            /////////////////////////////////////////////////
            is ADatabaseEvent.DeleteProjects -> TODO()
            ADatabaseEvent.SaveProject -> TODO()
            is ADatabaseEvent.SetKgMaterialUsed -> TODO()
            is ADatabaseEvent.SetProjectName -> TODO()
        }

    }


}