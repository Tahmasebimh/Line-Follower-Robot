package com.hossein.linefollower.core.provider

import android.util.TypedValue
import com.hossein.linefollower.util.ApplicationContextHolder

object SizeProvider {

    val generalPadding : Int = dpToPx(10)


    fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), ApplicationContextHolder.application?.resources?.displayMetrics).toInt()
    }
}