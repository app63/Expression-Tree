#!/bin/bash
javac Prefixer.java
javac PrefixCalc.java

java Prefixer -r test.txt
echo -e "Please enter the above expression below with brackets"
java PrefixCalc

