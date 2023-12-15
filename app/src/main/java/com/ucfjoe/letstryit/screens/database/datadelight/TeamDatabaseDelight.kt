package com.ucfjoe.letstryit.screens.database.datadelight

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ucfjoe.letstryit.TeamDatabase
import com.ucfjoe.letstryit.screens.database.datadelight.repository.TeamRepositoryDelightImpl
import com.ucfjoe.letstryit.screens.database.domain.repository.TeamRepository

class TeamDatabaseDelight {

    companion object {
        fun getTeamRepositoryDelight(context: Context) : TeamRepository {
            val driver  = getSqlDriver(context)
            return getTeamRepository(driver)
        }

        private fun getSqlDriver(context: Context): SqlDriver {
            return AndroidSqliteDriver(
                schema = TeamDatabase.Schema,
                context = context,
                name = "team_delight.db"
            )
        }

        private fun getTeamRepository(driver: SqlDriver) : TeamRepository {

            return TeamRepositoryDelightImpl(TeamDatabase(driver))
        }
    }
}