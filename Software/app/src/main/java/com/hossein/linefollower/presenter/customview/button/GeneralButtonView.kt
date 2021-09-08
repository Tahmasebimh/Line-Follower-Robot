package com.hossein.linefollower.presenter.customview.button

import android.content.Context
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import com.hossein.linefollower.core.provider.SizeProvider

class GeneralButtonView(context: Context) : AppCompatTextView(context) {

    init {
        setPadding(
            SizeProvider.generalPadding,
            SizeProvider.generalPadding,
            SizeProvider.generalPadding,
            SizeProvider.generalPadding
        )
        setTextColor()
    }

    fun setTextSizeInPixel(size: Int){
        setTextSize(TypedValue.COMPLEX_UNIT_PX, size.toFloat())
    }

}