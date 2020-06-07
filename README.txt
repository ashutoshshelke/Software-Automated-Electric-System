Title: Software Automated Electric System
Domain: Internet of Things (IOT)
Language: Java, C
Description: The aim of this project was to create a system that will automate the
electric switches and control them from a remote location. The system was also able
to detect the presence of a human and switch the lights accordingly with the help of a
Passive Infrared (PIR) sensor and Arduino Uno. The system was developed in Java SE
8 using RXTX interfacing libraries for serial programming and Java AWT for front-end
development.

REFER Arch.jpg for Architecture and connections.

Requirements : 1. Java 8 SE installed.
2. Arduino UNO
3. Arduino IDE
4. PIR Sensor
5. Relay Circuit
6. Bulb and holder
7. Connecting Wires
8. USB A-B Cable 

Steps :

Arduino
1. Burn the pirsensor.ino code in your arduino uno.
2. Connections :
Connect Arduino to PC using USB A-B Cable. Connection has to be wired. 
 
PIR sensor - Arduino UNO
GND 		GND
VCC		VCC
OUTPUT		PIN 3

3. RELAY Circuit
RELAY - 	Arduino UNO
GND		GND
VCC		VCC
INPUT		PIN 7

4. Connect bulb on the other side of relay circuit.

System
1. Copy RXTXcomm.jar in jdk/jre/lib/ext directory of your java program files.
2. Copy rxtxParallel.dll and rxtxSerial.dll in jdk/jre/bin of your java program files.
3. Use SerialTest.java to run the project. (SecondDemo.java should be in the same directory as of SerialTest.java)
4. Use username : ashutosh 
password : 12345

Cheers! 

