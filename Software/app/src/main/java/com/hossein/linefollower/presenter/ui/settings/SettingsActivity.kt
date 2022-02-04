package com.hossein.linefollower.presenter.ui.settings

import android.content.Context
import android.content.Intent
import android.view.View
import com.hossein.linefollower.core.BaseActivity
import com.hossein.linefollower.presenter.ui.controller.ControllerActivity
import com.hossein.linefollower.presenter.ui.settings.view.SettingsView

class SettingsActivity : BaseActivity() {

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }

    override fun setupPage(): View {
        return SettingsView(context).apply {

        }
    }
}