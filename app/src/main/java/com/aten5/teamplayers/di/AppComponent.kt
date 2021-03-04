package com.aten5.teamplayers.di

import android.content.Context
import com.aten5.teamplayers.MainActivity
import com.aten5.teamplayers.ui.fav.FavActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, BuilderModule::class, DatabaseModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(favActivity: FavActivity)
}