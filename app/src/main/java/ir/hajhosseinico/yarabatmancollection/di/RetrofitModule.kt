package ir.hajhosseinico.yarabatmancollection.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.hajhosseinico.yarabatmancollection.model.retrofit.MovieRetrofitInterface
import ir.hajhosseinico.yarabatmancollection.util.InternetStatus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provides retrofit dependencies
 * @Singleton is used because we had only 1 scope. singleton scope is = application lifecycle scope
 * If it was a full application, i would provide dependencies into custom scopes (or activity scope)
 */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().baseUrl("http://www.omdbapi.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): MovieRetrofitInterface {
        return retrofit
            .build()
            .create(MovieRetrofitInterface::class.java)
    }

    /**
     * Checking if phone is connected into internet
     * Using application context
     */

    @Singleton
    @Provides
    fun provideInternetStatus(@ApplicationContext context: Context): InternetStatus {
        return InternetStatus(context)
    }
}