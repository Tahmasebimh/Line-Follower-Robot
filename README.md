# Line Follower

Created: January 14, 2022 1:33 PM
Tags: BSC Project

# RFID:

## Pin document:

| RFID Pin | Uno Pin | Color |
| --- | --- | --- |
| RST | 9 |  |
| RQ | None |  |
| SDA | 10 |  |
| MOSI | 11 |  |
| MISO | 12 |  |
| SCK | 13 |   |
| VCC | 3.3V | Red |
| GND | GND | Black |

![RFID_Diagram.png](RFID%20Document%2063df1a68774b4e4f95ee52654ae65d3a/RFID_Diagram.png)

### Initial Function:

```c
#include <SPI.h>
#include <MFRC522.h>
#define RST_PIN         9           // Configurable, see typical pin layout above
#define SS_PIN          10          // Configurable, see typical pin layout above
MFRC522 mfrc522(SS_PIN, RST_PIN);   // Create MFRC522 instance.

MFRC522::MIFARE_Key key; //create a MIFARE_Key struct named 'key', which will hold the card information
void setup() 
{
  Serial.begin(9600); // Initialize serial communications with the PC
    SPI.begin();        // Init SPI bus
    mfrc522.PCD_Init(); // Init MFRC522 card

    // Prepare the key (used both as key A and as key B)
    // using FFFFFFFFFFFFh which is the default at chip delivery from the factory
    for (byte i = 0; i < 6; i++) {
        key.keyByte[i] = 0xFF;
    }
}
```

### Reading Function:

```c
int readBlock(int blockNumber, byte arrayAddress[]) 
{
  int largestModulo4Number=blockNumber/4*4;
  int trailerBlock=largestModulo4Number+3;//determine trailer block for the sector
 
  //authentication of the desired block for access
  byte status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, trailerBlock, &key, &(mfrc522.uid));
 
  if (status != MFRC522::STATUS_OK) {
         Serial.print("PCD_Authenticate() failed (read): ");
         Serial.println(mfrc522.GetStatusCodeName(status));
         return 3;//return "3" as error message
  }
 
//reading a block
byte buffersize = 18;//we need to define a variable with the read buffer size, since the MIFARE_Read method below needs a pointer to the variable that contains the size... 
status = mfrc522.MIFARE_Read(blockNumber, arrayAddress, &buffersize);//&buffersize is a pointer to the buffersize variable; MIFARE_Read requires a pointer instead of just a number
  if (status != MFRC522::STATUS_OK) {
          Serial.print("MIFARE_read() failed: ");
          Serial.println(mfrc522.GetStatusCodeName(status));
          return 4;//return "4" as error message
  }
  Serial.println("block was read");
}
```

Block number is number between 0 to 15(In this project we use number 2

How to use top method: 

```c
readBlock(block, readbackblock);
   Serial.print("read block: ");
   for (int j=0 ; j<16 ; j++)
   {
     Serial.write (readbackblock[j]);
   }
   Serial.println("");
```

readBackBlock is a byte array: 

```c
byte readbackblock[18];
```

### Writing Function:

```c
int writeBlock(int blockNumber, byte arrayAddress[]) 
{
  //this makes sure that we only write into data blocks. Every 4th block is a trailer block for the access/security info.
  int largestModulo4Number=blockNumber/4*4;
  int trailerBlock=largestModulo4Number+3;//determine trailer block for the sector
  if (blockNumber > 2 && (blockNumber+1)%4 == 0){Serial.print(blockNumber);Serial.println(" is a trailer block:");return 2;}
  Serial.print(blockNumber);
  Serial.println(" is a data block:");
   
  //authentication of the desired block for access
  byte status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, trailerBlock, &key, &(mfrc522.uid));
  if (status != MFRC522::STATUS_OK) {
         Serial.print("PCD_Authenticate() failed: ");
         Serial.println(mfrc522.GetStatusCodeName(status));
         return 3;//return "3" as error message
  }
   
  //writing the block 
  status = mfrc522.MIFARE_Write(blockNumber, arrayAddress, 16);
  //status = mfrc522.MIFARE_Write(9, value1Block, 16);
  if (status != MFRC522::STATUS_OK) {
           Serial.print("MIFARE_Write() failed: ");
           Serial.println(mfrc522.GetStatusCodeName(status));
           return 4;//return "4" as error message
  }
  Serial.println("block was written");
}
```

array address is a content that we want to write on the block: 

```c
byte blockcontent[16] = {"SPEED_UP"};
```

if We want to delete the block we have to use this: 

```c
byte blockcontent[16] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
```

Below code show us how to read on a block: 

```c
writeBlock(block, blockcontent);
```

### Tag Commands:

| Tags Command | Description |
| --- | --- |
| 0 | No Tag Read |
| 1 | Stop Motion |
| 2 | Turn Left |
| 3 | Turn Right |
| 4 | Speed Up |
| 5 | Speed Down |

```c
const uint8_t RFID_NO_TAG_READ = 0;
const uint8_t RFID_STOP_MOTION = 1;
const uint8_t RFID_TURN_LEFT = 2;
const uint8_t RFID_TURN_RIGHT = 3;
const uint8_t RFID_SPEED_UP = 4;
const uint8_t RFID_SPEED_DOWN = 5;
```
