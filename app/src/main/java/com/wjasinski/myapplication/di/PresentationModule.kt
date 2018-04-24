package com.wjasinski.myapplication.di

import android.content.Context
import com.google.gson.Gson
import com.wjasinski.myapplication.data.net.RestApi
import com.wjasinski.myapplication.data.net.RestService
import dagger.Module
import dagger.Provides
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
public class PresentationModule {

    companion object {
        const val API_URL_KEY = "apiUrl"
        const val API_URL_VALUE = "https://www.godt.no/api/"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context,
                            logger: HttpLoggingInterceptor?): OkHttpClient {

        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cacheSize = 50 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)

        val builder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(cache)

        if (logger != null) {
            builder.addInterceptor(logger)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideOkHttpLogger(): HttpLoggingInterceptor? {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return logging
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson,
                        @Named(DataModule.API_URL_KEY) apiEndpoint: String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(apiEndpoint)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build()

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun provideRestService(restApi: RestApi): RestService =
            RestService(restApi)

    @Provides
    @Singleton
    @Named(DataModule.API_URL_KEY)
    fun provideApiUrl(): String = API_URL_VALUE
}