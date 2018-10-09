#include <Adafruit_NeoPixel.h>

#define NumOfStrips 1

String buffer = "";
int pot0 = 0;
Adafruit_NeoPixel strips[] = {Adafruit_NeoPixel(39, 2)};

void setup() {
  Serial.begin(9600);
  pinMode(A0, OUTPUT);//POT0
  pot0 = analogRead(A0);
  for(int i = 0; i<NumOfStrips; i++){
    strips[i].begin();
    strips[i].show();
  }
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
  int newPot0 = analogRead(A0);
  if(abs(pot0 - newPot0) < 20){
    Serial.print("P:0:");
    Serial.println(newPot0);
  }
  pulseOne(0,0,0,5);
  pulseOne(0,0,0,0);
}

void handleCommand(String cmd){
  //Handle the command
}

void pulseOne(int stripId, int r, int g, int b){
  for(int i = 0; i<strips[stripId].numPixels(); i++){
    strips[stripId].setPixelColor(i,r,g,b);
    strips[stripId].show();
    delay(25);
  }
}
