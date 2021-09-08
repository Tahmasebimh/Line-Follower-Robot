package com.hossein.linefollower.presenter.ui.main

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.core.provider.StringProvider
import com.hossein.linefollower.presenter.customview.button.GeneralButtonView
import com.hossein.linefollower.presenter.ui.controller.ControllerActivity
import com.hossein.linefollower.presenter.ui.controller.view.ChooseBTDeviceBottomSheetView

class MainView(context: Context) : FrameLayout(context) {

    val TAG = "MainView>>>"
    private lateinit var imageView: AppCompatImageView
    private lateinit var generalButton: GeneralButtonView
    private lateinit var bottomSheetView: ChooseBTDeviceBottomSheetView
    private lateinit var bottomSheet: BottomSheetDialog

    init {

        imageView = AppCompatImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.drawable.linefollower)
        addView(
            imageView,
            ParamsProvider.Frame.fullScreen()
        )

        generalButton = GeneralButtonView(context)
        generalButton.text = StringProvider.startText
        addView(
            generalButton,
            ParamsProvider.Frame.defaultParams()
                .margins(
                    SizeProvider.generalMargin,
                    SizeProvider.generalMargin,
                    SizeProvider.generalMargin,
                    SizeProvider.generalMargin * 3
                ).gravity(Gravity.BOTTOM)
        )

        bottomSheet = BottomSheetDialog(context)

        generalButton.setOnClickListener {
            context.startActivity(ControllerActivity.newIntent(context))
        }

    }



}