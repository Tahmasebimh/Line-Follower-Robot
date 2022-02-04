package com.hossein.linefollower.presenter.ui.controller.view

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.presenter.customview.textview.GeneralTextView

class CustomToolBar(context: Context, type: ToolBarType) : RelativeLayout(context) {

    enum class ToolBarType(val type: Int){
        SETTING_TOOLBAR(0),
        BACK_TOOLBAR(1);
    }

    val titleTextView: GeneralTextView by lazy {
        GeneralTextView(context).apply {
            id = View.generateViewId()
            setTextSizeInPixel(SizeProvider.titleTextSize)
            setTextColor(ColorProvider.primaryTextColor)
            typeface = Typeface.DEFAULT_BOLD
        }
    }

    val settingIcon: ImageView by lazy {
        ImageView(context).apply {
            setImageResource(R.drawable.ic_baseline_settings_24)
            setColorFilter(ColorProvider.primaryTextColor)
            setPadding(
                10,
                10,
                10,
                10,
            )
        }
    }

    private val line: View by lazy {
        View(context).apply {
            setBackgroundColor(ColorProvider.dividerColor)
        }
    }


    init {
        when(type.type){
            ToolBarType.SETTING_TOOLBAR.type ->{
                initialSettingToolBar()
            }ToolBarType.BACK_TOOLBAR.type ->{
                initialBackToolbar()
            }else -> {
            TODO()
        }
        }
    }

    private fun initialBackToolbar() {
        addView(
            titleTextView,
            ParamsProvider.Relative.wrapContent()
                .centerInParent()
                .margins(
                    SizeProvider.dpToPx(16)
                )
        )

        addView(
            line,
            ParamsProvider.Relative.get(
                ParamsProvider.MATCH,
                SizeProvider.dpToPx(1)
            ).below(titleTextView.id)
                .margins(
                    0,
                    SizeProvider.dpToPx(16),
                    0,
                    0,
                )
        )
    }

    private fun initialSettingToolBar(){
        addView(
            titleTextView,
            ParamsProvider.Relative.wrapContent()
                .centerInParent()
                .margins(
                    SizeProvider.dpToPx(16)
                )
        )

        addView(
            settingIcon,
            ParamsProvider.Relative.get(
                SizeProvider.dpToPx(44),
                SizeProvider.dpToPx(44)
            )
                .alignParentEnd()
                .centerVertical()
                .margins(
                    SizeProvider.dpToPx(16)
                )
        )

        addView(
            line,
            ParamsProvider.Relative.get(
                ParamsProvider.MATCH,
                SizeProvider.dpToPx(1)
            ).below(titleTextView.id)
                .margins(
                    0,
                    SizeProvider.dpToPx(16),
                    0,
                    0,
                )
        )
    }

}