package com.example.edtcarboncounter.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


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

    fun insertProjectMaterialTransport(project_material_transport: ProjectMaterialTransport) = viewModelScope.launch {
        repository.insertProjectMaterialTransport(project_material_transport)
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
    fun deleteProjectMaterialTransport(project_material_transport: ProjectMaterialTransport) = viewModelScope.launch {
        repository.deleteProjectMaterialTransport(project_material_transport)
    }
//    fun getMaterialIdFromMaterialName(materialName: String): Int {
//        return runBlocking{ repository.getMaterialIdFromMaterialName(materialName) }
//    }

//    fun getMaterialIdFromMaterialName(materialName: String) = viewModelScope.launch {
//        repository.getMaterialIdFromMaterialName(materialName)
//    }


    fun getMaterialIdFromMaterialName(materialName: String): Long {
        var materialId: Long = 0
        viewModelScope.launch {
            materialId = repository.getMaterialIdFromMaterialName(materialName)
        }
        return materialId
    }

    fun getMaterialNameFromMaterialId(materialId: Long): String {
        var materialName: String = ""
        viewModelScope.launch {
            materialName = repository.getMaterialNameFromMaterialId(materialId)
        }
        return materialName
    }

    fun getTransportIdFromTransportName(transportName: String): Long {
        var transportId: Long = 0
        viewModelScope.launch {
            transportId = repository.getTransportIdFromTransportName(transportName)
        }
        return transportId
    }

    fun getTransportNameFromTransportId(transportId: Long): String {
        var transportName: String = ""
        viewModelScope.launch {
            transportName = repository.getMaterialNameFromMaterialId(transportId)
        }
        return transportName
    }

    fun getProjectIdFromProjectName(projectName: String): Long {
        var projectId: Long = 0
        viewModelScope.launch {
            projectId = repository.getTransportIdFromTransportName(projectName)
        }
        return projectId
    }

    fun getProjectNameFromProjectId(projectId: Long): String {
        var projectName: String = ""
        viewModelScope.launch {
            projectName = repository.getMaterialNameFromMaterialId(projectId)
        }
        return projectName
    }

    fun getProjectRowCount(): Long {
        var projectId = 0L
        viewModelScope.launch {
            projectId = repository.getProjectRowCount()
        }
        return projectId
    }



//    suspend fun getMaterialNameFromMaterialId(materialId: Long):MaterialEntity{
//        val deferred: Deferred<MaterialEntity> = viewModelScope.async {
//            repository.getMaterialNameFromMaterialId(materialId)
//        }
//        return deferred.await()
//    }
//
//    suspend fun getMaterialIdFromMaterialName(materialName: String):MaterialEntity{
//        val deferred: Deferred<MaterialEntity> = viewModelScope.async {
//            repository.getMaterialIdFromMaterialName(materialName)
//        }
//        return deferred.await()
//    }
//
//    suspend fun getTransportNameFromTransportId(transportId: Long):TransportEntity{
//        val deferred: Deferred<TransportEntity> = viewModelScope.async {
//            repository.getTransportNameFromTransportId(transportId)
//        }
//        return deferred.await()
//    }
//
//    suspend fun getTransportIdFromTransportName(transportName: String):TransportEntity{
//        val deferred: Deferred<TransportEntity> = viewModelScope.async {
//            repository.getTransportIdFromTransportName(transportName)
//        }
//        return deferred.await()
//    }
//
//    suspend fun getProjectNameFromTransportId(projectId: Long): ProjectEntity{
//        val deferred: Deferred<ProjectEntity> = viewModelScope.async {
//            repository.getProjectNameFromProjectId(projectId)
//        }
//        return deferred.await()
//    }
//
//    suspend fun getProjectIdFromProjectName(projectName: String): ProjectEntity{
//        val deferred: Deferred<ProjectEntity> = viewModelScope.async {
//            repository.getProjectIdFromProjectName(projectName)
//        }
//        return deferred.await()
//    }


//    private val _materialId = MutableLiveData<Int>()
//    val materialId: LiveData<Int> = _materialId
//    fun getMaterialIdFromMaterialName(materialName: String) {
//        viewModelScope.launch {
//            val id = repository.getMaterialIdFromMaterialName(materialName)
//            _materialId.postValue(id)
//        }
//    }
//    fun getMaterialNameFromMaterialId(materialId: Int): String {
//        return runBlocking{ repository.getMaterialNameFromMaterialId(materialId) }
//    }
//    fun getTransportIdFromTransportName(transportName: String): Int {
//        return runBlocking{ repository.getTransportIdFromTransportName(transportName) }
//    }
//    fun getTransportNameFromTransportId(transportId: Int): String {
//        return runBlocking{ repository.getTransportNameFromTransportId(transportId) }
//    }
//    fun getProjectIdFromProjectName(projectName: String): Int {
//        return runBlocking{ repository.getProjectIdFromProjectName(projectName) }
//    }
//    fun getProjectNameFromProjectId(projectId: Int): String {
//        return runBlocking{ repository.getTransportNameFromTransportId(projectId) }
//    }
//    fun getProjectRowCount(): Int {
//        return runBlocking{repository.getProjectRowCount()}
//    }
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


