#include <Adafruit_NeoPixel.h>

Adafruit_NeoPixel strip = Adafruit_NeoPixel(38, 2);
String buffer = "";
int pot0 = 0;

void setup() {
  Serial.begin(9600);
  pinMode(A0, OUTPUT);//POT0
  pot0 = analogRead(A0);
  strip.begin();
  strip.show();
}

void loop() {
  //READ ANY COMMANDS FROM THE PI
  while(Serial.available() > 0){
    char newChar = Serial.read();
    buffer += newChar;
    if(newChar == '\n'){
      handleCommand(buffer);
      buffer = "";
    }
  }

  //SEND NEW VALUES IF THEY ARE NEW
  newPot0 = analogRead(A0);
  if(abs(pot0 - newPot0) < 20){
    Serial.print("P:0:");
    Serial.println(newPot0);
  }
}

void handleCommand(String cmd){
  //Handle the command
}
