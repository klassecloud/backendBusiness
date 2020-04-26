#!/bin/bash
./gradlew build
mkdir -p build/dependencies && (cd build/dependency; jar -xf ../libs/*.jar)
docker build . -t klassecloud/$(./gradlew -q projectName):$(./gradlew -q projectVersion)
