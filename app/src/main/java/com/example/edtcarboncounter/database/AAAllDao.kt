package com.example.edtcarboncounter.database

////import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Query
//import androidx.room.Transaction
//import androidx.room.Upsert
//import com.example.carbon_counter.database.MaterialEntity
//import com.example.carbon_counter.database.ProjectEntity
//import com.example.carbon_counter.database.ProjectWithMaterialAndCarbonCount
//import kotlinx.coroutines.flow.Flow

//@Dao
//interface AAAllDao {
//    @Upsert
//    suspend fun upsertMaterial(material: MaterialEntity)
//
//    @Delete
//    suspend fun deleteMaterial(material: MaterialEntity)
//
//    @Query("SELECT * FROM MaterialEntity ORDER BY materialName ASC")
//    fun getMaterialsOrderedByMaterialName(): Flow<List<MaterialEntity>>
//
//    ///////////////////////////////////////////////////////////////////////
//    @Upsert
//    suspend fun upsertProject(project: ProjectEntity)
//
//    @Delete
//    suspend fun deleteProject(project: ProjectEntity)
//
//    @Query("SELECT * FROM ProjectEntity ORDER BY projectName ASC")
//    fun getProjectsOrderedByProjectName(): Flow<List<ProjectEntity>>
//
//    ////////////////////////////////////////////////////////////////////////////////
//    @Transaction
//    @Query("SELECT * FROM ProjectEntity")
//    suspend fun getProjectWithCarbon(): LiveData<List<ProjectWithMaterialAndCarbonCount>>
//    ///////////////////////////////////////////////////////////////////////////////
//}