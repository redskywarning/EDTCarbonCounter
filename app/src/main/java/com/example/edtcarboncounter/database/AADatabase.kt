package com.example.edtcarboncounter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(
    entities = [
        MaterialEntity::class,
        ProjectEntity::class,
        //ProjectMaterial::class,
        TransportEntity::class,
        ProjectMaterialTransport::class
    ],
    //views = [ProjectWithMaterialAndCarbonCountAndTransport::class], // Include views here
    version = 1, //tells Room how to migrate old db to new db -- I AM NOT DOING THAT
    exportSchema = false
)
abstract class AADatabase : RoomDatabase() {
    abstract fun allDao(): AAAllDao

    private class AADatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var allDao = database.allDao()

                    // Delete all content here.
                    /*allDao.deleteAll()
                    */

                    // Add sample words.
                    var material = MaterialEntity("ICE Bent Steel Rebar", 0.6663, 0)
                    allDao.upsertMaterial(material)
                    material = MaterialEntity("ICE Bent Steel Mesh Product", 0.6971, 1)
                    allDao.upsertMaterial(material)
                    material =
                        MaterialEntity("ROM Group prefabricated reinforcement product", 0.6811, 2)
                    allDao.upsertMaterial(material)
                    material = MaterialEntity("ICE Cement CEM I - Portland cement", 0.91, 3)
                    allDao.upsertMaterial(material)
                    material = MaterialEntity(
                        "ICE Cement CEM II-A-S - 13% GGBs - Portland-slag cement",
                        0.8,
                        4
                    )
                    allDao.upsertMaterial(material)
                    material = MaterialEntity(
                        "ICE Cement CEM II/B-S - 28% GGBs - Portland-slag cement",
                        0.67,
                        5
                    )
                    allDao.upsertMaterial(material)
                    material = MaterialEntity("ICE Clinker", 0.97, 6)
                    allDao.upsertMaterial(material)
                    material = MaterialEntity("ICE Gypsum", 0.002536, 7)
                    allDao.upsertMaterial(material)
                    material = MaterialEntity("ICE Limestone", 0.01577, 8)
                    allDao.upsertMaterial(material)
                    material = MaterialEntity("ICE Aggregates", 0.00747, 9)
                    allDao.upsertMaterial(material)
                    material = MaterialEntity("ICE Fly ash", 0.004, 10)
                    allDao.upsertMaterial(material)
                    material = MaterialEntity("ICE Water", 0.000344, 11)
                    allDao.upsertMaterial(material)
                    material = MaterialEntity("ICE Natural pozzolanic ash", 0.00747, 12)
                    allDao.upsertMaterial(material)

                    //Add transport.
                    var transport = TransportEntity("Diesel Long Haul Truck", 0.057, 0)
                    allDao.upsertTransport(transport)
                    transport = TransportEntity("Cargo Plane", 0.75, 1)
                    allDao.upsertTransport(transport)
                    transport = TransportEntity("Cargo Ship", 0.04, 2)
                    allDao.upsertTransport(transport)

                    //Add transport.
                    var project = ProjectEntity("Trial 1", 0.0)
                    allDao.upsertProject(project)

                    // Add material_transport
                    var project_material = ProjectMaterialTransport(0, 0, 0, 900.0, 100.0)
                    allDao.upsertProjectMaterialTransport(project_material)

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time
        @Volatile
        private var INSTANCE: AADatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AADatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            //synchronized(this) {
//                return INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    ADatabase::class.java,
//                    "project_with_material_and_carbon_count_db"
//                ).build().also {
//                    INSTANCE = it
//                }
//             }
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AADatabase::class.java,
                    "a_database"
                )
                    .addCallback(AADatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
