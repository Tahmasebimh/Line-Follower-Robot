package com.hossein.linefollower.core.provider

import android.content.Context
import android.util.TypedValue
import com.hossein.linefollower.util.ApplicationContextHolder

object SizeProvider {

    val titleTextSize: Int = dpToPx(18)
    val lineHeight: Int = dpToPx(1)
    val generalTextSize: Int = dpToPx(16)
    val generalMargin: Int = dpToPx(10)
    val generalRadius: Int = dpToPx(12)
    val generalPadding : Int = dpToPx(15)


    fun getDisplaySizeWidth(): Int? {
        return ApplicationContextHolder.application?.resources?.displayMetrics?.widthPixels
    }


    fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), ApplicationContextHolder.application?.resources?.displayMetrics).toInt()
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}