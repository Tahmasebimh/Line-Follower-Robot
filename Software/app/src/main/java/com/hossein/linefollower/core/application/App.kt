package com.hossein.linefollower.core.application

import android.app.Application
import com.hossein.linefollower.util.ApplicationContextHolder

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationContextHolder.init(this)
    }
}