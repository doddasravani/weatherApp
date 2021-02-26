package com.open.weather.dagger

import android.app.Application
import com.open.weather.WeatherApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [WeatherModule::class,
    WeatherActivityModule::class,
    AndroidSupportInjectionModule::class])
@Singleton
interface WeatherComponent {

    /*
     * We will call this builder interface from our custom Application class.
     * This will set our application object to the AppComponent.
     * So inside the AppComponent the application instance is available.
     * So this application instance can be accessed by our modules
     * such as ApiModule when needed
     *
     * */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): WeatherComponent
    }

    /*
     * This is our custom Application class
     * */
    fun inject(weatherApplication: WeatherApplication)
}