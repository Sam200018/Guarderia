package com.example.guarderia.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "tokens")
data class TokenEntity(
     @PrimaryKey val id: Int,
     val token: String
)


@Dao
interface AuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToken(token: TokenEntity)

    @Query("SELECT * FROM tokens WHERE id = 1")
    suspend fun getToken(): TokenEntity?

    @Query("DELETE FROM tokens")
    suspend fun removeToken()

}