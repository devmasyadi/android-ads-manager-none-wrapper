package com.adsmanager.adswrapper

import java.util.*

object Utils {

    var lastDate = Date()

    fun isValidBetweenTimeInterstitial(): Boolean {
        val currentDate = Date()
        val diffSeconds = (currentDate.time - lastDate.time) / 1000
        if (diffSeconds >= ConfigAds.intervalTimeInterstitial) {
            lastDate = currentDate
            return true
        }
        return false
    }
}