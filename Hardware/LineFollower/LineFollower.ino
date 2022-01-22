#include <L298N.h>
#include <SoftwareSerial.h>
#include <SPI.h>
#include <MFRC522.h>

//Global variable
bool runPermit = true;


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


const uint8_t MINSPEED = 120;
const uint8_t NORMALSPEED = 120;
const uint8_t MAXSPEED = 160;
uint8_t TURNSPEED = 240;
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
uint8_t irr;
uint8_t irl;
uint8_t irc;



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
/*
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  pinMode(IN3, OUTPUT);
  pinMode(IN4, OUTPUT);
*/
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
      //String input = Bluetooth.readString();
      
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

  
   readIRSensorsDatas();
   if(irr == LOW && irl == LOW){
        //go Forward 
      driver.forward(NORMALSPEED, 0);
      //goForward();
   }else if(irr == HIGH && irl == LOW){
         //turn right
      do{
        driver.right(TURNSPEED, 0);
        //goRight();
        readIRSensorsDatas();
        }while(irc != HIGH);      

   }else if(irr == LOW && irl == HIGH){
         //turn left   
      do{
          //goLeft();
          driver.left(TURNSPEED, 0);
          readIRSensorsDatas();          
      }while(irc != HIGH);
   }else if(irr == HIGH && irl == HIGH){
      driver.stop(0);
     //goStop(); 
     //stop 
   }
   //END LOOP
}

//Go forward function
void goForward(){
  
    analogWrite(ENA, NORMALSPEED);
    analogWrite(ENB, NORMALSPEED);
    
    digitalWrite(IN1, HIGH);
    digitalWrite(IN2, LOW);

    digitalWrite(IN3, HIGH);
    digitalWrite(IN4, LOW);
}

//Go right fuctoin
void goRight(){
  
    analogWrite(ENA, MAXSPEED);
    analogWrite(ENB, NORMALSPEED);
    
    digitalWrite(IN1, HIGH);
    digitalWrite(IN2, LOW);

    digitalWrite(IN3, HIGH);
    digitalWrite(IN4, LOW);
} 
//Go left function
void goLeft(){
  
    analogWrite(ENA, NORMALSPEED);
    analogWrite(ENB, MAXSPEED);
    
    digitalWrite(IN1, HIGH);
    digitalWrite(IN2, LOW);

    digitalWrite(IN3, HIGH);
    digitalWrite(IN4, LOW);
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

void readIRSensorsDatas(){
  irr = analogRead(IR_R) / irMidRange;
  irl = analogRead(IR_L) / irMidRange;
  irc = analogRead(IR_C) / irMidRange;
}

void handleRFIDTagData(int data){
    //Serial.print("Data is : ");
    Serial.println(data);
    switch(data){
      case RFID_NO_TAG_READ: 
        //Serial.println("No TAG read");
        break;
      case RFID_STOP_MOTION:
        //Serial.println("Stop TAG");
        break;
      case RFID_TURN_LEFT: 
        //Serial.println("Turn left TAG");
        break;
      case RFID_TURN_RIGHT: 
        //Serial.println("Turn Right TAG");
        break;
      case RFID_SPEED_UP: 
        //Serial.println("Speed up TAG");
        break;
      case RFID_SPEED_DOWN: 
        //Serial.println("Speed down TAG");
        break;
      default: 
        //Serial.println("Unknown TAG");
        break;      
    }
}
