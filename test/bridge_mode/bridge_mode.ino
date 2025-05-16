void setup() {
  Serial.begin(115200);     // USB
  Serial1.begin(115200);    // UART (328P)
}

void loop() {
  if (Serial.available()) Serial1.write(Serial.read());
  if (Serial1.available()) Serial.write(Serial1.read());
}