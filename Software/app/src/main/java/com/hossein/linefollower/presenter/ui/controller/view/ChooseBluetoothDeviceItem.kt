package com.hossein.linefollower.presenter.ui.controller.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.presenter.customview.textview.GeneralTextView

class ChooseBluetoothDeviceItem(context: Context) : FrameLayout(context) {

    init {
        initView()
    }

    private lateinit var titleView: GeneralTextView
    private lateinit var lineView: View


    private fun initView() {
        setBackgroundColor(ColorProvider.white)
        titleView = GeneralTextView(context)
        titleView.setPadding(
            SizeProvider.generalPadding,
            SizeProvider.generalPadding,
            SizeProvider.generalPadding,
            SizeProvider.generalPadding
        )
        titleView.gravity = Gravity.CENTER
        addView(
            titleView,
            ParamsProvider.Frame.defaultParams()
        )

        lineView = View(context)
        lineView.setBackgroundColor(ColorProvider.dividerColor)
        addView(
            lineView,
            ParamsProvider.Frame.get(
                ParamsProvider.MATCH,
                SizeProvider.lineHeight
            ).margins(
                SizeProvider.generalMargin,
                0,
                SizeProvider.generalMargin,
                0
            ).gravity(
                Gravity.BOTTOM
            )
        )

    }

    fun setupView(name: String?) {
        titleView.text = name
    }

}