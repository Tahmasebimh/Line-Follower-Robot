package com.hossein.linefollower.presenter.customview.button

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.util.Utility

class GeneralButtonView(context: Context) : AppCompatTextView(context) {

    init {
        gravity = Gravity.CENTER
        setPadding(
            SizeProvider.generalPadding,
            SizeProvider.generalPadding,
            SizeProvider.generalPadding,
            SizeProvider.generalPadding
        )
        setTextColor(ColorProvider.textIconMainColor)
        background = Utility.getRoundDrawable(
            ColorProvider.primaryColor,
            SizeProvider.generalRadius,
            onlyBorders = false
        )
        setTextSizeInPixel(SizeProvider.generalTextSize)
    }

    fun setTextSizeInPixel(size: Int){
        setTextSize(TypedValue.COMPLEX_UNIT_PX, size.toFloat())
    }

}