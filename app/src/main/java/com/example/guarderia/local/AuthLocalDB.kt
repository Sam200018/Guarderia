package com.example.guarderia.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.guarderia.data.AuthDao
import com.example.guarderia.data.TokenEntity

@Database(entities = [TokenEntity::class], version = 1)
abstract class AuthLocalDB : RoomDatabase() {
    abstract fun authDao(): AuthDao
}