package com.hossein.linefollower.presenter.ui.controller

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.*
import com.hossein.linefollower.presenter.calback.SetOnItemClickListener
import com.hossein.linefollower.presenter.customview.textview.GeneralTextView
import com.hossein.linefollower.presenter.ui.controller.view.ChooseBTDeviceBottomSheetView
import com.hossein.linefollower.util.bluetooth.ConnectedThread
import com.hossein.linefollower.util.toast.ToastManager
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class ControllerView(context: Context) : FrameLayout(context) {

    val TAG = "ControllerView>>>"

    private lateinit var conditionTextView: GeneralTextView
    private var mSocket: BluetoothSocket? = null
    private lateinit var bottomSheetView: ChooseBTDeviceBottomSheetView
    private lateinit var bottomSheet: BottomSheetDialog
    private var connectedThread: ConnectedThread? = null

    private lateinit var seekBar: SeekBar

    private lateinit var relativeLayout: RelativeLayout
    private lateinit var leftImageView: ImageView
    private lateinit var rightImageView: ImageView
    private lateinit var topImageView: ImageView
    private lateinit var bottomImageView: ImageView
    private lateinit var stopImageView: ImageView

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
                .gravity(Gravity.TOP)
                .margins(
                    SizeProvider.generalMargin,
                    SizeProvider.generalMargin * 2,
                    SizeProvider.generalMargin,
                    SizeProvider.generalMargin
                )
        )


        seekBar = SeekBar(context)
        seekBar.setBackgroundColor(ColorProvider.darkPrimaryColor)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.d(TAG, "onProgressChanged: value : $p1")
                sendCommand(Command.SPEED.value + "_" + p1)

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
        seekBar.max = 250
        addView(
            seekBar,
            ParamsProvider.Frame.defaultParams()
                .gravity(Gravity.TOP)
                .margins(
                    SizeProvider.generalMargin,
                    SizeProvider.generalMargin * 8,
                    SizeProvider.generalMargin,
                    SizeProvider.generalMargin
                )
        )

        relativeLayout = RelativeLayout(context)
        addView(
            relativeLayout,
            ParamsProvider.Frame.defaultParams()
                .gravity(Gravity.BOTTOM or Gravity.CENTER_VERTICAL)
                .margins(SizeProvider.generalMargin)
        )

        topImageView = ImageView(context)
        topImageView.setBackgroundColor(ColorProvider.accentColor)
        topImageView.id = View.generateViewId()
        topImageView.setImageResource(R.drawable.ic_baseline_arrow_24)
        topImageView.rotation = -90f
        relativeLayout.addView(
            topImageView,
            ParamsProvider.Relative.wrapContent()
//                .aboveOf(stopImageView.id)
                .centerHorizontal()
                .alignParentTop()
        )


        stopImageView = ImageView(context)
        stopImageView.id = View.generateViewId()
        stopImageView.setBackgroundColor(ColorProvider.accentColor)
        stopImageView.setImageResource(R.drawable.ic_baseline_stop_24)
        stopImageView.scaleType = ImageView.ScaleType.CENTER_CROP
        relativeLayout.addView(
            stopImageView,
            ParamsProvider.Relative.wrapContent()
                .below(topImageView.id)
                .centerInParent()
                .margins(SizeProvider.generalMargin)
        )

        leftImageView = ImageView(context)
        leftImageView.setBackgroundColor(ColorProvider.accentColor)

        leftImageView.id = View.generateViewId()
        leftImageView.setImageResource(R.drawable.ic_baseline_arrow_24)
        leftImageView.rotation = 180f
        relativeLayout.addView(
            leftImageView,
            ParamsProvider.Relative.wrapContent()
                .toStartOf(stopImageView.id)
                .centerVertical()
        )


        rightImageView = ImageView(context)
        rightImageView.id = View.generateViewId()
        rightImageView.setBackgroundColor(ColorProvider.accentColor)
        rightImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_24))
        relativeLayout.addView(
            rightImageView,
            ParamsProvider.Relative.wrapContent()
                .toEndOf(stopImageView.id)
                .centerVertical()
        )

        bottomImageView = ImageView(context)
        bottomImageView.setBackgroundColor(ColorProvider.accentColor)
        bottomImageView.setImageResource(R.drawable.ic_baseline_arrow_24)
        bottomImageView.rotation = 90f
        relativeLayout.addView(
            bottomImageView,
            ParamsProvider.Relative.wrapContent()
                .below(stopImageView.id)
                .centerHorizontal()
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


        topImageView.setOnClickListener {
            sendCommand(Command.FORWARD.value())
        }

        leftImageView.setOnClickListener {
            sendCommand(Command.TURNLEFT.value())
        }

        rightImageView.setOnClickListener {
            sendCommand(Command.TURNRIGHT.value())
        }

        bottomImageView.setOnClickListener {
            sendCommand(Command.BACKWARD.value())
        }
        stopImageView.setOnClickListener {
            sendCommand(Command.STOP.value())
        }

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
        val pairedDevice = mBluetoothAdapter.bondedDevices
        if (!mBluetoothAdapter.enable()){
            mBluetoothAdapter.enable()
            Thread.sleep(TimeUnit.SECONDS.toMillis(3))
        }
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
                        connectedThread = ConnectedThread(mSocket)
                        connectedThread!!.start()
                        conditionTextView.text = StringProvider.connectIsuccess
                        conditionTextView.setOnClickListener {
                            Log.d(TAG, "onItemClick: send commnad")
                            sendCommand("Test")
                        }
                    }
                    bottomSheet.dismiss()
                }

            }
        )
        bottomSheet.setContentView(bottomSheetView)
        bottomSheet.show()
    }

    private fun sendCommand(command: String) {
        if (mSocket != null) connectedThread?.write(command.toByteArray())
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
                Log.d(TAG, "connectToChosenDevice: ${e.toString()}")
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
            Log.d(TAG, "connectToChosenDevice: ${e.toString()}")
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