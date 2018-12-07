#!/usr/bin/env bash

DEBUG_APK_PATH="presentation/build/outputs/apk/debug/presentation-debug.apk"
RELEASE_APK_PATH=""

APK_PATH=${DEBUG_APK_PATH}

# Install APK to device
# Use as: APK_INSTALL app-debug.apk
APK_INSTALL="adb devices | tail -n +2 | cut -sf 1 | xargs -I X adb -s X install -r $1"

# Alias for building and installing the apk to connected device
# Run at the root of your project
# $ BUILD_AND_INSTALL_APK
BUILD_AND_INSTALL_APK="./gradlew assembleDebug && $APK_INSTALL $APK_PATH"

# Launch your debug apk on your connected device
# Execute at the root of your android project
# Usage: LAUNCH_DEBUG_APK
LAUNCH_DEBUG_APK="adb shell monkey -p `aapt dump badging ${APK_PATH} | grep -e 'package: name' | cut -d \' -f 2` 1"

# ------------- Single command to build+install+launch apk------------#
# Execute at the root of your android project

# Use as: buildInstallLaunchDebugApk
BUILD_INSTALL_LAUNCH_DEBUG_APK="$BUILD_AND_INSTALL_APK && $LAUNCH_DEBUG_APK"

eval ${BUILD_INSTALL_LAUNCH_DEBUG_APK}
