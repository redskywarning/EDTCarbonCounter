package com.example.carbon_counter.oldDatabase//package com.example.carbon_counter
//
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Query
//import androidx.room.Upsert
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface ZZDatabasesDao {
//    @Upsert
//    //@Insert(onConflict = )
//    suspend fun upsert_material(contact: Carbon_Entity)
//
//    @Delete
//    suspend fun delete_material(contact: Carbon_Entity)
//
//    @Query("SELECT * FROM carbon_db ORDER BY material_name ASC")
//    fun getMaterialsOrderedByName(): Flow<List<Carbon_Entity>>
//
//    @Query("SELECT * FROM carbon_db ORDER BY carbon_content ASC")
//    fun getMaterialsOrderedByCarbonContent(): Flow<List<Carbon_Entity>>
//
//    @Query("SELECT * FROM carbon_db ORDER BY material_from ASC")
//    fun getMaterialsOrderedByPlace(): Flow<List<Carbon_Entity>>
//}