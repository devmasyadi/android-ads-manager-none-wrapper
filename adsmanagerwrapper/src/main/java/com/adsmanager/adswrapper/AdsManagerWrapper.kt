package com.adsmanager.adswrapper

import android.app.Activity
import android.content.Context
import android.widget.RelativeLayout
import com.adsmanager.core.CallbackAds
import com.adsmanager.core.rewards.IRewards
import com.adsmanager.core.iadsmanager.IInitialize
import com.adsmanager.core.SizeBanner
import com.adsmanager.core.SizeNative


class AdsManagerWrapper(

) : IAds {
    override fun initialize(context: Context, iInitialize: IInitialize) {
    }

    override fun setTestDevices(activity: Activity, testDevices: List<String>) {

    }

    override fun loadGdpr(activity: Activity, childDirected: Boolean) {

    }

    override fun showBanner(
        activity: Activity,
        bannerView: RelativeLayout,
        sizeBanner: SizeBanner,
        callbackAds: CallbackAds?
    ) {
        callbackAds?.onAdFailedToLoad("ads none")
    }

    override fun loadInterstitial(activity: Activity) {

    }

    override fun showInterstitial(activity: Activity, callbackAds: CallbackAds?) {
        callbackAds?.onAdFailedToLoad("ads none")
    }

    override fun showNativeAds(
        activity: Activity,
        nativeView: RelativeLayout,
        sizeNative: SizeNative,
        callbackAds: CallbackAds?
    ) {
        callbackAds?.onAdFailedToLoad("ads none")
    }

    override fun loadRewards(activity: Activity) {

    }

    override fun showRewards(activity: Activity, callbackAds: CallbackAds?, iRewards: IRewards?) {
        callbackAds?.onAdFailedToLoad("ads none")
    }

}