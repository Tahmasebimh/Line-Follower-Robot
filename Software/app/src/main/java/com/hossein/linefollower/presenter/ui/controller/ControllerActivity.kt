package com.hossein.linefollower.presenter.ui.controller

import android.content.Context
import android.content.Intent
import android.view.View
import com.hossein.linefollower.core.BaseActivity

class ControllerActivity: BaseActivity() {

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, ControllerActivity::class.java)
        }
    }

    override fun setupPage(): View {
        return ControllerView(context)
    }



}