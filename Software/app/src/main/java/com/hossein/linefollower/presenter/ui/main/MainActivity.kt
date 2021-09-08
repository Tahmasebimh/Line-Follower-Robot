package com.hossein.linefollower.presenter.ui.main

import android.view.View
import com.hossein.linefollower.core.BaseActivity

class MainActivity : BaseActivity() {

    override fun setupPage(): View {
        return MainView(context)
    }

}