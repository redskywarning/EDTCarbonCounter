package com.example.edtcarboncounter.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.edtcarboncounter.database.MaterialEntity
import com.example.edtcarboncounter.database.ProjectEntity
import com.example.edtcarboncounter.database.ProjectWithMaterialAndCarbonCount
import kotlinx.coroutines.flow.Flow

@Dao
interface AAAllDao {
    @Upsert
    suspend fun upsertMaterial(material: MaterialEntity)

    @Delete
    suspend fun deleteMaterial(material: MaterialEntity)

    @Query("SELECT * FROM MaterialEntity ORDER BY materialName ASC")
    suspend fun getMaterialsOrderedByMaterialName(): Flow<List<MaterialEntity>>

    @Query("SELECT materialName FROM MaterialEntity")
    suspend fun getAllMaterialNames(): Flow<List<String>> //Flow<List<MaterialEntity>>

    ///////////////////////////////////////////////////////////////////////
    @Upsert
    suspend fun upsertProject(project: ProjectEntity)

    @Delete
    suspend fun deleteProject(project: ProjectEntity)

    @Query("SELECT * FROM ProjectEntity ORDER BY projectName ASC")
    fun getProjectsOrderedByProjectName(): Flow<List<ProjectEntity>>

    @Query("SELECT projectName FROM ProjectEntity")
    suspend fun getAllProjectNames(): Flow<List<String>> //Flow<List<ProjectEntity>>

    ////////////////////////////////////////////////////////////////////////////////
    @Upsert
    suspend fun upsertProjectMaterial(project: ProjectMaterial)

    @Delete
    suspend fun deleteProjectMaterial(project: ProjectMaterial)
    ////////////////////////////////////////////////////////////////////////////////
    @Transaction
    @Query("SELECT * FROM ProjectEntity WHERE projectId = :projectName")
    suspend fun getProjectWithMaterialsAndCarbonCount(projectName: String): Flow<List<ProjectWithMaterialAndCarbonCount>>
    ///////////////////////////////////////////////////////////////////////////////
}