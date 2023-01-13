package com.mdroid.eurosporttest.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdroid.eurosporttest.local.data.News
import com.mdroid.eurosporttest.remote.GetNewsSorted
import com.mdroid.eurosporttest.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewsSorted: GetNewsSorted
): ViewModel() {

    private val _news = mutableStateOf<Response<List<News>>>(Response.Loading(false))
    val news: State<Response<List<News>>> = _news

    init {
        viewModelScope.launch {
            _news.value = getNews()
        }
    }

    private suspend fun getNews(): Response<List<News>> {
        loading()
        return try {
            Response.Success(getNewsSorted())
        } catch (e: IOException) {
            e.printStackTrace()
            Response.Error(
                message = "Couldn't load intraday info"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Response.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

    private fun loading() {
        _news.value = Response.Loading(true)
    }
}

