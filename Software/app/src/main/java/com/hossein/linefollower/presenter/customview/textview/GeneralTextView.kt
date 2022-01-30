package com.hossein.linefollower.presenter.customview.textview

import android.content.Context
import android.os.Handler
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.SizeProvider

class GeneralTextView(context: Context) : AppCompatTextView(context) {

    private var mText: CharSequence? = null
    private var mIndex = 0
    private var mDelay: Long = 500 //Default 500ms delay


    init {
        setTextColor(ColorProvider.primaryTextColor)
        setTextSizeInPixel(SizeProvider.generalTextSize)
    }

    fun setTextSizeInPixel(size: Int){
        setTextSize(TypedValue.COMPLEX_UNIT_PX, size.toFloat())
    }

    private val mHandler: Handler = Handler()
    private val characterAdder: Runnable = object : Runnable {
        override fun run() {
            text = mText!!.subSequence(0, mIndex++)
            if (mIndex <= mText!!.length) {
                mHandler.postDelayed(this, mDelay)
            }
        }
    }

    fun animateText(text: CharSequence?) {
        mText = text
        mIndex = 0
        setText("")
        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, mDelay)
    }

    fun setCharacterDelay(millis: Long) {
        mDelay = millis
    }

}