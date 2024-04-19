package com.example.edtcarboncounter.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


//class ADatabaseViewModel4 {
//
//}

class ADatabaseViewModel4(private val repository: AADatabaseRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allMaterials: LiveData<List<MaterialEntity>> = repository.allMaterials.asLiveData()
    val allTransport: LiveData<List<TransportEntity>> = repository.allTransport.asLiveData()
    val allProjects: LiveData<List<ProjectEntity>> = repository.allProjects.asLiveData()
    val allProjectNames: LiveData<List<String>> = repository.allProjectNames.asLiveData()
    val allMaterialNames: LiveData<List<String>> = repository.allMaterialNames.asLiveData()
    val allTransportNames: LiveData<List<String>> = repository.allTransportNames.asLiveData()


    val allProjectMaterialTransport: LiveData<List<ProjectWithMaterialAndTransport>> = repository.allProjectMaterialTransport.asLiveData()


    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun upsertMaterial(material: MaterialEntity) = viewModelScope.launch {
        repository.upsertMaterial(material)
    }
    fun upsertTransport(transport: TransportEntity) = viewModelScope.launch {
        repository.upsertTransport(transport)
    }
    fun upsertProject(project: ProjectEntity) = viewModelScope.launch {
        repository.upsertProject(project)
    }
//    fun getAllProjectNames() = viewModelScope.launch {
//        repository.getAllProjectNames()
//    }

//    val projectNames: LiveData<List<String>> = repository.getAllProjectNames()

    fun upsertProjectMaterialTransport(project_material_transport: ProjectMaterialTransport) = viewModelScope.launch {
        repository.upsertProjectMaterialTransport(project_material_transport)
    }
    fun deleteMaterial(material: MaterialEntity) = viewModelScope.launch {
        repository.deleteMaterial(material)
    }
    fun deleteTransport(transport: TransportEntity) = viewModelScope.launch {
        repository.deleteTransport(transport)
    }
    fun deleteProject(project: ProjectEntity) = viewModelScope.launch {
        repository.deleteProject(project)
    }
    fun deleteProjectMaterial(project_material_transport: ProjectMaterialTransport) = viewModelScope.launch {
        repository.deleteProjectMaterialTransport(project_material_transport)
    }
}

class ADatabaseViewModelFactory(private val repository: AADatabaseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ADatabaseViewModel4::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ADatabaseViewModel4(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


