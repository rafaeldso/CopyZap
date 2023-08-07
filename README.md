# CopyZap App 
=============================

This app simplifies the process of copying multiple messages from your favorite instant messaging application, removing date, time, and sender headers.

## Building the CopyZap App 

First, clone the repo:

`git clone git@github.com:rafaeldso/CopyZap.git`

Building the sample then depends on your build tools.

### Android Studio (Recommended)

(These instructions were tested with Android Studio version  2022.1.1, 2022.1.2 or later)

* Open Android Studio and select `File->Open...` or from the Android Launcher select `Import project (Eclipse ADT, Gradle, etc.)` and navigate to the root directory of your project.
* Select the directory or drill in and select the file `build.gradle` in the cloned repo.
* Click 'OK' to open the the project in Android Studio.
* A Gradle sync should start, but you can force a sync and build the 'app' module as needed.

### Gradle (command line)

* Build the APK: `./gradlew build`


## Running the Sample App

Connect an Android device to your development machine.

### Android Studio

* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select the device you wish to run the app on and click 'OK'

### Gradle

* Install the debug APK on your device `./gradlew installDebug`
* Start the APK: `<path to Android SDK>/platform-tools/adb -d shell am start io.keen.client.android.example/io.keen.client.android.example.MyActivity`


## Using the CopyZap App

Copy multiple messages in your messaging app and paste into the home screen text edit field. Then click on the format button. 
You will be taken to the Result screen, where you can copy the formatted message, without the unwanted headers.

## Maintainers
This project is mantained by:
* [Rafael Oliveira](https://github.com/rafaeldso)
