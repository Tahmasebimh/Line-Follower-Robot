package com.hossein.linefollower.util.bluetooth

import android.bluetooth.BluetoothSocket

object ConnectedThreadHelper {

    var connectedThread: ConnectedThread? = null
        private set
    var bluetoothSocket: BluetoothSocket? = null

    fun initialConnectedThread(bluetoothSocket: BluetoothSocket){
        this.bluetoothSocket = bluetoothSocket
        connectedThread = ConnectedThread(bluetoothSocket)
        connectedThread?.start()
    }

    fun write(message: ByteArray) {
        connectedThread?.write(message)
    }

    fun addOnSubscriber(listener: ConnectedThread.PublishInterface){
        connectedThread?.addOnPublisherListener(listener)
    }


}