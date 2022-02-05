#include <L298N.h>
#include <SoftwareSerial.h>
#include <SPI.h>
#include <MFRC522.h>

//blutooth setup
SoftwareSerial Bluetooth(0, 1); // RX | TX

// Motors setup
// Must be PWM pin
//LEFT Driver
const uint8_t ENA = 3;
const uint8_t IN1 = 2;
const uint8_t IN2 = 4;
// Must me PWM pin
//RIGHT Driver
const uint8_t ENB = 5;
const uint8_t IN3 = 6;
const uint8_t IN4 = 7;


const uint8_t MINSPEED = 50;
const uint8_t NORMALSPEED = 70;
const uint8_t MAXSPEED = 110;
uint8_t TURNSPEED = 110;
uint8_t SELECTEDSPEED = NORMALSPEED;
const uint8_t delay_time = 0;

L298N driver(ENA, IN1, IN2, IN3, IN4, ENB, false, MINSPEED); 


//IR Setup They are analog pin number
const uint8_t IR_R = 0;
const uint8_t IR_C = 1;
const uint8_t IR_L = 2;
//If IR vlaue below of 512 -> Its white
//If IR vlaue top of 512 -> Its black
const int irMidRange = 512;
//IR sensor values
int irData[] = {0, 0, 0, 0, 0};
//premit move
enum RunMode  { Move, Stop, TurnLeft, TurnRight};
RunMode runMode = RunMode::Stop;

//RFID Setup
#define RST_PIN         9           // Configurable, see typical pin layout above
#define SS_PIN          10          // Configurable, see typical pin layout above
int block = 2;                      //this is the block number we will write into and then read. 
byte readbackblock[18];             //This array is used for reading out a block.
String readBlockString;             //This is RFID strign when read by RFID module
MFRC522 mfrc522(SS_PIN, RST_PIN);   // Create MFRC522 instance.
MFRC522::MIFARE_Key key;

//TAG command
const int RFID_NO_TAG_READ = 0;
const int RFID_STOP_MOTION = 1;
const int RFID_TURN_LEFT   = 2;
const int RFID_TURN_RIGHT  = 3;
const int RFID_SPEED_UP    = 4;
const int RFID_SPEED_DOWN  = 5;


//BLT Value With android


void setup() {
  Bluetooth.begin(9600);
  Serial.begin(9600);
  Bluetooth.setTimeout(300);

  SPI.begin();        // Init SPI bus
  mfrc522.PCD_Init(); // Init MFRC522 card
  
  for (byte i = 0; i < 6; i++) {
    key.keyByte[i] = 0xFF;
  }
}

void loop() {
  // put your main code here, to run repeatedly:
  if (Bluetooth.available()){
      //This scope will accesable when sth received by bluetooth module
      String input = Bluetooth.readString();
      if(input.startsWith("DIRECTION")){
        if(input.indexOf("FORWARD") != -1){
          driver.forward(SELECTEDSPEED, 0);
        }else if(input.indexOf("LEFT") != -1){
          driver.left(TURNSPEED
          , 0);
        }else if(input.indexOf("RIGHT") != -1){
          driver.right(TURNSPEED, 0);
        }else if(input.indexOf("BACKWARD") != -1){
          driver.backward(SELECTEDSPEED, 0);
        }
        delay(1500);
        driver.stop();  
      }else if(input.startsWith("SPEED")){
          String speedType = input.substring(input.indexOf("_") + 1, input.length());
        if(speedType == "1"){
            SELECTEDSPEED = NORMALSPEED;
        }else if(speedType == "2"){
            SELECTEDSPEED = MAXSPEED;
        }else{
            SELECTEDSPEED = speedType.toInt();
        }
        Serial.print("Speed now: " + String(SELECTEDSPEED));
        TURNSPEED = SELECTEDSPEED + 30;
      }
   }
   
   //RFID Code: 
   if (mfrc522.PICC_IsNewCardPresent()){
      if (mfrc522.PICC_ReadCardSerial()){
          //this scope will accesable when RFID sensor find one RFID card
           readBlockString = "";
           readBlock(block, readbackblock);
           for (int j=0 ; j<16 ; j++)
           {
             if(readbackblock[j] > 0) readBlockString.concat((char)readbackblock[j]);
           }
           handleRFIDTagData(readBlockString.toInt());
           // Halt PICC
           mfrc522.PICC_HaltA();
           // Stop encryption on PCD
           mfrc522.PCD_StopCrypto1();
        }
   }

   //IR Sensors
   readIRSensorsDatas();
   if(runMode == RunMode::Move){
     if(irData[0] == LOW && irData[1] == LOW && irData[3] == LOW && irData[4] == LOW){
        //nothing viewed -> Forward()
        goForward();
      }else if(irData[1] == HIGH){
        goForwardRight();
      }else if(irData[3] == HIGH){
        goForwardLeft();  
      }else if(irData[0] == HIGH && irData[2] == LOW){
        do{
          turnRight();
          readIRSensorsDatas();
        }while(irData[2] == HIGH);  
      }else if(irData[4] == HIGH && irData[2] == LOW){
        do{
          turnLeft();
          readIRSensorsDatas();
        }while(irData[2] == HIGH); 
      }else if(irData[2] == HIGH){
        goForward();
      }else{
        goStop();
      }
  }
  
   //END LOOP
}

void readIRSensorsDatas(){
  irData[0] = analogRead(0) / irMidRange;
  irData[1] = analogRead(1) / irMidRange;
  irData[2] = analogRead(2) / irMidRange;
  irData[3] = analogRead(3) / irMidRange;
  irData[4] = analogRead(4) / irMidRange;
}

//Go forward function
void goForward(){
  
    analogWrite(ENA, SELECTEDSPEED);
    analogWrite(ENB, SELECTEDSPEED);
    
    digitalWrite(IN1, HIGH);
    digitalWrite(IN2, LOW);

    digitalWrite(IN3, HIGH);
    digitalWrite(IN4, LOW);
}

//Go right fuctoin
void goForwardRight(){
  
    analogWrite(ENA, SELECTEDSPEED + 20);
    analogWrite(ENB, SELECTEDSPEED);
    
    digitalWrite(IN1, HIGH);
    digitalWrite(IN2, LOW);

    digitalWrite(IN3, HIGH);
    digitalWrite(IN4, LOW);
    delay(100);
} 
//Go left function
void goForwardLeft(){
  
    analogWrite(ENA, SELECTEDSPEED);
    analogWrite(ENB, SELECTEDSPEED + 20);
    
    digitalWrite(IN1, HIGH);
    digitalWrite(IN2, LOW);

    digitalWrite(IN3, HIGH);
    digitalWrite(IN4, LOW);
    delay(100);
}
//Stop
void goStop(){
  
    analogWrite(ENA, 0);
    analogWrite(ENB, 0);
    
    digitalWrite(IN1, LOW);
    digitalWrite(IN2, LOW);

    digitalWrite(IN3, LOW);
    digitalWrite(IN4, LOW);
}

void turnLeft(){
  
  driver.left(TURNSPEED, 0);
}

void turnRight(){
  
  driver.right(TURNSPEED, 0);
}



int readBlock(int blockNumber, byte arrayAddress[]) 
{
  int largestModulo4Number = blockNumber / 4 * 4;
  int trailerBlock = largestModulo4Number + 3;//determine trailer block for the sector
  byte status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, trailerBlock, &key, &(mfrc522.uid));
 
  if (status != MFRC522::STATUS_OK) {
         return 3;//return "3" as error message
  }
  byte buffersize = 18;//we need to define a variable with the read buffer size, since the MIFARE_Read method below needs a pointer to the variable that contains the size... 
  status = mfrc522.MIFARE_Read(blockNumber, arrayAddress, &buffersize);//&buffersize is a pointer to the buffersize variable; MIFARE_Read requires a pointer instead of just a number
  if (status != MFRC522::STATUS_OK) {
     return 4;//return "4" as error message
  }
}



void handleRFIDTagData(int data){
    //Serial.print("Data is : ");
    String output = "TAG_" + String(data);
    Serial.println(output);
    switch(data){
      case RFID_NO_TAG_READ: 
        //Serial.println("No TAG read");
        break;
      case RFID_STOP_MOTION:
        //Serial.println("Stop TAG");
        runMode = RunMode::Stop;
        break;
      case RFID_TURN_LEFT: 
        //Serial.println("Turn left TAG");
        break;
      case RFID_TURN_RIGHT: 
        //Serial.println("Turn Right TAG");
        break;
      case RFID_SPEED_UP: 
        //Serial.println("Speed up TAG");
        SELECTEDSPEED = MAXSPEED;
        break;
      case RFID_SPEED_DOWN: 
        //Serial.println("Speed down TAG");
        SELECTEDSPEED = NORMALSPEED;
        break;
      default: 
        //Serial.println("Unknown TAG");
        break;      
    }
}
