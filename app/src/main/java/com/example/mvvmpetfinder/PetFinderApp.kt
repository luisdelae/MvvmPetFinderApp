package com.example.mvvmpetfinder

import android.app.Application
import timber.log.Timber


class PetFinderApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}