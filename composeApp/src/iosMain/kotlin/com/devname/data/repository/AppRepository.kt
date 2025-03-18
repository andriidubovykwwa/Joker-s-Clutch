package com.devname.data.repository

interface AppRepository {

    companion object {
        const val DEFAULT_MUSIC_VALUE = 5
        const val DEFAULT_SOUNDS_VALUE = 5
        const val DEFAULT_LAST_COMPLETED_LVL_VALUE = -1

        const val MIN_MUSIC_VALUE = 0
        const val MAX_MUSIC_VALUE = 10
        const val MIN_SOUNDS_VALUE = 0
        const val MAX_SOUNDS_VALUE = 10
    }

    fun setMusic(value: Int)
    fun getMusic(): Int

    fun setSounds(value: Int)
    fun getSounds(): Int

    fun processCompletedLvl(value: Int)
    fun getLastCompletedLvl(): Int
}