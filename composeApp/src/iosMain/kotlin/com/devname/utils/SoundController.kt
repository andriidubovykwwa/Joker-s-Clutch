package com.devname.utils

import jokersclutch.composeapp.generated.resources.Res
import kotlinx.cinterop.ExperimentalForeignApi
import org.jetbrains.compose.resources.ExperimentalResourceApi
import platform.AVFAudio.AVAudioPlayer
import platform.Foundation.NSURL

@OptIn(ExperimentalResourceApi::class, ExperimentalForeignApi::class)
object SoundController {
    private var musicPlayer = createPlayer("files/music.mp3", numberOfLoops = -1)
    private var shufflePlayer = createPlayer("files/shuffle.mp3")
    private var cardPlayPlayer = createPlayer("files/play_card.mp3")
    private var clickPlayer = createPlayer("files/click.mp3")

    private const val DEFAULT_VOLUME = 5 // from 0 to 10

    private fun createPlayer(path: String, numberOfLoops: Int = 0): AVAudioPlayer? {
        try {
            val playerUrl = NSURL.URLWithString(Res.getUri(path))
            if (playerUrl != null) {
                val player = AVAudioPlayer(playerUrl, error = null)
                player.let {
                    it.numberOfLoops = numberOfLoops.toLong()
                    it.volume = DEFAULT_VOLUME * 0.1f
                    it.prepareToPlay()
                }
                return player
            }
            return null
        } catch (_: Exception) {
            return null
        }
    }

    fun playMusic(volume: Int) {
        if (volume == 0) {
            musicPlayer?.stop()
            return
        }
        musicPlayer?.let {
            it.volume = 0.1f * volume
        }
        musicPlayer?.play()
    }

    fun playShuffle(volume: Int = DEFAULT_VOLUME) {
        if (volume == 0) return
        shufflePlayer?.let {
            it.volume = 0.1f * volume
        }
        shufflePlayer?.play()
    }

    fun playCardPlay(volume: Int = DEFAULT_VOLUME) {
        if (volume == 0) return
        cardPlayPlayer?.let {
            it.volume = 0.1f * volume
        }
        cardPlayPlayer?.play()
    }

    fun playClick(volume: Int = DEFAULT_VOLUME) {
        if (volume == 0) return
        clickPlayer?.let {
            it.volume = 0.1f * volume
        }
        clickPlayer?.play()
    }
}