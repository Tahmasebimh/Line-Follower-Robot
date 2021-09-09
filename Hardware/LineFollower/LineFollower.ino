#include <L298N.h>
#include <SoftwareSerial.h>
// Motors setup
//Must be PWM pin
const uint8_t ENA = 3;
const uint8_t IN1 = 2;
const uint8_t IN2 = 4;
//Must me PWM pin
const uint8_t ENB = 5;
const uint8_t IN3 = 6;
const uint8_t IN4 = 7;

const uint8_t MINSPEED = 50;
const uint8_t NORMALSPEED = 150;
const uint8_t MAXSPEED = 255;

int delay_time = 0;


//BLT Value With android
const String FORWARD = "forward";
const String BACKWARD = "backward"; 
const String TURNRIGHT = "turnRight";
const String TURNLEFT = "turnLeft";
const String STOP = "stop";

SoftwareSerial Bluetooth(0, 1); // RX | TX
L298N driver(ENA, IN1, IN2, IN3, IN4, ENB); 


void setup() {
  // put your setup code here, to run once:
  Bluetooth.begin(9600);
  Serial.begin(9600);
  Bluetooth.setTimeout(500);
  delay(0);    
}

void loop() {
  // put your main code here, to run repeatedly:

  if (Bluetooth.available()){
    String input = Bluetooth.readString();
    Serial.println(input);
    if(input == FORWARD){
      Serial.println("forward");
        driver.full_stop(delay_time); 
        driver.forward(MAXSPEED, delay_time);
    }else if(input == BACKWARD){
        driver.full_stop(delay_time); 
        driver.backward(MAXSPEED, delay_time);
    }else if(input == TURNRIGHT){
        driver.full_stop(delay_time); 
        driver.turn_right(MAXSPEED, delay_time);
    }else if(input == TURNLEFT){
        driver.full_stop(delay_time); 
        driver.turn_left(MAXSPEED, delay_time);
    }else if(input == STOP){
        driver.full_stop(delay_time); 
    }
    delay(1000);
  }else{
   
  }

  
}
