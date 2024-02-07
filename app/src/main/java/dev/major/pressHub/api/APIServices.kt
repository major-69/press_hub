package dev.major.pressHub.api

import dev.major.pressHub.modal.NewsResponse
import dev.major.pressHub.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        key:String = Constants.API_KEY

    ): Response<NewsResponse>


    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        key:String = Constants.API_KEY

    ): Response<NewsResponse>

}