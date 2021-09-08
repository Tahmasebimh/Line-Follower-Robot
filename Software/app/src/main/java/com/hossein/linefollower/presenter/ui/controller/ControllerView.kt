package com.hossein.linefollower.presenter.ui.controller

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hossein.linefollower.core.provider.ColorProvider
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.core.provider.StringProvider
import com.hossein.linefollower.presenter.calback.SetOnItemClickListener
import com.hossein.linefollower.presenter.customview.textview.GeneralTextView
import com.hossein.linefollower.presenter.ui.controller.view.ChooseBTDeviceBottomSheetView
import com.hossein.linefollower.util.toast.ToastManager
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class ControllerView(context: Context) : FrameLayout(context) {

    val TAG = "ControllerView>>>"

    private lateinit var conditionTextView: GeneralTextView
    private var mSocket: BluetoothSocket? = null
    private lateinit var bottomSheetView: ChooseBTDeviceBottomSheetView
    private lateinit var bottomSheet: BottomSheetDialog

    init {
        initView()
    }

    private fun initView() {

        setBackgroundColor(ColorProvider.white)

        conditionTextView = GeneralTextView(context)
        conditionTextView.gravity = Gravity.CENTER
        addView(
            conditionTextView,
            ParamsProvider.Frame.defaultParams()
                .gravity(Gravity.CENTER)
                .margins(SizeProvider.generalMargin)
        )

        if (mSocket != null){
            if (mSocket!!.isConnected){
                conditionTextView.text = StringProvider.theConnectionIsAvailable
            }
        }else{
            conditionTextView.text = StringProvider.noConncetionAvailable
            conditionTextView.setOnClickListener {
                handleBluetooth()
            }
        }

        bottomSheet = BottomSheetDialog(context)
    }

    fun handleBluetooth(){
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
        if (!mBluetoothAdapter.enable()) mBluetoothAdapter.enable()
        val pairedDevice = mBluetoothAdapter.bondedDevices
        val deviceList = ArrayList<BluetoothDevice>()

        pairedDevice.forEach {
            deviceList.add(it)
        }

        bottomSheetView = ChooseBTDeviceBottomSheetView(
            context,
            deviceList,
            object : SetOnItemClickListener {
                override fun onItemClick(position: Int) {
                    if (connectToChosenDevice(deviceList[position])){
                        //TODO setupController to send command
                        conditionTextView.text = StringProvider.connectIsuccess
                    }
                    bottomSheet.dismiss()
                }

            }
        )
        bottomSheet.setContentView(bottomSheetView)
        bottomSheet.show()
    }

    private fun connectToChosenDevice(bluetoothDevice: BluetoothDevice): Boolean {
        try {
            mSocket =
                bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"))
            try {
                mSocket?.connect()
                ToastManager.showSuccessMessage(
                    context,
                    StringProvider.connectSuccess,
                    this
                )
            } catch (e: Exception) {
                try {
                    mSocket?.close()
                    ToastManager.showErrorMessage(
                        context,
                        StringProvider.disconected,
                        this
                    )
                    return false
                } catch (ee: Exception) {
                    ToastManager.showErrorMessage(
                        context,
                        StringProvider.sthWentWrong,
                        this
                    )
                    return false
                }
            }
        } catch (e: IOException) {
            ToastManager.showErrorMessage(
                context,
                StringProvider.prblemInConnecting,
                this
            )
            return false
        }
        ToastManager.showSuccessMessage(
            context,
            StringProvider.connectedTo + bluetoothDevice.name,
            this
        )
        return true
    }


}