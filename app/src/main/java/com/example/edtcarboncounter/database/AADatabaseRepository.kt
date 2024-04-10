package com.example.edtcarboncounter.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class AADatabaseRepository(private val AAAllDao: AAAllDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allMaterials: Flow<List<MaterialEntity>> = AAAllDao.getMaterialsOrderedByMaterialName()
    val allProjects: Flow<List<ProjectEntity>> = AAAllDao.getProjectsOrderedByProjectName()
    val allTransport: Flow<List<TransportEntity>> = AAAllDao.getTransportOrderedByTransportName()
    val allProjectMaterialTransport: Flow<List<ProjectWithMaterialAndTransport>> = AAAllDao.getProjectMaterialTransport()


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
    suspend fun deleteMaterial(material: MaterialEntity) {
        AAAllDao.deleteMaterial(material)
    }
    suspend fun deleteTransport(transport: TransportEntity) {
        AAAllDao.deleteTransport(transport)
    }
    suspend fun deleteProject(project: ProjectEntity) {
        AAAllDao.deleteProject(project)
    }
    suspend fun upsertProjectMaterialTransport(project_material_transport: ProjectMaterialTransport) {
        AAAllDao.upsertProjectMaterialTransport(project_material_transport)
    }
    suspend fun deleteProjectMaterialTransport(project_material: ProjectMaterialTransport) {
        AAAllDao.deleteProjectMaterialTransport(project_material)
    }
}