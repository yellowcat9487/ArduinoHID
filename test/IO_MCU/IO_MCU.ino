#define BUTTON_1 2
#define BUTTON_2 3
#define BUTTON_3 4

byte lastData = 0;

void setup() {
  pinMode(BUTTON_1, INPUT_PULLUP);
  pinMode(BUTTON_2, INPUT_PULLUP);
  pinMode(BUTTON_3, INPUT_PULLUP);

  Serial.begin(115200);
}

void loop() {
  byte data = 0;

  if (!digitalRead(BUTTON_1)) data |= 0b00000001;
  if (!digitalRead(BUTTON_2)) data |= 0b00000010;
  if (!digitalRead(BUTTON_3)) data |= 0b00000100;

  if (data != lastData) {
    Serial.write(data);
    lastData = data;
  }
}
