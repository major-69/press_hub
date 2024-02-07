package dev.major.pressHub.modal

import dev.major.pressHub.modal.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)