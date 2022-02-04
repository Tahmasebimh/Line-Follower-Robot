package com.hossein.linefollower.presenter.ui.settings.view

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.model.DirectionCommand
import com.hossein.linefollower.presenter.customview.textview.GeneralTextView
import com.hossein.linefollower.util.bluetooth.ConnectedThread
import com.hossein.linefollower.util.bluetooth.ConnectedThreadHelper
import com.hossein.linefollower.util.toast.ToastManager

class ManualControllerVIew(context: Context) : LinearLayout(context) {

    private lateinit var titleTextView: GeneralTextView

    private lateinit var forwardImageView: ImageView

    private lateinit var sideLinearLayout: LinearLayout
    private lateinit var leftImageView: ImageView
    private lateinit var rightImageView: ImageView

    private lateinit var backwardImageView: ImageView

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL

        titleTextView = GeneralTextView(context).apply {

            typeface = Typeface.DEFAULT_BOLD
            setTextColor(ColorProvider.primaryTextColor)
            setTextSizeInPixel(SizeProvider.dpToPx(16))
            text = "تست جهت های حرکت(یک ثانیه): "
            gravity = Gravity.START
        }
        addView(
            titleTextView,
            ParamsProvider.Linear.defaultParams()
                .margins(
                    SizeProvider.dpToPx(16)
                )
        )

        forwardImageView = ImageView(context).apply {
            setImageResource(R.drawable.ic_baseline_arrow_upward_24)
            setColorFilter(ColorProvider.white)
            setBackgroundColor(ColorProvider.accentColor)
            setOnClickListener {
                ConnectedThreadHelper.write(
                    DirectionCommand.FORWARD.value.toByteArray()
                )
            }
        }
        addView(
            forwardImageView,
            ParamsProvider.Linear.get(
                SizeProvider.dpToPx(52),
                SizeProvider.dpToPx(52)
            ).margins(
                SizeProvider.dpToPx(8)
            )
        )

        sideLinearLayout = LinearLayout(context).apply {

            gravity = Gravity.CENTER

            leftImageView = ImageView(context).apply {
                setImageResource(R.drawable.ic_baseline_rotate_left_24)
                setColorFilter(ColorProvider.white)
                setBackgroundColor(ColorProvider.accentColor)
                setOnClickListener {
                    ConnectedThreadHelper.write(
                        DirectionCommand.LEFT.value.toByteArray()
                    )
                }
            }
            addView(
                leftImageView,
                ParamsProvider.Linear.get(
                    SizeProvider.dpToPx(52),
                    SizeProvider.dpToPx(52)
                ).margins(SizeProvider.dpToPx(8))
            )

            rightImageView = ImageView(context).apply {
                setImageResource(R.drawable.ic_baseline_rotate_right_24)
                setColorFilter(ColorProvider.white)
                setBackgroundColor(ColorProvider.accentColor)
                setOnClickListener {
                    ConnectedThreadHelper.write(
                        DirectionCommand.RIGHT.value.toByteArray()
                    )
                }
            }
            addView(
                rightImageView,
                ParamsProvider.Linear.get(
                    SizeProvider.dpToPx(52),
                    SizeProvider.dpToPx(52)
                ).margins(SizeProvider.dpToPx(8))
            )
        }
        addView(
            sideLinearLayout,
            ParamsProvider.Linear.defaultParams()
        )

        backwardImageView = ImageView(context).apply {
            setImageResource(R.drawable.ic_baseline_arrow_downward_24)
            setColorFilter(ColorProvider.white)
            setBackgroundColor(ColorProvider.accentColor)
            setOnClickListener {
                ConnectedThreadHelper.write(
                    DirectionCommand.BACKWARD.value.toByteArray()
                )
            }
        }
        addView(
            backwardImageView,
            ParamsProvider.Linear.get(
                SizeProvider.dpToPx(52),
                SizeProvider.dpToPx(52)
            ).margins(SizeProvider.dpToPx(8))
        )

        ConnectedThreadHelper.addOnSubscriber(object : ConnectedThread.PublishInterface{
            override fun onMessage(message: String?) {
                ToastManager.showSuccessMessage(
                    context,
                    message!!,
                    this@ManualControllerVIew,
                )
            }

        })
    }
}