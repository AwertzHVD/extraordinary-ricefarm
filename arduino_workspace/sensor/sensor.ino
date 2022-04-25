// the setup routine runs once when you press reset:
void setup() {
// initialize serial communication at 9600 bits per second:
Serial.begin(9600);
}



int z=0;

 

// the loop routine runs over and over again forever:
void loop() {
// read the input on analog pin 0:
z=z+1;
int sensorValue0 = analogRead(A0);
int sensorValue1 = analogRead(A1);
Serial.print("z:");
Serial.print(z);
Serial.print(" A0:");
Serial.print(sensorValue0);
Serial.print(" A1:");
Serial.println(sensorValue1);

// print out the value you read:

delay(250); // delay in between reads for stability
}
