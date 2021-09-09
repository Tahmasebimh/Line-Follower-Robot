package com.hossein.linefollower.core.provider

enum class Command(value: String) {
    FORWARD("forward"),
    BACKWARD("backward"),
    TURNRIGHT("turnRight"),
    TURNLEFT("turnLeft"),
    STOP("stop");


    fun value(): String {
        return value()
    }
}