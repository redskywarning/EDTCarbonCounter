package com.example.edtcarboncounter.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class AADatabaseRepository(private val AAAllDao: AAAllDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allMaterials: Flow<List<MaterialEntity>> = AAAllDao.getMaterialsOrderedByMaterialName()
    val allMaterialNames: Flow<List<String>> = AAAllDao.getAllMaterialNames()

    val allProjects: Flow<List<ProjectEntity>> = AAAllDao.getProjectsOrderedByProjectName()
    val allProjectNames: Flow<List<String>> = AAAllDao.getAllProjectNames()

    val allTransport: Flow<List<TransportEntity>> = AAAllDao.getTransportOrderedByTransportName()
    val allTransportNames: Flow<List<String>> = AAAllDao.getAllTransportNames()


    //val allProjectMaterialTransport: Flow<List<ProjectWithMaterialAndTransport>> = AAAllDao.getProjectMaterialTransport()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun upsertMaterial(material: MaterialEntity) {
        AAAllDao.upsertMaterial(material)
    }
    suspend fun upsertTransport(transport: TransportEntity) {
        AAAllDao.upsertTransport(transport)
    }
    suspend fun upsertProject(project: ProjectEntity) {
        AAAllDao.upsertProject(project)
    }
    //    fun getAllProjectNames() {
//        AAAllDao.getAllProjectNames()
//    }
    fun getAllMaterialNames() {
        AAAllDao.getAllMaterialNames()
    }
    fun getAllTransportNames() {
        AAAllDao.getAllTransportNames()
    }
    suspend fun deleteMaterial(material: MaterialEntity) {
        AAAllDao.deleteMaterial(material)
    }
    suspend fun deleteTransport(transport: TransportEntity) {
        AAAllDao.deleteTransport(transport)
    }
    suspend fun deleteProject(project: ProjectEntity) {
        AAAllDao.deleteProject(project)
    }
    suspend fun insertProjectMaterialTransport(project_material_transport: ProjectMaterialTransport) {
        AAAllDao.insertProjectMaterialTransport(project_material_transport)
    }
    suspend fun deleteProjectMaterialTransport(project_material: ProjectMaterialTransport) {
        AAAllDao.deleteProjectMaterialTransport(project_material)
    }
    suspend fun getMaterialIdFromMaterialName(materialName: String): Long {
        return AAAllDao.getMaterialIdFromMaterialName(materialName)
    }
    suspend fun getMaterialNameFromMaterialId(materialId: Long): String {
        return AAAllDao.getMaterialNameFromMaterialId(materialId)
    }
    suspend fun getTransportIdFromTransportName(transportName: String): Long {
        return AAAllDao.getTransportIdFromTransportName(transportName)
    }
    suspend fun getTransportNameFromTransportId(transportId: Long): String {
        return AAAllDao.getTransportNameFromTransportId(transportId)
    }
    suspend fun getProjectIdFromProjectName(projectName: String): Long {
        return AAAllDao.getProjectIdFromProjectName(projectName)
    }
    suspend fun getProjectNameFromProjectId(projectId: Long): String {
        return AAAllDao.getProjectNameFromProjectId(projectId)
    }
    suspend fun getProjectRowCount(): Long {
        return AAAllDao.getProjectRowCount()
    }
}