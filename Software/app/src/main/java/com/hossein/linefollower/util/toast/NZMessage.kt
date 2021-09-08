package com.hossein.linefollower.util.toast

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.CountDownTimer
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.SizeProvider

class NZMessage {
    private lateinit var popupWindow: PopupWindow
    private var autoDismiss: Boolean = true

    private constructor(
            context: Context,
            builder: Builder
    ) {
        popupWindow = PopupWindow(context)
        popupWindow.setBackgroundDrawable(null)
//        popupWindow.isClippingEnabled = false
        popupWindow.isFocusable = false
        if (builder.customView == null){

            val view = HotToastView(
                    context,
                    builder.message,
                    builder.textColor,
                    builder.icon,
                    builder.backgroundColor,
                    builder.lineColor,
                    builder.typeface,
                    builder.iconColor
            )
            popupWindow.contentView = view
        }else{
            popupWindow.contentView = builder.customView
        }
        popupWindow.width = FrameLayout.LayoutParams.MATCH_PARENT
        popupWindow.height = FrameLayout.LayoutParams.WRAP_CONTENT
        this.autoDismiss = builder.autoDismiss
    }


    fun showAsDropDown(view: View) {
        popupWindow.showAsDropDown(
                view,
                0,
                0,
                Gravity.TOP or Gravity.CENTER_HORIZONTAL
        )
        if (autoDismiss)
            setupCounter()
    }

    fun showAsDefault(view: View){
        popupWindow.animationStyle = R.style.Animation
        popupWindow.showAtLocation(
                view,
                Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                0,
                0
        )
        if (autoDismiss)
            setupCounter()
    }

    fun slideInDown(view: View){
        val distance: Int = view.top + view.height
        val animationSet = AnimatorSet()
        animationSet.duration = 300
        animationSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationY", -distance.toFloat(), 0f)
        )
        animationSet.start()
    }

    fun dismiss(){
        if (popupWindow.isShowing) popupWindow.dismiss()
    }

    private fun setupCounter() {
        val timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                popupWindow.dismiss()
            }
        }
        timer.start()
    }

    class Builder(private val context: Context){

        var message: String = ""
            private set
        var lineColor = ColorProvider.dividerColor
            private set
        var textColor = ColorProvider.primaryTextColor
            private set
        var backgroundColor = ColorProvider.white
            private set
        var typeface = Typeface.DEFAULT
            private set
        var icon: Int = 0
            private set
        var autoDismiss = true
            private set
        var customView: View? = null
            private set
        var iconColor: Int = 0
            private set
        fun message(message: String) = apply{
            this.message = message
        }
        fun lineColor(color: Int) = apply{
            lineColor = color
        }
        fun textColor(color: Int) = apply {
            textColor = color
        }
        fun icon(icon: Int) = apply{
            this.icon = icon
        }
        fun backgroundColor(color: Int) = apply {
            backgroundColor = color
        }
        fun typeFace(font: Typeface) = apply {
            typeface = font
        }
        fun autoDismiss(auto: Boolean) = apply {
            autoDismiss = auto
        }
        fun setIconColor(color: Int) = apply {
            iconColor = color
        }
        fun build() : NZMessage {
                return NZMessage(
                        context,
                        this
                )
        }

        fun setView(view: View) = apply{
            customView = view
        }

    }
    class HotToastView(
            context: Context,
            val text: String,
            val textColor: Int,
            val icon: Int,
            val backgroundColor: Int,
            val lineColor: Int,
            val typeface: Typeface?,
            val iconColor: Int
    ) : FrameLayout(context)
    {

        private lateinit var cardView: CardView
        private lateinit var containerLinearLayout: LinearLayout
        private lateinit var textContainerLinearLayout: LinearLayout
        private lateinit var lineView: View
        private lateinit var imageView: ImageView
        private lateinit var textView: TextView

        init {
            initView()
        }

        private fun initView() {
            clipChildren = false

            cardView = CardView(context)
            cardView.elevation = dpToPx(12).toFloat()
            cardView.radius = dpToPx(8).toFloat()
            val cardViewL = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            cardViewL.setMargins(
                dpToPx(16),
                SizeProvider.getStatusBarHeight(context),
                dpToPx(16),
                dpToPx(16)
            )
            addView(
                cardView,
                cardViewL
            )

            containerLinearLayout = LinearLayout(context)
            val containerLinearLayoutL = LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            containerLinearLayout.gravity = Gravity.CENTER_VERTICAL
            cardView.addView(
                    containerLinearLayout,
                    containerLinearLayoutL
            )



            lineView = View(context)
            val lineViewL = LinearLayout.LayoutParams(
                    dpToPx(8),
                    ViewGroup.LayoutParams.MATCH_PARENT
            )
            lineView.background = createLeftRoundShape(
                    lineColor, dpToPx(4).toFloat()
            )

            containerLinearLayout.addView(
                    lineView,
                    lineViewL
            )

            textContainerLinearLayout = LinearLayout(context)
            textContainerLinearLayout.gravity = Gravity.CENTER_VERTICAL
            val textContainerLinearLayoutL = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
//        textContainerLinearLayoutL.weight = 1f
            containerLinearLayout.addView(
                    textContainerLinearLayout,
                    textContainerLinearLayoutL
            )

            textContainerLinearLayout.background = createRoundCornerShape(
                    backgroundColor,
                    dpToPx(8).toFloat()
            )


            imageView = ImageView(context)
            imageView.setImageResource(icon)
            imageView.setColorFilter(iconColor)
            val imageL = LinearLayout.LayoutParams(
                    dpToPx(25),
                    dpToPx(25)
            )
            imageL.setMargins(
                    dpToPx(20),
                    dpToPx(20),
                    dpToPx(20),
                    dpToPx(20)
            )
            textContainerLinearLayout.addView(
                    imageView,
                    imageL
            )

            textView = TextView(context)
            textView.setTextColor(textColor)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, SizeProvider.generalTextSize.toFloat())
            textView.typeface = typeface
            textView.text = text
            val textViewL = LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            textViewL.weight = 1f
            textViewL.setMargins(
                SizeProvider.generalMargin,
                0,
                SizeProvider.generalMargin,
                0
            )
            textContainerLinearLayout.addView(
                    textView,
                    textViewL
            )

        }

        fun createRoundCornerShape(color: Int, radius: Float): Drawable? {
            val gradientDrawable = GradientDrawable()
            gradientDrawable.cornerRadii = floatArrayOf(
                    0f, 0f,
                    radius, radius,
                    radius, radius,
                    0f, 0f,
            )
            gradientDrawable.setColor(color)
            return gradientDrawable
        }

        fun createLeftRoundShape(color: Int, radius: Float): Drawable? {
            val drawable = GradientDrawable(
            )
            drawable.setColor(color)
            drawable.cornerRadii = floatArrayOf(
                    radius, radius, // top left
                    0f, 0f, // top right
                    0f, 0f,
                    radius, radius, // bottom left
            )
            return drawable
        }

        fun dpToPx(dp: Int): Int {
            return TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    dp.toFloat(),
                    this.resources?.displayMetrics
            ).toInt()
        }
    }

}