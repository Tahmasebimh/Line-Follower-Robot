package com.hossein.linefollower.util

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable

class Utility {

    companion object{
        fun getRoundDrawable(color: Int, radius: Int, strokeSize: Int = 0, onlyBorders: Boolean): Drawable {
            val gradientDrawable = GradientDrawable()
            gradientDrawable.cornerRadius = radius.toFloat()
            if (onlyBorders) {
                gradientDrawable.setStroke(strokeSize, color)
            } else {
                gradientDrawable.setColor(color)
            }
            return gradientDrawable
        }

        fun getRoundDrawableFillAndBorder(color: Int, backgroundColor: Int, radius: Int, strokeSize: Int, onlyBorders: Boolean): Drawable {
            val gradientDrawable = GradientDrawable()
            gradientDrawable.cornerRadius = radius.toFloat()
            gradientDrawable.setStroke(strokeSize, color)
            gradientDrawable.setColor(backgroundColor)
            return gradientDrawable
        }
    }

}