package com.hossein.linefollower.presenter.ui.settings.view

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.presenter.customview.textview.GeneralTextView

class SettingItemView(context: Context) : FrameLayout(context) {

    private lateinit var containerLinearLayout: LinearLayout
    val iconView: ImageView
    val titleTextView: GeneralTextView
    val descriptionTextView: GeneralTextView
    val arrowIconView: ImageView

    private lateinit var lineView: View

    init {

        containerLinearLayout = LinearLayout(context).apply {

            gravity = Gravity.CENTER_VERTICAL

            arrowIconView = ImageView(context).apply {
                setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)
                rotation = 180f
            }
            addView(
                arrowIconView,
                ParamsProvider.Linear.get(
                    SizeProvider.dpToPx(24),
                    SizeProvider.dpToPx(24)
                )
                    .margins(
                        SizeProvider.dpToPx(16),
                        SizeProvider.dpToPx(16),
                        0,
                        SizeProvider.dpToPx(16),
                    )
            )

            descriptionTextView = GeneralTextView(context).apply {
                setTextColor(ColorProvider.secondaryTextColor)
                setTextSizeInPixel(SizeProvider.dpToPx(14))
            }
            addView(
                descriptionTextView,
                ParamsProvider.Linear.wrapContent()
                    .margins(
                        SizeProvider.dpToPx(8),
                        SizeProvider.dpToPx(16),
                        SizeProvider.dpToPx(8),
                        SizeProvider.dpToPx(16),
                    )
            )

            titleTextView = GeneralTextView(context).apply {
                setTextColor(ColorProvider.primaryTextColor)
                setTextSizeInPixel(SizeProvider.dpToPx(16))
                gravity = Gravity.START or Gravity.CENTER_VERTICAL
                typeface = Typeface.DEFAULT_BOLD
            }
            addView(
                titleTextView,
                ParamsProvider.Linear.availableWidthParams()
            )


            iconView = ImageView(context).apply {
                setColorFilter(ColorProvider.primaryTextColor)
            }
            addView(
                iconView,
                ParamsProvider.Linear.get(
                    SizeProvider.dpToPx(24),
                    SizeProvider.dpToPx(24)
                ).margins(
                    SizeProvider.dpToPx(16)
                )
            )





        }
        addView(
            containerLinearLayout,
            ParamsProvider.Frame.defaultParams()
        )

        lineView = View(context).apply {
            setBackgroundColor(ColorProvider.dividerColor)
        }
        addView(
            lineView,
            ParamsProvider.Frame.get(
                ParamsProvider.MATCH,
                SizeProvider.dpToPx(1)
            ).gravity(Gravity.BOTTOM)
        )

    }

}