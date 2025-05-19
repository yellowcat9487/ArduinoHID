#include <HID-Project.h>

byte lastData = 0;

void setup() {
  Gamepad.begin();
  Serial.begin(115200);// USB
  Serial1.begin(115200);// UART (328P)
}

void loop() {
  if (Serial1.available()) {
    byte data = Serial1.read();
    if(data != lastData){
      if(data & 0b00000001) Gamepad.press(1);
      else Gamepad.release(1);

      if(data & 0b00000010) Gamepad.press(2);
      else Gamepad.release(2);

      if(data & 0b00000100) Gamepad.press(3);
      else Gamepad.release(3);

      Gamepad.write();
      lastData = data;
    }
  }
}
