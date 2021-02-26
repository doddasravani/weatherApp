package com.open.weather

import android.app.Activity
import android.app.Application
import com.open.weather.dagger.DaggerWeatherComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class WeatherApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerWeatherComponent.builder().
        application(this).
        build().
        inject(this)
    }
}