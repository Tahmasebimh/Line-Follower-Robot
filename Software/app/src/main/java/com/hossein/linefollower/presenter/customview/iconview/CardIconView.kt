package com.hossein.linefollower.presenter.customview.iconview

import android.content.Context
import android.view.Gravity
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider

class CardIconView(context: Context) : CardView(context) {

    val iconView: ImageView by lazy {
        ImageView(context).apply {

        }
    }

    init {
        elevation = SizeProvider.dpToPx(12).toFloat()
        radius = Int.MAX_VALUE.toFloat()

        addView(
            iconView,
            ParamsProvider.Card.get(
                SizeProvider.dpToPx(24),
                SizeProvider.dpToPx(24)
            ).gravity(Gravity.CENTER)
        )

    }

}