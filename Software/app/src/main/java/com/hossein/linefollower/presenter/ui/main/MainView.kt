package com.hossein.linefollower.presenter.ui.main

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.core.provider.StringProvider
import com.hossein.linefollower.presenter.calback.SetOnItemClickListener
import com.hossein.linefollower.presenter.customview.button.GeneralButtonView
import com.hossein.linefollower.presenter.ui.main.view.ChooseBTDeviceBottomSheetView
import com.hossein.linefollower.util.toast.ToastManager
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MainView(context: Context) : FrameLayout(context) {

    val TAG = "MainView>>>"
    private lateinit var imageView: AppCompatImageView
    private lateinit var generalButton: GeneralButtonView
    private lateinit var bottomSheetView: ChooseBTDeviceBottomSheetView
    private lateinit var bottomSheet: BottomSheetDialog

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

        bottomSheet = BottomSheetDialog(context)

        generalButton.setOnClickListener {
            handleBlutooth()
        }

    }

    private fun handleBlutooth() {

    }

    private fun connectToChosenDevice(bluetoothDevice: BluetoothDevice) {
        var socket: BluetoothSocket? = null
        try {
            socket =
                bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"))
            try {
                socket.connect()
                ToastManager.showSuccessMessage(
                    context,
                    StringProvider.connectSuccess,
                this
                )
            } catch (e: Exception) {
                try {
                    socket.close()
                    ToastManager.showErrorMessage(
                        context,
                        StringProvider.disconected,
                        this
                    )
                } catch (ee: Exception) {
                    ToastManager.showErrorMessage(
                        context,
                        StringProvider.sthWentWrong,
                        this
                    )
                }
            }
        } catch (e: IOException) {
            ToastManager.showErrorMessage(
                context,
                StringProvider.prblemInConnecting,
                this
            )
        }
        ToastManager.showSuccessMessage(
            context,
            StringProvider.connectedTo + bluetoothDevice.name,
            this
        )
    }


}