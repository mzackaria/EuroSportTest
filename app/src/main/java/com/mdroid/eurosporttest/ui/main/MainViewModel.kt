package com.mdroid.eurosporttest.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdroid.eurosporttest.local.data.News
import com.mdroid.eurosporttest.remote.GetNewsSorted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewsSorted: GetNewsSorted
): ViewModel() {

    private val _news = mutableStateOf<List<News>>(emptyList())
    val news: State<List<News>> = _news

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch{
            _news.value = getNewsSorted()
        }
    }

}