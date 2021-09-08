package com.hossein.linefollower.presenter.ui.controller.view.adapter

import android.bluetooth.BluetoothDevice
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hossein.linefollower.core.provider.ParamsProvider
import com.hossein.linefollower.presenter.calback.SetOnItemClickListener
import com.hossein.linefollower.presenter.ui.controller.view.ChooseBluetoothDeviceItem

class ChooseBTRVAdapter(
    data: ArrayList<BluetoothDevice>,
    val callback: SetOnItemClickListener
): RecyclerView.Adapter<ChooseBTRVAdapter.ChooseBTRVViewHolder>() {

    class ChooseBTRVViewHolder(itemView: View, val clickListener: SetOnItemClickListener) : RecyclerView.ViewHolder(itemView) {

        fun bind(bluetoothDevice: BluetoothDevice) {
            (itemView as ChooseBluetoothDeviceItem).setupView(bluetoothDevice.name)
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

    private var arrayList: ArrayList<BluetoothDevice> = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseBTRVViewHolder {
        val view = ChooseBluetoothDeviceItem(parent.context)
        val viewL = ParamsProvider.Recycler.defaultParams()
        view.layoutParams = viewL
        return ChooseBTRVViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ChooseBTRVViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

}