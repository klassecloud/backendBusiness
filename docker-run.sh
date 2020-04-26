#!/bin/bash
docker run -p 8080:8080 -t klassecloud/$(./gradlew -q projectName):$(./gradlew -q projectVersion)
