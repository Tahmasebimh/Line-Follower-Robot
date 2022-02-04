package com.hossein.linefollower.model

data class Speed(
    val title: String,
    val value: String
)
//default value between app and arduino
enum class SpeedCommand(val speed: Speed){
    FIRST_SPEED(Speed("سرعت 1", "SPEED_1")),
    SECOND_SPEED(Speed("سرعت 2", "SPEED_2"));
}

enum class DirectionCommand(val value: String){
    FORWARD("DIRECTION_FORWARD"),
    RIGHT("DIRECTION_RIGHT"),
    LEFT("DIRECTION_LEFT"),
    BACKWARD("DIRECTION_BACKWARD");
}
