package com.hossein.linefollower.presenter.ui.splash.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.doOnLayout
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.presenter.customview.textview.GeneralTextView
import com.hossein.linefollower.presenter.ui.controller.ControllerActivity
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

@SuppressLint("SetTextI18n")
class SplashView(context: Context) : FrameLayout(context) {

    lateinit var containerLinearLayout: LinearLayout
    private lateinit var amirKabirLogo: ImageView
    private lateinit var descriptionTextView: GeneralTextView

    init {

        setBackgroundColor(ColorProvider.white)

        containerLinearLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER

            amirKabirLogo = ImageView(context).apply {
                setImageResource(R.drawable.logo)
            }
            addView(
                amirKabirLogo,
                ParamsProvider.Linear.get(
                    SizeProvider.getDisplaySizeWidth()!! / 2,
                    SizeProvider.getDisplaySizeWidth()!! / 2,
                )
            )

            descriptionTextView = GeneralTextView(context).apply {
                setTextSizeInPixel(SizeProvider.generalTextSize)
                setTextColor(ColorProvider.primaryTextColor)
                gravity = Gravity.CENTER
                setLineSpacing(0f, 1.2f)
                text = "تهیه کننده: \n محمد حسین طهماسبی \n آزمایشگاه الکترونیک پیشرفته خودرو "
            }
            addView(
                descriptionTextView,
                ParamsProvider.Linear.defaultParams()
                    .margins(
                        SizeProvider.dpToPx(16)
                    )
            )


        }
        addView(
            containerLinearLayout,
            ParamsProvider.Frame.fullScreen()
                .gravity(Gravity.CENTER)
        )

        containerLinearLayout.doOnLayout {
            val cx = (containerLinearLayout.left + containerLinearLayout.right) / 2
            val cy = (containerLinearLayout.top + containerLinearLayout.bottom) / 2
            val finalRadius = sqrt(
                (containerLinearLayout.width / 2).toDouble().pow(2.0) +
                        (containerLinearLayout.height / 2).toDouble().pow(2.0)
            )
            ViewAnimationUtils.createCircularReveal(
                containerLinearLayout,
                cx,
                cy,
                0f,
                finalRadius.toFloat()
            ).apply {
                duration = 2500
                start()
            }
            Handler(Looper.myLooper()!!).postDelayed({
                val intent = ControllerActivity.newIntent(context)
                context.startActivity(intent)
            }, 2500)
        }
    }

}