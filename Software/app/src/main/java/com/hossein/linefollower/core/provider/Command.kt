package com.hossein.linefollower.core.provider

enum class Command(val value: String) {
    FORWARD("forward"),
    BACKWARD("backward"),
    TURNRIGHT("turnRight"),
    TURNLEFT("turnLeft"),
    SPEED("SPEED"),
    STOP("stop");

    fun value() : String  = value

}