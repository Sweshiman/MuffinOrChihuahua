#include <Adafruit_NeoPixel.h>

#define numOfStrips 28
#define numOfPots 3
#define numOfRings 3

String buffer = "";
int pots[] = {0,0,0};
Adafruit_NeoPixel strips[] = {Adafruit_NeoPixel(6, 28),
                                Adafruit_NeoPixel(6, 29),
                                Adafruit_NeoPixel(9, 2),
                                Adafruit_NeoPixel(5, 3),
                                Adafruit_NeoPixel(9, 4),
                                Adafruit_NeoPixel(8, 5),
                                Adafruit_NeoPixel(20, 6),
                                Adafruit_NeoPixel(8, 7),
                                Adafruit_NeoPixel(9, 8),
                                Adafruit_NeoPixel(7, 9),
                                Adafruit_NeoPixel(9, 10),
                                Adafruit_NeoPixel(8, 11),
                                Adafruit_NeoPixel(8, 12),
                                Adafruit_NeoPixel(20, 13),
                                Adafruit_NeoPixel(8, 14),
                                Adafruit_NeoPixel(11, 15),
                                Adafruit_NeoPixel(8, 16),
                                Adafruit_NeoPixel(11, 17),
                                Adafruit_NeoPixel(8, 18),
                                Adafruit_NeoPixel(8, 19),
                                Adafruit_NeoPixel(11, 20),
                                Adafruit_NeoPixel(8, 21),
                                Adafruit_NeoPixel(11, 22),
                                Adafruit_NeoPixel(8, 23),
                                Adafruit_NeoPixel(9, 24),
                                Adafruit_NeoPixel(9, 25),
                                Adafruit_NeoPixel(9, 26),
                                Adafruit_NeoPixel(9, 27),
                                };

Adafruit_NeoPixel rings[] = {Adafruit_NeoPixel(24, 30),
                                Adafruit_NeoPixel(24, 31),
                                Adafruit_NeoPixel(24, 32),
                                };

void setup() {
  Serial.begin(9600);

  //INIT POTS
  pinMode(A0, OUTPUT);
  pinMode(A1, OUTPUT);
  pinMode(A2, OUTPUT);
  pots[0] = analogRead(A0);
  pots[1] = analogRead(A1);
  pots[2] = analogRead(A2);

  //INIT STRIPS
  for(int i = 0; i<numOfStrips; i++){
    strips[i].begin();
    strips[i].show();
  }

  //INIT RINGS
  for(int i = 0; i<numOfRings; i++){
    rings[i].begin();
    rings[i].show();
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
  /*int newPot0 = analogRead(A0);
  if(abs(pot0 - newPot0) < 20){
    Serial.print("P:0:");
    Serial.println(newPot0);
  }*/
  //TEST STRIPS
  for(int i = 0; i<numOfStrips; i++){
    pulseStrip(i,0,0,255);
    pulseStrip(i,0,0,0);
  }

  //TEST RINGS
  for(int i = 0; i<numOfRings; i++){
    pulseRing(i,0,0,255);
    pulseRing(i,0,0,0);
  }
}

void handleCommand(String cmd){
  //Handle the command
}

void pulseStrip(int stripId, int r, int g, int b){
  for(int i = 0; i<strips[stripId].numPixels(); i++){
    strips[stripId].setPixelColor(i,r,g,b);
    strips[stripId].show();
    delay(25);
  }
}

void pulseRing(int ringId, int r, int g, int b){
  for(int i = 0; i<rings[ringId].numPixels(); i++){
    strips[ringId].setPixelColor(i,r,g,b);
    strips[ringId].show();
    delay(25);
  }
}
