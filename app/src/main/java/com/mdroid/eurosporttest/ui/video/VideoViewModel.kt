package com.mdroid.eurosporttest.ui.video

import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoViewModel  @Inject constructor(
    val player: Player
): ViewModel() {

    fun initPlayer(url: String) {
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.playWhenReady = false
        player.prepare()
    }

    fun pausePlayer() {
        player.pause()
    }

    fun playPlayer() {
        player.play()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

}