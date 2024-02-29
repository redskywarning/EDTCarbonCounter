package com.example.edtcarboncounter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [
        MaterialEntity::class,
        ProjectEntity::class,
        ProjectMaterial::class
    ],
    version = 1 //tells Room how to migrate old db to new db -- I AM NOT DOING THAT
)
abstract class ADatabase : RoomDatabase() {


    abstract val allDao: AAAllDao

    companion object {
        @Volatile
        private var INSTANCE: ADatabase? = null

        fun getInstance(context: Context): ADatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ADatabase::class.java,
                    "project_with_material_and_carbon_count_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}