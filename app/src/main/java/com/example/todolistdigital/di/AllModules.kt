package com.example.todolistdigital.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolistdigital.database.AppDataBase
import com.example.todolistdigital.repository.TaskRepository
import com.example.todolistdigital.viewmodel.TaskViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getKoinModuleList() = listOf(
    roomDatabaseModule,
    viewModelModule,
    repositoryModule,
    daoModule,
)

val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDataBase::class.java, "task-database")
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING).build()
    }
}

val daoModule = module {
    single { get<AppDataBase>().getTaskDao() }
}

val repositoryModule = module {
    single { TaskRepository(
        taskDao = get()
    ) }
}

val viewModelModule = module {
    viewModel { TaskViewModel(
        repository = get()
    ) }
}


