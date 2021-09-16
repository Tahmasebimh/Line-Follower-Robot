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

//IR Setup
const uint8_t IR_R = 8;
const uint8_t IR_L = 9;

const uint8_t MINSPEED = 50;
const uint8_t NORMALSPEED = 150;
const uint8_t MAXSPEED = 255;
const uint8_t SELECTEDSPEED = 150;

int delay_time = 500;


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
  Bluetooth.setTimeout(300);
  pinMode(IR_R, INPUT);
  pinMode(IR_L, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
 

  if (Bluetooth.available()){
    String input = Bluetooth.readString();
    Serial.println(input);
    if(input == FORWARD){
      Serial.println("forward");
        driver.forward(MAXSPEED, delay_time);
    }else if(input == BACKWARD){
        driver.backward(MAXSPEED, delay_time);
    }else if(input == TURNRIGHT){
        driver.turn_right(MAXSPEED, delay_time);
    }else if(input == TURNLEFT){
        driver.turn_left(MAXSPEED, delay_time);
    }else if(input == STOP){
        driver.full_stop(delay_time); 
    }
  }else{
   
  }

  uint8_t irr = digitalRead(IR_R);
  uint8_t irl = digitalRead(IR_L);
  Serial.print("Irr is : ");
  Serial.println(irr);
  Serial.print("Irl is : ");
  Serial.println(irl);

   if(irr == 0 && irl == 0){
      driver.forward(SELECTEDSPEED, delay_time);
    //go Forward 
   }else if(irr == 1 && irl == 0){
      driver.turn_right(SELECTEDSPEED, delay_time);
     //turn right
   }else if(irr == 0 && irl == 1){
      driver.turn_left(SELECTEDSPEED, delay_time);
     //turn left   
   }else if(irr == 1 && irl == 1){
      driver.full_stop(delay_time); 
     //stop 
   }

  
}
