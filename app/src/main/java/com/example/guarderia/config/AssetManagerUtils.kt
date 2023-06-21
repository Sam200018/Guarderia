package com.example.guarderia.config

import android.content.Context
import java.io.IOException
import java.util.Properties

object AssetManagerUtils {
    fun readPropertiesFile(context: Context, filename: String): Properties? {
        val properties = Properties()
        try {
            context.assets.open(filename).use { inputStream ->
                properties.load(inputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return properties
    }
}