
# java logback example.

There's a shell script call "run_it.sh" which will compile and run the package... if it works, you should see stuff printed to the console.

It'll also be written to whatever you've identified as logging providers in logback.xml file.  Like say the "logs" directory...

## Other stuff

This is an example of the use of Logback in a basic Java program.
The program should be relatively self-contained and it was run on Ubuntu 18.04

The program randomly prints around 50-100 messages with varying levels and contents.

I use MDC to demonstrate establish a log "context".


