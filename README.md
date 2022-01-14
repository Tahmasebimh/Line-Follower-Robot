RFID Document/* cspell:disable-file */
/* webkit printing magic: print all background colors */
html {
-webkit-print-color-adjust: exact;
}
* {
box-sizing: border-box;
-webkit-print-color-adjust: exact;
}

html,
body {
margin: 0;
padding: 0;
}
@media only screen {
body {
margin: 2em auto;
max-width: 900px;
color: rgb(55, 53, 47);
}
}

body {
line-height: 1.5;
white-space: pre-wrap;
}

a,
a.visited {
color: inherit;
text-decoration: underline;
}

.pdf-relative-link-path {
font-size: 80%;
color: #444;
}

h1,
h2,
h3 {
letter-spacing: -0.01em;
line-height: 1.2;
font-weight: 600;
margin-bottom: 0;
}

.page-title {
font-size: 2.5rem;
font-weight: 700;
margin-top: 0;
margin-bottom: 0.75em;
}

h1 {
font-size: 1.875rem;
margin-top: 1.875rem;
}

h2 {
font-size: 1.5rem;
margin-top: 1.5rem;
}

h3 {
font-size: 1.25rem;
margin-top: 1.25rem;
}

.source {
border: 1px solid #ddd;
border-radius: 3px;
padding: 1.5em;
word-break: break-all;
}

.callout {
border-radius: 3px;
padding: 1rem;
}

figure {
margin: 1.25em 0;
page-break-inside: avoid;
}

figcaption {
opacity: 0.5;
font-size: 85%;
margin-top: 0.5em;
}

mark {
background-color: transparent;
}

.indented {
padding-left: 1.5em;
}

hr {
background: transparent;
display: block;
width: 100%;
height: 1px;
visibility: visible;
border: none;
border-bottom: 1px solid rgba(55, 53, 47, 0.09);
}

img {
max-width: 100%;
}

@media only print {
img {
max-height: 100vh;
object-fit: contain;
}
}

@page {
margin: 1in;
}

.collection-content {
font-size: 0.875rem;
}

.column-list {
display: flex;
justify-content: space-between;
}

.column {
padding: 0 1em;
}

.column:first-child {
padding-left: 0;
}

.column:last-child {
padding-right: 0;
}

.table_of_contents-item {
display: block;
font-size: 0.875rem;
line-height: 1.3;
padding: 0.125rem;
}

.table_of_contents-indent-1 {
margin-left: 1.5rem;
}

.table_of_contents-indent-2 {
margin-left: 3rem;
}

.table_of_contents-indent-3 {
margin-left: 4.5rem;
}

.table_of_contents-link {
text-decoration: none;
opacity: 0.7;
border-bottom: 1px solid rgba(55, 53, 47, 0.18);
}

table,
th,
td {
border: 1px solid rgba(55, 53, 47, 0.09);
border-collapse: collapse;
}

table {
border-left: none;
border-right: none;
}

th,
td {
font-weight: normal;
padding: 0.25em 0.5em;
line-height: 1.5;
min-height: 1.5em;
text-align: left;
}

th {
color: rgba(55, 53, 47, 0.6);
}

ol,
ul {
margin: 0;
margin-block-start: 0.6em;
margin-block-end: 0.6em;
}

li > ol:first-child,
li > ul:first-child {
margin-block-start: 0.6em;
}

ul > li {
list-style: disc;
}

ul.to-do-list {
text-indent: -1.7em;
}

ul.to-do-list > li {
list-style: none;
}

.to-do-children-checked {
text-decoration: line-through;
opacity: 0.375;
}

ul.toggle > li {
list-style: none;
}

ul {
padding-inline-start: 1.7em;
}

ul > li {
padding-left: 0.1em;
}

ol {
padding-inline-start: 1.6em;
}

ol > li {
padding-left: 0.2em;
}

.mono ol {
padding-inline-start: 2em;
}

.mono ol > li {
text-indent: -0.4em;
}

.toggle {
padding-inline-start: 0em;
list-style-type: none;
}

/* Indent toggle children */
.toggle > li > details {
padding-left: 1.7em;
}

.toggle > li > details > summary {
margin-left: -1.1em;
}

.selected-value {
display: inline-block;
padding: 0 0.5em;
background: rgba(206, 205, 202, 0.5);
border-radius: 3px;
margin-right: 0.5em;
margin-top: 0.3em;
margin-bottom: 0.3em;
white-space: nowrap;
}

.collection-title {
display: inline-block;
margin-right: 1em;
}

.simple-table {
margin-top: 1em;
font-size: 0.875rem;
}

.simple-table-header {
background: rgb(247, 246, 243);
color: black;
font-weight: 500;
}

time {
opacity: 0.5;
}

.icon {
display: inline-block;
max-width: 1.2em;
max-height: 1.2em;
text-decoration: none;
vertical-align: text-bottom;
margin-right: 0.5em;
}

img.icon {
border-radius: 3px;
}

.user-icon {
width: 1.5em;
height: 1.5em;
border-radius: 100%;
margin-right: 0.5rem;
}

.user-icon-inner {
font-size: 0.8em;
}

.text-icon {
border: 1px solid #000;
text-align: center;
}

.page-cover-image {
display: block;
object-fit: cover;
width: 100%;
max-height: 30vh;
}

.page-header-icon {
font-size: 3rem;
margin-bottom: 1rem;
}

.page-header-icon-with-cover {
margin-top: -0.72em;
margin-left: 0.07em;
}

.page-header-icon img {
border-radius: 3px;
}

.link-to-page {
margin: 1em 0;
padding: 0;
border: none;
font-weight: 500;
}

p > .user {
opacity: 0.5;
}

td > .user,
td > time {
white-space: nowrap;
}

input[type="checkbox"] {
transform: scale(1.5);
margin-right: 0.6em;
vertical-align: middle;
}

p {
margin-top: 0.5em;
margin-bottom: 0.5em;
}

.image {
border: none;
margin: 1.5em 0;
padding: 0;
border-radius: 0;
text-align: center;
}

.code,
code {
background: rgba(135, 131, 120, 0.15);
border-radius: 3px;
padding: 0.2em 0.4em;
border-radius: 3px;
font-size: 85%;
tab-size: 2;
}

code {
color: #eb5757;
}

.code {
padding: 1.5em 1em;
}

.code-wrap {
white-space: pre-wrap;
word-break: break-all;
}

.code > code {
background: none;
padding: 0;
font-size: 100%;
color: inherit;
}

blockquote {
font-size: 1.25em;
margin: 1em 0;
padding-left: 1em;
border-left: 3px solid rgb(55, 53, 47);
}

.bookmark {
text-decoration: none;
max-height: 8em;
padding: 0;
display: flex;
width: 100%;
align-items: stretch;
}

.bookmark-title {
font-size: 0.85em;
overflow: hidden;
text-overflow: ellipsis;
height: 1.75em;
white-space: nowrap;
}

.bookmark-text {
display: flex;
flex-direction: column;
}

.bookmark-info {
flex: 4 1 180px;
padding: 12px 14px 14px;
display: flex;
flex-direction: column;
justify-content: space-between;
}

.bookmark-image {
width: 33%;
flex: 1 1 180px;
display: block;
position: relative;
object-fit: cover;
border-radius: 1px;
}

.bookmark-description {
color: rgba(55, 53, 47, 0.6);
font-size: 0.75em;
overflow: hidden;
max-height: 4.5em;
word-break: break-word;
}

.bookmark-href {
font-size: 0.75em;
margin-top: 0.25em;
}

.sans { font-family: ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol"; }
.code { font-family: "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace; }
.serif { font-family: Lyon-Text, Georgia, ui-serif, serif; }
.mono { font-family: iawriter-mono, Nitti, Menlo, Courier, monospace; }
.pdf .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK JP'; }
.pdf:lang(zh-CN) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK SC'; }
.pdf:lang(zh-TW) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK TC'; }
.pdf:lang(ko-KR) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, "Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji", "Segoe UI Symbol", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK KR'; }
.pdf .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK JP'; }
.pdf:lang(zh-CN) .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK SC'; }
.pdf:lang(zh-TW) .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK TC'; }
.pdf:lang(ko-KR) .code { font-family: Source Code Pro, "SFMono-Regular", Menlo, Consolas, "PT Mono", "Liberation Mono", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK KR'; }
.pdf .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK JP'; }
.pdf:lang(zh-CN) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK SC'; }
.pdf:lang(zh-TW) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK TC'; }
.pdf:lang(ko-KR) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK KR'; }
.pdf .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK JP'; }
.pdf:lang(zh-CN) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK SC'; }
.pdf:lang(zh-TW) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK TC'; }
.pdf:lang(ko-KR) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK KR'; }
.highlight-default {
color: rgba(55, 53, 47, 1);
}
.highlight-gray {
color: rgba(120, 119, 116, 1);
fill: rgba(145, 145, 142, 1);
}
.highlight-brown {
color: rgba(159, 107, 83, 1);
fill: rgba(187, 132, 108, 1);
}
.highlight-orange {
color: rgba(217, 115, 13, 1);
fill: rgba(215, 129, 58, 1);
}
.highlight-yellow {
color: rgba(203, 145, 47, 1);
fill: rgba(203, 148, 51, 1);
}
.highlight-teal {
color: rgba(68, 131, 97, 1);
fill: rgba(108, 155, 125, 1);
}
.highlight-blue {
color: rgba(51, 126, 169, 1);
fill: rgba(91, 151, 189, 1);
}
.highlight-purple {
color: rgba(144, 101, 176, 1);
fill: rgba(167, 130, 195, 1);
}
.highlight-pink {
color: rgba(193, 76, 138, 1);
fill: rgba(205, 116, 159, 1);
}
.highlight-red {
color: rgba(212, 76, 71, 1);
fill: rgba(225, 111, 100, 1);
}
.highlight-gray_background {
background: rgba(241, 241, 239, 1);
}
.highlight-brown_background {
background: rgba(244, 238, 238, 1);
}
.highlight-orange_background {
background: rgba(251, 236, 221, 1);
}
.highlight-yellow_background {
background: rgba(251, 243, 219, 1);
}
.highlight-teal_background {
background: rgba(237, 243, 236, 1);
}
.highlight-blue_background {
background: rgba(231, 243, 248, 1);
}
.highlight-purple_background {
background: rgba(244, 240, 247, 0.8);
}
.highlight-pink_background {
background: rgba(249, 238, 243, 0.8);
}
.highlight-red_background {
background: rgba(253, 235, 236, 1);
}
.block-color-default {
color: inherit;
fill: inherit;
}
.block-color-gray {
color: rgba(120, 119, 116, 1);
fill: rgba(145, 145, 142, 1);
}
.block-color-brown {
color: rgba(159, 107, 83, 1);
fill: rgba(187, 132, 108, 1);
}
.block-color-orange {
color: rgba(217, 115, 13, 1);
fill: rgba(215, 129, 58, 1);
}
.block-color-yellow {
color: rgba(203, 145, 47, 1);
fill: rgba(203, 148, 51, 1);
}
.block-color-teal {
color: rgba(68, 131, 97, 1);
fill: rgba(108, 155, 125, 1);
}
.block-color-blue {
color: rgba(51, 126, 169, 1);
fill: rgba(91, 151, 189, 1);
}
.block-color-purple {
color: rgba(144, 101, 176, 1);
fill: rgba(167, 130, 195, 1);
}
.block-color-pink {
color: rgba(193, 76, 138, 1);
fill: rgba(205, 116, 159, 1);
}
.block-color-red {
color: rgba(212, 76, 71, 1);
fill: rgba(225, 111, 100, 1);
}
.block-color-gray_background {
background: rgba(241, 241, 239, 1);
}
.block-color-brown_background {
background: rgba(244, 238, 238, 1);
}
.block-color-orange_background {
background: rgba(251, 236, 221, 1);
}
.block-color-yellow_background {
background: rgba(251, 243, 219, 1);
}
.block-color-teal_background {
background: rgba(237, 243, 236, 1);
}
.block-color-blue_background {
background: rgba(231, 243, 248, 1);
}
.block-color-purple_background {
background: rgba(244, 240, 247, 0.8);
}
.block-color-pink_background {
background: rgba(249, 238, 243, 0.8);
}
.block-color-red_background {
background: rgba(253, 235, 236, 1);
}
.select-value-color-pink { background-color: rgba(245, 224, 233, 1); }
.select-value-color-purple { background-color: rgba(232, 222, 238, 1); }
.select-value-color-green { background-color: rgba(219, 237, 219, 1); }
.select-value-color-gray { background-color: rgba(227, 226, 224, 1); }
.select-value-color-orange { background-color: rgba(250, 222, 201, 1); }
.select-value-color-brown { background-color: rgba(238, 224, 218, 1); }
.select-value-color-red { background-color: rgba(255, 226, 221, 1); }
.select-value-color-yellow { background-color: rgba(253, 236, 200, 1); }
.select-value-color-blue { background-color: rgba(211, 229, 239, 1); }

.checkbox {
display: inline-flex;
vertical-align: text-bottom;
width: 16;
height: 16;
background-size: 16px;
margin-left: 2px;
margin-right: 5px;
}

.checkbox-on {
background-image: url("data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%0A%3Crect%20width%3D%2216%22%20height%3D%2216%22%20fill%3D%22%2358A9D7%22%2F%3E%0A%3Cpath%20d%3D%22M6.71429%2012.2852L14%204.9995L12.7143%203.71436L6.71429%209.71378L3.28571%206.2831L2%207.57092L6.71429%2012.2852Z%22%20fill%3D%22white%22%2F%3E%0A%3C%2Fsvg%3E");
}

.checkbox-off {
background-image: url("data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%0A%3Crect%20x%3D%220.75%22%20y%3D%220.75%22%20width%3D%2214.5%22%20height%3D%2214.5%22%20fill%3D%22white%22%20stroke%3D%22%2336352F%22%20stroke-width%3D%221.5%22%2F%3E%0A%3C%2Fsvg%3E");
}

[🏣RFID DocumentCreated@January 14, 2022 1:33 PMTagsBSC ProjectRFID:Pin document: RFID PinUno PinColorRST9RQNoneSDA10MOSI11MISO12SCK13 VCC3.3VRedGNDGNDBlack|IMG|](null)Initial Function: `#include &lt;SPI.h&gt;
#include &lt;MFRC522.h&gt;
#define RST_PIN         9           // Configurable, see typical pin layout above
#define SS_PIN          10          // Configurable, see typical pin layout above
MFRC522 mfrc522(SS_PIN, RST_PIN);   // Create MFRC522 instance.

MFRC522::MIFARE_Key key; //create a MIFARE_Key struct named &#x27;key&#x27;, which will hold the card information
void setup() 
{
  Serial.begin(9600); // Initialize serial communications with the PC
    SPI.begin();        // Init SPI bus
    mfrc522.PCD_Init(); // Init MFRC522 card

    // Prepare the key (used both as key A and as key B)
    // using FFFFFFFFFFFFh which is the default at chip delivery from the factory
    for (byte i = 0; i &lt; 6; i++) {
        key.keyByte[i] = 0xFF;
    }
}`Reading Function: `int readBlock(int blockNumber, byte arrayAddress[]) 
{
  int largestModulo4Number=blockNumber/4*4;
  int trailerBlock=largestModulo4Number+3;//determine trailer block for the sector
 
  //authentication of the desired block for access
  byte status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, trailerBlock, &amp;key, &amp;(mfrc522.uid));
 
  if (status != MFRC522::STATUS_OK) {
         Serial.print(&quot;PCD_Authenticate() failed (read): &quot;);
         Serial.println(mfrc522.GetStatusCodeName(status));
         return 3;//return &quot;3&quot; as error message
  }
 
//reading a block
byte buffersize = 18;//we need to define a variable with the read buffer size, since the MIFARE_Read method below needs a pointer to the variable that contains the size... 
status = mfrc522.MIFARE_Read(blockNumber, arrayAddress, &amp;buffersize);//&amp;buffersize is a pointer to the buffersize variable; MIFARE_Read requires a pointer instead of just a number
  if (status != MFRC522::STATUS_OK) {
          Serial.print(&quot;MIFARE_read() failed: &quot;);
          Serial.println(mfrc522.GetStatusCodeName(status));
          return 4;//return &quot;4&quot; as error message
  }
  Serial.println(&quot;block was read&quot;);
}`Block number is number between 0 to 15(In this project we use number 2How to use top method: `readBlock(block, readbackblock);
   Serial.print(&quot;read block: &quot;);
   for (int j=0 ; j&lt;16 ; j++)
   {
     Serial.write (readbackblock[j]);
   }
   Serial.println(&quot;&quot;);`readBackBlock is a byte array: `byte readbackblock[18];`Writing Function: `int writeBlock(int blockNumber, byte arrayAddress[]) 
{
  //this makes sure that we only write into data blocks. Every 4th block is a trailer block for the access/security info.
  int largestModulo4Number=blockNumber/4*4;
  int trailerBlock=largestModulo4Number+3;//determine trailer block for the sector
  if (blockNumber &gt; 2 &amp;&amp; (blockNumber+1)%4 == 0){Serial.print(blockNumber);Serial.println(&quot; is a trailer block:&quot;);return 2;}
  Serial.print(blockNumber);
  Serial.println(&quot; is a data block:&quot;);
   
  //authentication of the desired block for access
  byte status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, trailerBlock, &amp;key, &amp;(mfrc522.uid));
  if (status != MFRC522::STATUS_OK) {
         Serial.print(&quot;PCD_Authenticate() failed: &quot;);
         Serial.println(mfrc522.GetStatusCodeName(status));
         return 3;//return &quot;3&quot; as error message
  }
   
  //writing the block 
  status = mfrc522.MIFARE_Write(blockNumber, arrayAddress, 16);
  //status = mfrc522.MIFARE_Write(9, value1Block, 16);
  if (status != MFRC522::STATUS_OK) {
           Serial.print(&quot;MIFARE_Write() failed: &quot;);
           Serial.println(mfrc522.GetStatusCodeName(status));
           return 4;//return &quot;4&quot; as error message
  }
  Serial.println(&quot;block was written&quot;);
}`array address is a content that we want to write on the block: `byte blockcontent[16] = {&quot;SPEED_UP&quot;};`if We want to delete the block we have to use this: `byte blockcontent[16] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};`Below code show us how to read on a block: `writeBlock(block, blockcontent);`Tag Commands: Tags CommandDescription0No Tag Read1Stop Motion2Turn Left3Turn Right4Speed Up5Speed Down`const uint8_t RFID_NO_TAG_READ = 0;
const uint8_t RFID_STOP_MOTION = 1;
const uint8_t RFID_TURN_LEFT = 2;
const uint8_t RFID_TURN_RIGHT = 3;
const uint8_t RFID_SPEED_UP = 4;
const uint8_t RFID_SPEED_DOWN = 5;`
