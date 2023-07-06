package com.example.guarderia.data

import android.content.Context
import androidx.room.Room
import com.example.guarderia.config.AssetManagerUtils
import com.example.guarderia.local.AuthLocalDB
import com.example.guarderia.network.AnnouncementsDataSourceRemote
import com.example.guarderia.network.AuthDataSourceRemote
import com.example.guarderia.network.IngestionsDataSourceRemote
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val authRepository: AuthRepository
    val announcementsRepository: AnnouncementsRepository
    val ingestionsRepository: IngestionsRepository
}

class GuarderiaAppContainer(
    private val context: Context
) : AppContainer {
    //    Asi creamos un data source, para poder hacer peticiones al servico
    private val authDataSourceRemote: AuthDataSourceRemote by lazy {
        retrofit.create(AuthDataSourceRemote::class.java)
    }

    private val announcementsDataSourceRemote: AnnouncementsDataSourceRemote by lazy {
        retrofit.create(AnnouncementsDataSourceRemote::class.java)
    }

    private val ingestionsDataSourceRemote: IngestionsDataSourceRemote by lazy {
        retrofit.create(IngestionsDataSourceRemote::class.java)
    }

    private val authLocalDB: AuthLocalDB by lazy {
        Room.databaseBuilder(context, AuthLocalDB::class.java, "auth_db")
            .fallbackToDestructiveMigration().build()
    }

    //    no tocar
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()

    private fun requestRequiresAuthentication(request: Request): Boolean {
        return when (request.url.toString()) {
//            Agregar rutas no protegidas aca
            "/login" -> false
            else -> true
        }
    }

    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        val entity = runBlocking {
            authLocalDB.authDao().getToken()
        }
        if (requestRequiresAuthentication(original)) {
            requestBuilder.header("Authorization", "Bearer ${entity?.token}")
        }
        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()

    //    Aqui se cargan las propierdades del la app
    private val properties = AssetManagerUtils.readPropertiesFile(context, "app.properties")
    private val baseURL = properties?.getProperty("BASE_URL")

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient)
        .baseUrl(baseURL!!).build()

    //Asi se instancia un repo, en este caso el repo cuenta con  data source local y remota
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(authDataSourceRemote, authLocalDB.authDao())
    }

    override val announcementsRepository: AnnouncementsRepository by lazy {
        AnnouncementsRepositoryImpl( announcementsDataSourceRemote)
    }

    override val ingestionsRepository: IngestionsRepository by lazy {
        IngestionsRepositoryImpl(ingestionsDataSourceRemote)
    }
}