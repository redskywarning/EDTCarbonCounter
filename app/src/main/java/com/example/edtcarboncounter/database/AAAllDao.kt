package com.example.edtcarboncounter.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AAAllDao {
    @Upsert
    suspend fun upsertMaterial(material: MaterialEntity)

    @Delete
    suspend fun deleteMaterial(material: MaterialEntity)

    @Query("SELECT * FROM MaterialEntity ORDER BY materialName ASC")
    fun getMaterialsOrderedByMaterialName(): Flow<List<MaterialEntity>>

    @Query("SELECT materialName FROM MaterialEntity")
    fun getAllMaterialNames(): Flow<List<String>> //Flow<List<MaterialEntity>>

    @Query("SELECT materialId FROM MaterialEntity WHERE materialName = :materialName")
    suspend fun getMaterialIdFromMaterialName(materialName: String): Long //Flow<List<MaterialEntity>>

    @Query("SELECT materialName FROM MaterialEntity WHERE materialId = :materialId")
    suspend fun getMaterialNameFromMaterialId(materialId: Long): String

    ///////////////////////////////////////////////////////////////////////

    @Upsert
    suspend fun upsertTransport(transport: TransportEntity)

    @Delete
    suspend fun deleteTransport(transport: TransportEntity)

    @Query("SELECT * FROM TransportEntity ORDER BY transportName ASC")
    fun getTransportOrderedByTransportName(): Flow<List<TransportEntity>>

    @Query("SELECT transportName FROM TransportEntity")
    fun getAllTransportNames(): Flow<List<String>> //Flow<List<MaterialEntity>>

    @Query("SELECT transportId FROM TransportEntity WHERE transportName = :transportName")
    suspend fun getTransportIdFromTransportName(transportName: String): Long

    @Query("SELECT transportName FROM TransportEntity WHERE transportId = :transportId")
    suspend fun getTransportNameFromTransportId(transportId: Long): String

    ///////////////////////////////////////////////////////////////////////
    @Upsert
    suspend fun upsertProject(project: ProjectEntity)

    @Delete
    suspend fun deleteProject(project: ProjectEntity)

    @Query("SELECT * FROM ProjectEntity ORDER BY projectName ASC")
    fun getProjectsOrderedByProjectName(): Flow<List<ProjectEntity>>

    @Query("SELECT projectName FROM ProjectEntity")
    fun getAllProjectNames(): Flow<List<String>> //List<String> //Flow<List<ProjectEntity>>

    @Query("SELECT projectId FROM ProjectEntity WHERE projectName = :projectName")
    suspend fun getProjectIdFromProjectName(projectName: String): Long

    @Query("SELECT projectName FROM ProjectEntity WHERE projectId = :projectId")
    suspend fun getProjectNameFromProjectId(projectId: Long): String

    @Query("SELECT COUNT(*) FROM ProjectEntity")
    suspend fun getProjectRowCount(): Long


    ////////////////////////////////////////////////////////////////////////////////
//    @Upsert
//    suspend fun upsertProjectMaterial(project_material: ProjectMaterial)
//
//    @Delete
//    suspend fun deleteProjectMaterial(project_material: ProjectMaterial)
//
//    @Query("SELECT * FROM ProjectEntity ORDER BY projectId ASC")
//    fun getProjectMaterialOrderedByProjectId(): Flow<List<ProjectMaterial>> //Flow<List<ProjectEntity>>
    ////////////////////////////////////////////////////////////////////////////////
//    @Transaction
//    @Query("SELECT * FROM ProjectWithMaterialAndCarbonCountAndTransport WHERE projectId = :projectName")
//    fun getProjectWithMaterialsAndCarbonCount(projectName: String): Flow<List<ProjectWithMaterialAndCarbonCountAndTransport>>
    ///////////////////////////////////////////////////////////////////////////////
//    @Transaction
//    @Query("SELECT * FROM ProjectWithMaterialWithTransport WHERE projectId = :projectName")
//    fun getProjectWithMaterialsAndCarbonCount(projectName: String): Flow<List<ProjectWithMaterialWithTransport>>

    @Insert
    suspend fun insertProjectMaterialTransport(project_material_transport: ProjectMaterialTransport)

    @Delete
    suspend fun deleteProjectMaterialTransport(project_material_transport: ProjectMaterialTransport)

//    @Transaction
//    @Query("SELECT * FROM ProjectEntity")
//    fun getProjectMaterialTransport(): Flow<List<ProjectWithMaterialAndTransport>>
}