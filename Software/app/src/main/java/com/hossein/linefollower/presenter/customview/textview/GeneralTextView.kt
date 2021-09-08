package com.hossein.linefollower.presenter.customview.textview

import android.content.Context
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.SizeProvider

class GeneralTextView(context: Context) : AppCompatTextView(context) {

    init {
        setTextColor(ColorProvider.primaryTextColor)
        setTextSizeInPixel(SizeProvider.generalTextSize)
    }

    fun setTextSizeInPixel(size: Int){
        setTextSize(TypedValue.COMPLEX_UNIT_PX, size.toFloat())
    }
}