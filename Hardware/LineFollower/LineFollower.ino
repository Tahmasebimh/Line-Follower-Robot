#include <L298N.h>
#include <SoftwareSerial.h>
#include <SPI.h>
#include <MFRC522.h>

// Motors setup
//Must be PWM pin
const uint8_t ENA = 3;
const uint8_t IN1 = 2;
const uint8_t IN2 = 4;
//Must me PWM pin
const uint8_t ENB = 5;
const uint8_t IN3 = 6;
const uint8_t IN4 = 7;

//IR Setup I should change it to Analog Pin
const uint8_t IR_R = 8;
const uint8_t IR_L = 9;
const uint8_t IR_C = 10;

const uint8_t MINSPEED = 50;
const uint8_t NORMALSPEED = 70;
const uint8_t MAXSPEED = 120;
uint8_t TURNSPEED = NORMALSPEED;
uint8_t SELECTEDSPEED = MINSPEED;
int delay_time = 0;


/*
//RFID Setup
#define RST_PIN         9           // Configurable, see typical pin layout above
#define SS_PIN          10          // Configurable, see typical pin layout above
int block = 2;                      //this is the block number we will write into and then read. 
byte blockcontent[16];              //an array with 16 bytes to be written into one of the 64 card blocks is defined
//byte blockcontent[16] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};  //all zeros. This can be used to delete a block.
byte readbackblock[18];             //This array is used for reading out a block.
String readBlockString;
 

MFRC522 mfrc522(SS_PIN, RST_PIN);   // Create MFRC522 instance.

MFRC522::MIFARE_Key key;
*/

//BLT Value With android
const String FORWARD = "forward";
const String BACKWARD = "backward"; 
const String TURNRIGHT = "turnRight";
const String TURNLEFT = "turnLeft";
const String STOP = "stop";
const String SPEED = "SPEED";

SoftwareSerial Bluetooth(0, 1); // RX | TX
L298N driver(ENA, IN1, IN2, IN3, IN4, ENB, false, MINSPEED); 


void setup() {
  // put your setup code here, to run once:
  Bluetooth.begin(9600);
  Serial.begin(9600);
  Bluetooth.setTimeout(300);
  //pinMode(IR_R, INPUT);
  //pinMode(IR_L, INPUT);

  //SPI.begin();        // Init SPI bus
  //mfrc522.PCD_Init(); // Init MFRC522 card

    // Prepare the key (used both as key A and as key B)
    // using FFFFFFFFFFFFh which is the default at chip delivery from the factory
//  for (byte i = 0; i < 6; i++) {
//    key.keyByte[i] = 0xFF;
//  }
}

void loop() {
  // put your main code here, to run repeatedly:
  /*if (Bluetooth.available()){
      String input = Bluetooth.readString();
      if(input.startsWith(SPEED)){
          SELECTEDSPEED = input.substring(input.indexOf("_") + 1, input.length()).toInt();
          TURNSPEED = SELECTEDSPEED * 3 / 4;
      }else{
          if(input == FORWARD){
            driver.forward(SELECTEDSPEED, delay_time);
          }else if(input == BACKWARD){
            driver.backward(SELECTEDSPEED, delay_time);
          }else if(input == TURNRIGHT){
            driver.right(TURNSPEED, delay_time);
          }else if(input == TURNLEFT){
            driver.left(TURNSPEED, delay_time);
          }else if(input == STOP){
             driver.stop(false, delay_time); 
          }
      }
   }
*/

/*
   //RFID Code: 
   if (mfrc522.PICC_IsNewCardPresent()){
    // Select one of the cards
      if (mfrc522.PICC_ReadCardSerial()){
           readBlockString = "";
           readBlock(block, readbackblock);
           for (int j=0 ; j<16 ; j++)
           {
             //Serial.println(readbackblock[j]);
             if(readbackblock[j] > 0) readBlockString.concat((char)readbackblock[j]);
           }
           //send to phone by blutooth
           Serial.println(readBlockString);
           // Halt PICC
           mfrc522.PICC_HaltA();
           // Stop encryption on PCD
           mfrc522.PCD_StopCrypto1();
        }
        */
   //}

  
  uint8_t irr = digitalRead(IR_R);
  uint8_t irl = digitalRead(IR_L);
  uint8_t irc = digitalRead(IR_C);

    
   if(irr == LOW && irl == LOW){
      driver.forward(NORMALSPEED, delay_time);
    //go Forward 
   }else if(irr == HIGH && irl == LOW){
      do{
        driver.right(MAXSPEED, 0);
        
        irr = digitalRead(IR_R);
        irl = digitalRead(IR_L);
        irc = digitalRead(IR_C);
        }while(irc != HIGH);
      
     //turn right
   }else if(irr == LOW && irl == HIGH){
      do{
          driver.left(MAXSPEED, 0);
          irr = digitalRead(IR_R);
          irl = digitalRead(IR_L);
          irc = digitalRead(IR_C);          
      }while(irc != HIGH);
     //turn left   
   }else if(irr == HIGH && irl == HIGH){
      driver.stop(false, delay_time); 
     //stop 
   }
}

/*
int readBlock(int blockNumber, byte arrayAddress[]) 
{
  int largestModulo4Number = blockNumber / 4 * 4;
  int trailerBlock = largestModulo4Number + 3;//determine trailer block for the sector
  //authentication of the desired block for access
  byte status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, trailerBlock, &key, &(mfrc522.uid));
 
  if (status != MFRC522::STATUS_OK) {
//         Serial.print("PCD_Authenticate() failed (read): ");
//         Serial.println(mfrc522.GetStatusCodeName(status));
         return 3;//return "3" as error message
  }
  //reading a block
  byte buffersize = 18;//we need to define a variable with the read buffer size, since the MIFARE_Read method below needs a pointer to the variable that contains the size... 
  status = mfrc522.MIFARE_Read(blockNumber, arrayAddress, &buffersize);//&buffersize is a pointer to the buffersize variable; MIFARE_Read requires a pointer instead of just a number
  if (status != MFRC522::STATUS_OK) {
//     Serial.print("MIFARE_read() failed: ");
//     Serial.println(mfrc522.GetStatusCodeName(status));
     return 4;//return "4" as error message
  }
//  Serial.println("block was read");
}
*/
