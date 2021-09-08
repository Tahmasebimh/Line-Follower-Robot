#include <SoftwareSerial.h>

SoftwareSerial Bluetooth(0, 1); // RX | TX
void setup() {
  // put your setup code here, to run once:
Bluetooth.begin(9600);
Serial.begin(9600);
Bluetooth.setTimeout(500);
    pinMode(4, OUTPUT);
    pinMode(5, OUTPUT);
    pinMode(6, OUTPUT);
    pinMode(7, OUTPUT);
    pinMode(8, OUTPUT);
    pinMode(9, OUTPUT);
    
    delay(1000);
    
}

void loop() {
  // put your main code here, to run repeatedly:

  if (Bluetooth.available()){
    String input = Bluetooth.readString();
    delay(1000);
    

  }

  Serial.println("Hellow###");
  Bluetooth.println("Hellow##");
  delay(5000);

}
