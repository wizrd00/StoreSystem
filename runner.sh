#!/usr/bin/sh

mkdir -p bin/
javac -d bin $(find src -name "*.java")
java -cp bin Main
