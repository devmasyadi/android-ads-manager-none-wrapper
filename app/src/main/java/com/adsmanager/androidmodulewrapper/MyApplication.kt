@file:Suppress("DEPRECATION")

package com.adsmanager.androidmodulewrapper

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.adsmanager.ads.di.adsManagerModule
import com.adsmanager.adswrapper.AdsManagerOpenAdWrapper
import com.adsmanager.adswrapper.AdsManagerWrapper
import com.adsmanager.adswrapper.di.adsManagerWrapperModule
import com.adsmanager.core.iadsmanager.IInitialize
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(), Application.ActivityLifecycleCallbacks, LifecycleObserver {

    private var adsManagerOpenAd: AdsManagerOpenAdWrapper? = null

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        val app = startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    adsManagerModule,
                    adsManagerWrapperModule
                )
            )
        }
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        val adsManagerWrapper = app.koin.get<AdsManagerWrapper>()
        adsManagerWrapper.initialize(this, object : IInitialize {
            override fun onInitializationComplete() {

            }
        })
        adsManagerOpenAd = app.koin.get()

    }

    @Suppress("DEPRECATION")
    /** LifecycleObserver method that shows the app open ad when the app moves to foreground. */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        // Show the ad (if available) when the app moves to foreground.
        adsManagerOpenAd?.getCurrentActivity()?.let {
            adsManagerOpenAd?.showAdIfAvailable(it, null)
        }
    }

    /** ActivityLifecycleCallback methods. */
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {
        // An ad activity is started when an ad is showing, which could be AdActivity class from Google
        // SDK or another activity class implemented by a third party mediation partner. Updating the
        // currentActivity only when an ad is not showing will ensure it is not an ad activity, but the
        // one that shows the ad.
        if (adsManagerOpenAd?.isShowingAd() == false) {
            adsManagerOpenAd?.setCurrentActivity(activity)
        }
    }

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {}
}