#include <AM2321.h>


void setup() {
  Serial.begin(9600);
}

void loop() {
  AM2321 am;
  am.read();
  Serial.print("z:0");
  Serial.print(" A0:");
  Serial.print(am.temperature / 10.0);
  Serial.print(" A1:");
  Serial.println(am.humidity / 10.0);
  delay(1000);
}
