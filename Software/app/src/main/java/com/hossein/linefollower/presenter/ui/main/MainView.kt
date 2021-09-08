package com.hossein.linefollower.presenter.ui.main

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.core.provider.StringProvider
import com.hossein.linefollower.presenter.customview.button.GeneralButtonView
import com.hossein.linefollower.util.toast.ToastManager

class MainView(context: Context) : FrameLayout(context) {

    val TAG = "MainView>>>"
    private lateinit var imageView: AppCompatImageView
    private lateinit var generalButton: GeneralButtonView

    init {

        imageView = AppCompatImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.drawable.linefollower)
        addView(
            imageView,
            ParamsProvider.Frame.fullScreen()
        )

        generalButton = GeneralButtonView(context)
        generalButton.text = StringProvider.startText
        addView(
            generalButton,
            ParamsProvider.Frame.defaultParams()
                .margins(
                    SizeProvider.generalMargin,
                    SizeProvider.generalMargin,
                    SizeProvider.generalMargin,
                    SizeProvider.generalMargin * 3
                ).gravity(Gravity.BOTTOM)
        )


        generalButton.setOnClickListener {
            handleBlutooth()
        }

    }

    private fun handleBlutooth() {
        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (mBluetoothAdapter == null){
            ToastManager.showErrorMessage(
                context,
                StringProvider.blutoothNotFound,
                this
            )
            return
        }

        //TODO add how to pair new device
        mBluetoothAdapter.enable()
        val pairedDevice = mBluetoothAdapter.bondedDevices
        pairedDevice.forEach {
            Log.d(TAG, "handleBlutooth: name: ${it.name} type: ${it.uuids}")
        }
    }

}