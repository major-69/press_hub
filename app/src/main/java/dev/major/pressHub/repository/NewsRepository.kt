package dev.major.pressHub.repository

import dev.major.pressHub.api.RetrofitClient
import dev.major.pressHub.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitClient.api.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearchResult(searchQuery: String,pageNumber: Int) =
        RetrofitClient.api.searchForNews(searchQuery,pageNumber)
}
