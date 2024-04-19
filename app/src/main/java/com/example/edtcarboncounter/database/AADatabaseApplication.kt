package com.example.edtcarboncounter.database

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

//class AADatabaseApplication {
//}

public class AADatabaseApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
//    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    companion object {
        lateinit var database: AADatabase
        lateinit var repository: AADatabaseRepository
        lateinit var viewModel: ADatabaseViewModel4
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AADatabase::class.java, "my-database").build()
        repository = AADatabaseRepository(database.allDao())
        viewModel = ADatabaseViewModel4(repository)
    }

    public fun getViewModel(): ADatabaseViewModel4 {
        return viewModel
    }
}