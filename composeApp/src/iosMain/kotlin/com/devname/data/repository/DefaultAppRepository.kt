package com.devname.data.repository

import platform.Foundation.NSUserDefaults

class DefaultAppRepository : AppRepository {
    private val userDefaults = NSUserDefaults.standardUserDefaults()

    companion object {
        private const val SOUNDS_KEY = "sounds"
        private const val MUSIC_KEY = "music"
        private const val LAST_COMPLETED_LVL_KEY = "last_completed_lvl"


    }

    override fun setMusic(value: Int) {
        val valueToAssign =
            value.coerceIn(AppRepository.MIN_MUSIC_VALUE, AppRepository.MAX_MUSIC_VALUE)
        userDefaults.setInteger(valueToAssign.toLong(), forKey = MUSIC_KEY)
    }

    override fun getMusic(): Int {
        return if (userDefaults.objectForKey(MUSIC_KEY) != null) {
            userDefaults.integerForKey(MUSIC_KEY).toInt()
        } else AppRepository.DEFAULT_MUSIC_VALUE
    }

    override fun setSounds(value: Int) {
        val valueToAssign =
            value.coerceIn(AppRepository.MIN_SOUNDS_VALUE, AppRepository.MAX_SOUNDS_VALUE)
        userDefaults.setInteger(valueToAssign.toLong(), forKey = SOUNDS_KEY)
    }

    override fun getSounds(): Int {
        return if (userDefaults.objectForKey(SOUNDS_KEY) != null) {
            userDefaults.integerForKey(SOUNDS_KEY).toInt()
        } else AppRepository.DEFAULT_SOUNDS_VALUE
    }

    override fun processCompletedLvl(value: Int) {
        val lastCompletedLvl = getLastCompletedLvl()
        if (lastCompletedLvl >= value) return // Don't overwrite greater lvl
        userDefaults.setInteger(value.toLong(), forKey = LAST_COMPLETED_LVL_KEY)
    }

    override fun getLastCompletedLvl(): Int {
        return if (userDefaults.objectForKey(LAST_COMPLETED_LVL_KEY) != null) {
            userDefaults.integerForKey(LAST_COMPLETED_LVL_KEY).toInt()
        } else AppRepository.DEFAULT_LAST_COMPLETED_LVL_VALUE
    }
}