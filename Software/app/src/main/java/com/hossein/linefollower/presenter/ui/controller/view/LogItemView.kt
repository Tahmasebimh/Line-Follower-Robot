package com.hossein.linefollower.presenter.ui.controller.view

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.core.provider.margin
import com.hossein.linefollower.presenter.customview.textview.GeneralTextView
import com.hossein.linefollower.util.rfid.RFIDModel

class LogItemView(context: Context) : CardView(context) {

    private lateinit var linearLayout: LinearLayout
    private lateinit var titleTextView: GeneralTextView
    private lateinit var descriptionTextView: GeneralTextView

    init {

        radius = SizeProvider.dpToPx(12).toFloat()
        elevation = SizeProvider.dpToPx(4).toFloat()
        setCardBackgroundColor(ColorProvider.white)

        linearLayout = LinearLayout(context).apply {

            gravity = Gravity.CENTER_VERTICAL

            titleTextView = GeneralTextView(context).apply {
                setTextSizeInPixel(SizeProvider.dpToPx(14))
            }
            addView(
                titleTextView,
                ParamsProvider.Linear.wrapContent()
                    .margins(
                        SizeProvider.dpToPx(16)
                    )
            )

            descriptionTextView = GeneralTextView(context).apply {
                setTextSizeInPixel(SizeProvider.dpToPx(12))
            }
            addView(
                descriptionTextView,
                ParamsProvider.Linear.availableWidthParams()
                    .margin(
                        0,
                        SizeProvider.dpToPx(16),
                        SizeProvider.dpToPx(16),
                        SizeProvider.dpToPx(16),
                    )
            )
        }
        addView(
            linearLayout,
            ParamsProvider.Card.defaultParams()
        )
    }

    fun setupView(data: RFIDModel){
        titleTextView.text = data.value
        descriptionTextView.text = data.flag.toString()
    }

}