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
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.*
import com.hossein.linefollower.model.MovementCommand
import com.hossein.linefollower.presenter.calback.SetOnItemClickListener
import com.hossein.linefollower.presenter.customview.iconview.CardIconView
import com.hossein.linefollower.presenter.customview.textview.GeneralTextView
import com.hossein.linefollower.presenter.ui.controller.adapter.LogRVAdapter
import com.hossein.linefollower.presenter.ui.controller.view.ChooseBTDeviceBottomSheetView
import com.hossein.linefollower.presenter.ui.controller.view.CustomToolBar
import com.hossein.linefollower.presenter.ui.settings.SettingsActivity
import com.hossein.linefollower.util.bluetooth.ConnectedThreadHelper
import com.hossein.linefollower.util.rfid.RFIDModel
import com.hossein.linefollower.util.toast.ToastManager
import top.defaults.drawabletoolbox.DrawableBuilder
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class ControllerView(context: Context) : FrameLayout(context) {

    private var mSocket: BluetoothSocket? = null
    private lateinit var bottomSheet: BottomSheetDialog
    private lateinit var bottomSheetView: ChooseBTDeviceBottomSheetView
    val TAG = "ControllerView>>>"


    private lateinit var containerLinearLayout: LinearLayout

    private lateinit var customToolBar: CustomToolBar

    private lateinit var listContainerFrameLayout: FrameLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var maskFrameLayout: FrameLayout
    private lateinit var startStopIconView: CardIconView
    var isRobotMoving: Boolean = false

    private lateinit var lineView2: View
    private lateinit var statusButton: GeneralTextView

    private val rvAdapter = LogRVAdapter()


    init {
        initView()
    }

    private fun initView() {

        bottomSheet = BottomSheetDialog(context)

        containerLinearLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(ColorProvider.white)

            customToolBar = CustomToolBar(context, CustomToolBar.ToolBarType.SETTING_TOOLBAR)
            addView(
                customToolBar,
                ParamsProvider.Linear.defaultParams()
            )


            listContainerFrameLayout = FrameLayout(context).apply {
                recyclerView = RecyclerView(context).apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    setPadding(
                        SizeProvider.generalPadding / 2
                    )
                    clipToPadding = false
                    adapter = rvAdapter
                }
                addView(
                    recyclerView,
                    ParamsProvider.Frame.fullScreen()
                )

                maskFrameLayout = FrameLayout(context).apply {
                    startStopIconView = CardIconView(context).apply {
                        iconView.setImageResource(R.drawable.ic_baseline_play_arrow_24)

                        setOnClickListener {
                            ConnectedThreadHelper.bluetoothSocket?.let {
                                if (it.isConnected){
                                    if (isRobotMoving){
                                        ConnectedThreadHelper.write(
                                            MovementCommand.STOP.value.toByteArray()
                                        )
                                        startStopIconView.iconView.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                                        maskFrameLayout.setBackgroundColor(Color.argb(100, 255, 255, 255))
                                    }else{
                                        ConnectedThreadHelper.write(
                                            MovementCommand.START.value.toByteArray()
                                        )
                                        startStopIconView.iconView.setImageResource(R.drawable.ic_baseline_stop_24)
                                        maskFrameLayout.setBackgroundColor(Color.TRANSPARENT)
                                    }
                                    isRobotMoving = !isRobotMoving
                                }
                            }
                        }

                    }
                    addView(
                        startStopIconView,
                        ParamsProvider.Card.get(
                            SizeProvider.dpToPx(54),
                            SizeProvider.dpToPx(54),
                        ).gravity(Gravity.BOTTOM)
                            .margins(
                                SizeProvider.dpToPx(24)
                            )
                    )
                }
                addView(
                    maskFrameLayout,
                    ParamsProvider.Frame.fullScreen()
                )

            }
            addView(
                listContainerFrameLayout,
                ParamsProvider.Linear.availableHeightParams()
            )


            lineView2 = View(context).apply {
                setBackgroundColor(ColorProvider.dividerColor)
            }
            addView(
                lineView2,
                ParamsProvider.Linear.get(
                    ParamsProvider.MATCH,
                    SizeProvider.dpToPx(1)
                )
            )

            statusButton = GeneralTextView(context).apply {
                setPadding(
                    SizeProvider.dpToPx(16)
                )
                gravity = Gravity.CENTER
                setTextColor(ColorProvider.textIconMainColor)
                text = StringProvider.clickToUpdate
                background = DrawableBuilder()
                    .solidColor(ColorProvider.primaryColor)
                    .ripple()
                    .rippleColor(ColorProvider.dividerColor)
                    .cornerRadius(SizeProvider.dpToPx(12))
                    .build()

                setOnClickListener {
                    updateStatus()
                    handleBluetooth()
                }
            }
            addView(
                statusButton,
                ParamsProvider.Linear.defaultParams()
                    .margins(
                        SizeProvider.dpToPx(16),
                        SizeProvider.dpToPx(16),
                        SizeProvider.dpToPx(16),
                        SizeProvider.dpToPx(16),
                    )
            )
            updateStatus()

        }
        addView(
            containerLinearLayout,
            ParamsProvider.Frame.fullScreen()
        )

        customToolBar.settingIcon.setOnClickListener {
            context.startActivity(SettingsActivity.newIntent(context))
        }

    }

    private fun updateStatus() {
        if (mSocket == null){
            customToolBar.titleTextView.text = StringProvider.noConncetionAvailable
        }else{
            customToolBar.titleTextView.text = StringProvider.theConnectionIsAvailable
        }
    }

    private fun handleBluetooth(){
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
                        mSocket?.let { bluetoothSocket ->
                            ConnectedThreadHelper.initialConnectedThread(bluetoothSocket)
                            ConnectedThreadHelper.addOnSubscriber {
                                if (it.startsWith("TAG")){
                                    val model = when(Integer.parseInt(it.split("_")[1].trim())){
                                        RFIDModel.RFID_STOP_MOTION.flag -> RFIDModel.RFID_STOP_MOTION
                                        RFIDModel.RFID_TURN_LEFT.flag -> RFIDModel.RFID_TURN_LEFT
                                        RFIDModel.RFID_TURN_RIGHT.flag -> RFIDModel.RFID_TURN_RIGHT
                                        RFIDModel.RFID_SPEED_UP.flag -> RFIDModel.RFID_SPEED_UP
                                        RFIDModel.RFID_SPEED_DOWN.flag -> RFIDModel.RFID_SPEED_DOWN
                                        else -> RFIDModel.RFID_NO_TAG_READ
                                    }
                                    rvAdapter.data.add(model)
                                    rvAdapter.notifyDataSetChanged()
                                }
                            }
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
        ConnectedThreadHelper.write(command.toByteArray())
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
                customToolBar.titleTextView.text = StringProvider.theConnectionIsAvailable

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