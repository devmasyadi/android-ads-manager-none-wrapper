package com.adsmanager.adswrapper

import android.app.Activity
import com.adsmanager.core.CallbackAds
import com.adsmanager.core.CallbackOpenAd

class AdsManagerOpenAdWrapper(

) {

    private var currentActivity: Activity? = null

    fun setCurrentActivity(activity: Activity) {
        this.currentActivity = activity

    }

    fun getCurrentActivity() = currentActivity

    fun isShowingAd(): Boolean {
        return false
    }

    fun loadAd(
        activity: Activity,
        callbackAds: CallbackAds?
    ) {
        callbackAds?.onAdFailedToLoad("ads none")
    }

    fun showAdIfAvailable(
        activity: Activity,
        callbackOpenAd: CallbackOpenAd?
    ) {
        callbackOpenAd?.onAdFailedToLoad("ads none")
    }

}
