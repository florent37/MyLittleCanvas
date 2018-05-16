#!/usr/bin/env bash
./gradlew clean :mylittlecanvas:assembleDebug :mylittlecanvas:install
./gradlew :mylittlecanvas:bintrayUpload