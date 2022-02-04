package com.hossein.linefollower.presenter.ui.settings.view

import android.content.Context
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.StringProvider
import com.hossein.linefollower.presenter.ui.controller.view.CustomToolBar
import com.hossein.linefollower.util.bluetooth.ConnectedThreadHelper
import com.hossein.linefollower.util.repository.Reporsitory

class SettingsView(context: Context) : LinearLayout(context) {

    private lateinit var toolbar: CustomToolBar

    private lateinit var speedSettingItemView: SettingItemView

    private val bottomSheetDialog = BottomSheetDialog(context).apply {
    }
    private lateinit var speedBottomSheetView: SpeedBottomSheetView

    private lateinit var manualControllerVIew: ManualControllerVIew

    init {

        orientation = VERTICAL

        toolbar = CustomToolBar(context, CustomToolBar.ToolBarType.BACK_TOOLBAR).apply {
            titleTextView.text = StringProvider.setting
        }
        addView(
            toolbar,
            ParamsProvider.Linear.defaultParams()
        )

        speedSettingItemView = SettingItemView(context).apply {
            iconView.setImageResource(R.drawable.ic_baseline_speed_24)
            titleTextView.text = StringProvider.speed
            descriptionTextView.text = Reporsitory.speed.title
            setOnClickListener {
                bottomSheetDialog.show()
            }
        }
        addView(
            speedSettingItemView,
            ParamsProvider.Linear.defaultParams()
        )

        speedBottomSheetView = SpeedBottomSheetView(context) {
            speedSettingItemView.descriptionTextView.text = it.title
            Reporsitory.speed = it
            ConnectedThreadHelper.write(Reporsitory.speed.value.toByteArray())
            bottomSheetDialog.dismiss()
        }.also {
            bottomSheetDialog.setContentView(it)
        }

        manualControllerVIew = ManualControllerVIew(context).apply {

        }
        addView(
            manualControllerVIew,
            ParamsProvider.Linear.defaultParams()
        )

    }
}