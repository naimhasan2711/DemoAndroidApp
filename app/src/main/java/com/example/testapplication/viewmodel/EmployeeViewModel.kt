package com.example.testapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.models.Article
import com.example.testapplication.models.NewsResponse
import com.example.testapplication.repository.EmployeeRepository
import com.example.testapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {
    private val _resArticle = MutableLiveData<Resource<NewsResponse>>()
    var breakingNewsPage = 1
    val resArticle: MutableLiveData<Resource<NewsResponse>>
        get() = _resArticle
    init {
        getBreakingNews()
    }

    private fun getBreakingNews() = viewModelScope.launch {
        _resArticle.postValue(Resource.loading(null))
        employeeRepository.getArticles().let {
            if (it.isSuccessful) {
                _resArticle.postValue(Resource.success(it.body()))
            } else {
                _resArticle.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

    fun addArticleIntoDB(article: Article) = viewModelScope.launch {
        employeeRepository.addArticle(article)
    }
}