package com.example.testapplication.repository

import com.example.testapplication.api.ApiHelper
import com.example.testapplication.db.ArticleDao
import com.example.testapplication.models.Article
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val articleDao: ArticleDao
) {
    suspend fun getArticles() = apiHelper.getArticle()
    suspend fun addArticle(article: Article) = articleDao.insert(article)
}