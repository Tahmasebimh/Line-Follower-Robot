package com.hossein.linefollower.presenter.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import com.hossein.linefollower.core.BaseActivity

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun setupPage(): View {
        return MainView(context)
    }

}