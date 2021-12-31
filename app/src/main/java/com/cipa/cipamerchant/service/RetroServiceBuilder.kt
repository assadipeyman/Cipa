package com.cipa.cipamerchant.service

import com.cipa.cipamerchant.memory.MemoryData
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.typeOf

object RetroServiceBuilder {
    fun<T : CipaPrivateService> buildService(service: Class<T>): T {

        return Retrofit.Builder()
            .baseUrl("http://91.232.65.100:1460/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(OAuthInterceptor("Bearer", MemoryData.getToken())).build()
            )
            .build().create(service)
    }

    fun<T : CipaPublicService> buildService(service: Class<T>): T {
        val client: OkHttpClient =

        return Retrofit.Builder()
            .baseUrl("http://91.232.65.100:1460/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build().create(service)
    }

    class OAuthInterceptor(private val tokenType: String, private val acceessToken: String): Interceptor {

        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()
                request = request.newBuilder().header("Authorization", "$tokenType $acceessToken").build()
            return chain.proceed(request)
        }
    }
}