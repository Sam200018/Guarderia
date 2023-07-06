package com.example.guarderia.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.guarderia.data.AuthDao
import com.example.guarderia.data.TokenEntity
import com.example.guarderia.model.User

@Database(entities = [TokenEntity::class, User::class], version = 2)
abstract class AuthLocalDB : RoomDatabase() {
    abstract fun authDao(): AuthDao
}