package com.devtides.androidcoroutinesflow.service

import com.devtides.androidcoroutinesretrofit.model.NewsArticle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository{

    companion object{
        const val NEWS_URL = "https://raw.githubusercontent.com/DevTides/NewsApi/master/"
        const val DELAY = 3000L
    }

    fun newsService() = Retrofit.Builder()
        .baseUrl(NEWS_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsService::class.java)

    fun getNewsArticles(): kotlinx.coroutines.flow.Flow<NewsArticle> {
        return flow<NewsArticle> {
            val newsList = newsService().getNews()
            newsList.forEach {
                emit(it)
                delay(DELAY)
            }
        }
    }
}