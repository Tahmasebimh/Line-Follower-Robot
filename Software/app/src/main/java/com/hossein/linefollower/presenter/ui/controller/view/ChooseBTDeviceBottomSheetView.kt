package com.hossein.linefollower.presenter.ui.controller.view

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.presenter.calback.SetOnItemClickListener
import com.hossein.linefollower.presenter.ui.controller.view.adapter.ChooseBTRVAdapter

class ChooseBTDeviceBottomSheetView(
    context: Context,
    data: ArrayList<BluetoothDevice>,
    clickListener: SetOnItemClickListener
) : FrameLayout(context) {

    private var recyclerView: RecyclerView = RecyclerView(context)

    init {
        recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        val adapter = ChooseBTRVAdapter(
            data,
            clickListener
        )
        recyclerView.adapter = adapter
        addView(
            recyclerView,
            ParamsProvider.Frame.defaultParams()
        )
    }

}