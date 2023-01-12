package com.mdroid.eurosporttest.ui.main

import androidx.lifecycle.ViewModel
import com.mdroid.eurosporttest.remote.GetNewsSorted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewsSorted: GetNewsSorted
): ViewModel() {


}