package com.hossein.linefollower.presenter.ui.settings.view

import android.content.Context
import android.text.InputType
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.core.provider.StringProvider
import com.hossein.linefollower.presenter.customview.textview.GeneralTextView
import com.hossein.linefollower.model.Speed
import com.hossein.linefollower.model.SpeedCommand
import top.defaults.drawabletoolbox.DrawableBuilder

class SpeedBottomSheetView(context: Context, val clickCallback: (value: Speed) -> Unit) : LinearLayout(context) {

    private val firstSpeed: GeneralTextView
    private val line: View
    private val secondSpeed: GeneralTextView
    private val line2: View

    private lateinit var inputLinearLayout: LinearLayout
    private lateinit var editText: EditText
    private lateinit var submitButton: GeneralTextView

    init {
        orientation = VERTICAL

        firstSpeed = GeneralTextView(context).apply {
            gravity = Gravity.CENTER
            SizeProvider.dpToPx(16)
            setTextColor(ColorProvider.primaryTextColor)
            text = SpeedCommand.FIRST_SPEED.speed.title
            setOnClickListener {
                clickCallback.invoke(SpeedCommand.FIRST_SPEED.speed)
            }
        }
        addView(
            firstSpeed,
            ParamsProvider.Linear.get(
                ParamsProvider.MATCH,
                SizeProvider.dpToPx(52)
            )
        )

        line = View(context).apply {

            setBackgroundColor(ColorProvider.dividerColor)
        }
        addView(
            line,
            ParamsProvider.Linear.get(
                ParamsProvider.MATCH,
                SizeProvider.dpToPx(1)
            )
        )

        secondSpeed = GeneralTextView(context).apply {
            gravity = Gravity.CENTER
            SizeProvider.dpToPx(16)
            setTextColor(ColorProvider.primaryTextColor)
            text = SpeedCommand.SECOND_SPEED.speed.title
            setOnClickListener {
                clickCallback.invoke(SpeedCommand.SECOND_SPEED.speed)
            }
        }
        addView(
            secondSpeed,
            ParamsProvider.Linear.get(
                ParamsProvider.MATCH,
                SizeProvider.dpToPx(52)
            )
        )

        line2 = View(context).apply {

            setBackgroundColor(ColorProvider.dividerColor)
        }
        addView(
            line2,
            ParamsProvider.Linear.get(
                ParamsProvider.MATCH,
                SizeProvider.dpToPx(1)
            )
        )

        inputLinearLayout = LinearLayout(context).apply {

            editText = EditText(context).apply {
                setTextSize(TypedValue.COMPLEX_UNIT_PX, SizeProvider.dpToPx(14).toFloat())
                setTextColor(ColorProvider.primaryTextColor)
                hint = "سرعت دلخواه"
                inputType = InputType.TYPE_CLASS_NUMBER
            }
            addView(
                editText,
                ParamsProvider.Linear.get(
                    0,
                    SizeProvider.dpToPx(52)
                ).margins(
                    SizeProvider.dpToPx(16)
                ).weight(1f)
            )

            submitButton = GeneralTextView(context).apply {
                gravity = Gravity.CENTER
                setTextColor(ColorProvider.white)
                background = DrawableBuilder()
                    .cornerRadius(SizeProvider.dpToPx(12))
                    .solidColor(ColorProvider.accentColor)
                    .ripple()
                    .build()
                text = StringProvider.submit
                setPadding(
                    SizeProvider.dpToPx(16),
                    SizeProvider.dpToPx(16),
                    SizeProvider.dpToPx(16),
                    SizeProvider.dpToPx(16),
                )
                setOnClickListener {
                    clickCallback.invoke(
                        Speed(
                            title = "سرعت دلخواه: ${editText.text}",
                            value = "SPEED_" + editText.text.toString()
                        )
                    )
                }
            }
            addView(
                submitButton,
                ParamsProvider.Linear.wrapContent()
                    .margins(
                        SizeProvider.dpToPx(16)
                    )
            )

        }
        addView(
            inputLinearLayout,
            ParamsProvider.Linear.defaultParams()
        )
    }

}