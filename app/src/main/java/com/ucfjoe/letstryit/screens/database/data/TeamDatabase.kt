package com.ucfjoe.letstryit.screens.database.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ucfjoe.letstryit.screens.database.data.dao.TeamDao
import com.ucfjoe.letstryit.screens.database.data.entity.TeamEntity

@Database(entities = [TeamEntity::class], version = 1)
abstract class TeamDatabase : RoomDatabase() {
    abstract val teamDao: TeamDao

    companion object {
        private var instance: TeamDatabase? = null

        @Synchronized
        fun getInstance(context: Context): TeamDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamDatabase::class.java,
                    "team_database.db"
                )
                    .build()
            }
            return instance!!
        }
    }
}