#!/bin/bash
#java -cp ./log4j-api-2.13.3.jar:./log4j-core-2.13.3.jar:./splunk-library-javalogging-1.8.0.jar:./okhttp-3.9.0.jar:gson-2.8.6.jar:./okio-1.13.0.jar -Dlog4j.configurationFile=./log4j2.xml Genlog_log4j2.java
java -cp "./splunk-library-javalogging-1.8.0.jar:./jars/*" -Dlog4j.configurationFile=./log4j2.xml Genlog_log4j2.java