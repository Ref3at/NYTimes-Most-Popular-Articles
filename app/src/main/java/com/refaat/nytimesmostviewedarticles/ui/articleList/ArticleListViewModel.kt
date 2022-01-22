package com.refaat.nytimesmostviewedarticles.ui.articleList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refaat.nytimesmostviewedarticles.network.NYTApi
import com.refaat.nytimesmostviewedarticles.network.PeriodApiFilter
import com.refaat.nytimesmostviewedarticles.network.models.ArticleItem
import com.refaat.nytimesmostviewedarticles.network.models.MostViewedArticlesResponse
import kotlinx.coroutines.launch

enum class NYTApiStatus { LOADING, ERROR, DONE }
class ArticleListViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<NYTApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<NYTApiStatus>
        get() = _status


    private val _articlesLastDay = MutableLiveData<List<ArticleItem>>()
    private val _articlesLastWeek = MutableLiveData<List<ArticleItem>>()
    private val _articlesLastMonth = MutableLiveData<List<ArticleItem>>()

    val articles = MutableLiveData<List<ArticleItem>?>()

    init {
        updateFilter(PeriodApiFilter.LAST_DAY)
        _status.value = NYTApiStatus.LOADING

    }


    private fun getMostViewedArticles(filter: PeriodApiFilter) {

        var articlesTemp: List<ArticleItem>?

        viewModelScope.launch {
            _status.value = NYTApiStatus.LOADING
            try {
                articlesTemp = NYTApi.retrofitService.getArticles(filter.value).articleItems
                _status.value = NYTApiStatus.DONE
            } catch (e: Exception) {
                _status.value = NYTApiStatus.ERROR
                articlesTemp = ArrayList()
                articles.value = null

            }



            when (filter) {
                PeriodApiFilter.LAST_DAY -> {

                    if (articlesTemp != null) {
                        _articlesLastDay.value = articlesTemp!!
                        articles.value = _articlesLastDay.value
                    }

                }
                PeriodApiFilter.LAST_WEEK -> {

                    if (articlesTemp != null) {
                        _articlesLastWeek.value = articlesTemp!!
                        articles.value = _articlesLastWeek.value
                    }

                }
                PeriodApiFilter.LAST_MONTH -> {

                    if (articlesTemp != null) {
                        _articlesLastMonth.value = articlesTemp!!
                        articles.value = _articlesLastMonth.value
                    }
                }

            }
        }
    }

    /**
     * Updates the data set filter for the web services by querying the data with the new filter
     * by calling [getMostViewedArticles]
     * @param filter the [PeriodApiFilter] that is sent as part of the web server request
     */
    fun updateFilter(filter: PeriodApiFilter) {
        when (filter) {
            PeriodApiFilter.LAST_DAY -> {
                if (!_articlesLastDay.value.isNullOrEmpty()) {
                    articles.value = _articlesLastDay.value
                    _status.value = NYTApiStatus.DONE
                    return
                } else {
                    getMostViewedArticles(filter)
                }

            }
            PeriodApiFilter.LAST_WEEK -> {
                if (!_articlesLastWeek.value.isNullOrEmpty()) {
                    articles.value = _articlesLastWeek.value
                    _status.value = NYTApiStatus.DONE
                    return
                } else {
                    getMostViewedArticles(filter)
                }
            }
            PeriodApiFilter.LAST_MONTH -> {
                if (!_articlesLastMonth.value.isNullOrEmpty()) {
                    articles.value = _articlesLastMonth.value
                    _status.value = NYTApiStatus.DONE
                    return
                } else {
                    getMostViewedArticles(filter)
                }
            }

        }

    }
}