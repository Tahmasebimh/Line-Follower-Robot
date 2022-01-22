package com.hossein.linefollower.util.rfid

enum class RFIDModel(val flag: Int, val value: String) {
    RFID_NO_TAG_READ(0, "No tag read"),
    RFID_STOP_MOTION(1, "Stop motion"),
    RFID_TURN_LEFT(2, "Turn left"),
    RFID_TURN_RIGHT(3, "Turn right"),
    RFID_SPEED_UP(4, "Speed up"),
    RFID_SPEED_DOWN(5, "Speed down")
}