﻿# AndroidSensorChecker

This is a minimal sample app to quickly check which sensors are accessible on an Android device.  
It uses the Android `SensorManager` API to determine availability for various sensors.

## Requirements
- **Android Gradle Plugin (AGP)**: 8.2.2
- **compileSdkVersion**: 34
- **Kotlin**: 1.9.x

## Setup
1. Clone the repository:
   ~~~bash
   git clone https://github.com/miyashita-code/AndroidSensorChecker.git
   ~~~
2. Open in **Android Studio (stable version)**.
3. Build & run on a device or emulator.

## How It Works
- The app retrieves a predefined list of sensors (e.g. `ACCELEROMETER`, `GYROSCOPE`) through `SensorManager.getDefaultSensor(...)`.
- It checks if each sensor is available on the device.
- Results are displayed in a scrollable `TextView` on the main screen.


## Reference
- [Akira Watson's Blog (Japanese)](https://akira-watson.com/android/sensor-all.html)
