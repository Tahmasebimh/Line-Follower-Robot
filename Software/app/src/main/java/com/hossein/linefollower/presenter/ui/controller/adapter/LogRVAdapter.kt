package com.hossein.linefollower.presenter.ui.controller.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hossein.linefollower.core.provider.SizeProvider
import com.hossein.linefollower.presenter.ui.controller.view.LogItemView
import com.hossein.linefollower.util.rfid.RFIDModel

class LogRVAdapter: RecyclerView.Adapter<LogRVAdapter.LogRVViewHolder>() {

    class LogRVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(rfidModel: RFIDModel) {
            (itemView as LogItemView).setupView(rfidModel)
        }

    }

    val data = ArrayList<RFIDModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogRVViewHolder {
        return LogRVViewHolder(LogItemView(parent.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(
                    SizeProvider.dpToPx(8),
                    SizeProvider.dpToPx(8),
                    SizeProvider.dpToPx(8),
                    SizeProvider.dpToPx(8)
                )
            }
        })
    }

    override fun onBindViewHolder(holder: LogRVViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}