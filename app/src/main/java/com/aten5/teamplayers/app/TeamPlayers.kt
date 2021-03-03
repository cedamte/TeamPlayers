package com.aten5.teamplayers.app

import android.app.Application
import com.aten5.teamplayers.BuildConfig
import com.aten5.teamplayers.di.AppComponent
import com.aten5.teamplayers.di.DaggerAppComponent
import timber.log.Timber

class TeamPlayers : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(context = applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}