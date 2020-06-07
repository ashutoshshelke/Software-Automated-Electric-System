//Print on Serial port when pir returns high
//Control led and light bulb from serial port

int led = 13;
int pir = 3;
int relaypin=7;
char sw;

void setup() {
  Serial.begin(9600);
  pinMode(relaypin,OUTPUT);
  digitalWrite(relaypin,HIGH);
  pinMode(led, OUTPUT);
  digitalWrite(led, LOW);
  pinMode(pir, INPUT);
  digitalWrite(pir, LOW);
}

void loop() {
  char ch = Serial.read();
  
  if(ch=='2')
  {
    delay(500);
    sw='1';
  }
  else if(ch=='3')
  {
    delay(500);
    sw='0';
  }

  switch(sw)
  {
    case '0':
    {
      while (Serial.available())
      {
        char ch1 = Serial.read();
        if (ch1 == '1')
        {
          digitalWrite(led, HIGH);
          digitalWrite(relaypin,LOW);
          Serial.println("Led on");
          delay(2000);
        }
        else if (ch1 == '0')
        {
          digitalWrite(led, LOW);
          digitalWrite(relaypin,HIGH);
          Serial.println("Led off");
          delay(2000);
        }
      }
    }
    break;

    case '1':
    { 
      boolean st=digitalRead(pir);
      if (st == HIGH)
      {
        digitalWrite(led, HIGH);
        digitalWrite(relaypin,LOW);
        Serial.println("Led on");
        delay(4000);
      }
      else if (st == LOW)
      {
        digitalWrite(led, LOW);
        digitalWrite(relaypin,HIGH);
        Serial.println("Led off");
        delay(4000);
      }
    }
  }
}


