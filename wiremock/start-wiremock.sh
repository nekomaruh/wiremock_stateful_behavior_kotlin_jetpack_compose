#!/bin/bash

export WIREMOCK_HOST=localhost
export WIREMOCK_PORT=3001
export WIREMOCK_DATA=../data
export ARTIFACT_NAME=wiremock.jar
export ARTIFACT_VERSION=2.35.0

if [[ ! -f $ARTIFACT_NAME ]] ;
then
  curl https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/$ARTIFACT_VERSION/wiremock-standalone-$ARTIFACT_VERSION.jar --output $ARTIFACT_NAME
  java -jar $ARTIFACT_NAME --root-dir data --port=$WIREMOCK_PORT --verbose
else
  java -jar $ARTIFACT_NAME --root-dir data --port=$WIREMOCK_PORT --verbose
fi
