package com.hossein.linefollower.util.toast

import android.content.Context
import android.view.View
import android.widget.Toast
import com.hossein.linefollower.R
import com.hossein.linefollower.core.provider.ColorProvider

class ToastManager() {
    companion object{

        fun showSuccessMessage(context: Context, text: String, view: View){
//            NZMessage.Builder(context)
//                .message(text)
//                .icon(R.drawable.ic_baseline_check_24)
//                .lineColor(ColorProvider.toastSuccessIconColor)
//                .setIconColor(ColorProvider.toastSuccessIconColor)
//                .backgroundColor(ColorProvider.white)
//                .textColor(ColorProvider.primaryTextColor)
//                .autoDismiss(true)
//                .build()
//                .showAsDefault(view)
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }

        fun showErrorMessage(context: Context, text: String, view: View){
//            NZMessage.Builder(context)
//                .message(text)
//                .icon(R.drawable.ic_baseline_close_24)
//                .lineColor(ColorProvider.errorToastColor)
//                .setIconColor(ColorProvider.errorToastColor)
//                .backgroundColor(ColorProvider.white)
//                .textColor(ColorProvider.primaryTextColor)
//                .autoDismiss(true)
//                .build()
//                .showAsDefault(view)
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()

        }
    }
}