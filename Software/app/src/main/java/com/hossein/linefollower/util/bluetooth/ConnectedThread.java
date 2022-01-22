package com.hossein.linefollower.util.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ConnectedThread extends Thread {
    private interface MessageConstants {
        int MESSAGE_READ = 0;
        int MESSAGE_WRITE = 1;
        int MESSAGE_TOAST = 2;
    }

    public interface PublishInterface{
        void onMessage(String message);
    }

    private final ArrayList<PublishInterface> messageListeners = new ArrayList<>();

    private static final String TAG = "MY_APP_DEBUG_TAG";
    private final Handler mHandler;
    private final BluetoothSocket sockettargetModule;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private byte[] mmBuffer;
    private String receiverMessage = "";
    public ConnectedThread(BluetoothSocket bluetoothSocket) {

        sockettargetModule = bluetoothSocket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        mHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == MessageConstants.MESSAGE_READ){
                    byte[] readBuff = (byte[]) msg.obj;
                    String temp = new String(readBuff, 0, msg.arg1);
                    Log.d(TAG, "handleMessage: Temp is : " + temp);
                    receiverMessage += temp;
                    Log.d(TAG, "handleMessage: Message is : " + receiverMessage);
                    broadCastMessage(receiverMessage);
                    receiverMessage = "";
                }
            }
        };
        BluetoothSocket socket;
        try {
            tmpIn = bluetoothSocket.getInputStream();

        } catch (IOException e) {
            Log.e(TAG, "Error occurred when creating input stream", e);
        }
        try {
            tmpOut = bluetoothSocket.getOutputStream();
        } catch (IOException e) {
            Log.e(TAG, "Error occurred when creating output stream", e);
        }
        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    private void broadCastMessage(String receiverMessage) {
        if (!messageListeners.isEmpty()){
            for (PublishInterface publishInterface: messageListeners){
                publishInterface.onMessage(receiverMessage);
            }
        }
    }

    @Override
    public void run() {
        mmBuffer = new byte[1024];
        int numBytes;
        while (true) {
            try {
                if (mmInStream.available() > 0) {
                    try {
                        // Read from the InputStream.
                        numBytes = mmInStream.read(mmBuffer);
                        // Send the obtained bytes to the UI activity.
                        //mHandler = new Handler();
                        Message readMsg = mHandler.obtainMessage(
                                MessageConstants.MESSAGE_READ,
                                numBytes,
                                -1,
                                mmBuffer
                        );
                        readMsg.sendToTarget();
                    } catch (IOException e) {
                        break;
                    }
                }else{
                    SystemClock.sleep(100);
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void write(byte[] bytes) {
        if (bytes.length == 0) {
        }
        try {
            mmOutStream.write(bytes);
            // Share the sent message with the UI activity.
            //mHandler = new Handler();
            Message writtenMsg = mHandler.obtainMessage(
                    MessageConstants.MESSAGE_WRITE, -1, -1, mmBuffer);
            writtenMsg.sendToTarget();
        } catch (IOException e) {
            Log.e(TAG, "Error occurred when sending data", e);

            // Send a failure message back to the activity.
            Message writeErrorMsg =
                    mHandler.obtainMessage(MessageConstants.MESSAGE_TOAST);
            Bundle bundle = new Bundle();
            bundle.putString("toast",
                    "Couldn't send data to the other device");
            writeErrorMsg.setData(bundle);
            mHandler.sendMessage(writeErrorMsg);
        }
    }


    void addOnPublisherListener(PublishInterface publishInterface){
        messageListeners.add(publishInterface);
    }

}
