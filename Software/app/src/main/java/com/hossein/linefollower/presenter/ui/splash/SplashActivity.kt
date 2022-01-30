package com.hossein.linefollower.presenter.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import com.hossein.linefollower.core.BaseActivity
import com.hossein.linefollower.presenter.ui.splash.view.SplashView
import kotlin.math.roundToInt

class SplashActivity : BaseActivity() {

    override fun setupPage(): View {
        return SplashView(context).apply {

        }.also {
//            val raduis = it.containerLinearLayout.measuredHeight / 2f
//            ViewAnimationUtils.createCircularReveal(
//                it.containerLinearLayout,
//                (raduis/2).roundToInt(),
//                (raduis/2).roundToInt(),
//                0f,
//                raduis
//            ).apply {
//                duration = 1000
//                start()
//            }
        }
    }
}