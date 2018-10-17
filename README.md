# Overview
<<<<<<< HEAD
This project is a fork of the [j2mod](https://sourceforge.net/projects/j2mod/) library which began life as [jamod](http://jamod.sourceforge.net/). 
A huge amount of refactoring and code fixing has been carried out on this library, with the addition of supporting JUnit tests, to ensure the library is fit for production use.

This implementation supports Modbus TCP, UDP, RTU over TCP, Serial RTU and Serial ASCII in both Master and Slave configurations.
The serial comms is implemented using [jSerialComm](http://fazecast.github.io/jSerialComm/) and does not require any outside dependencies over and above the logging facade [slf4j](https://www.slf4j.org/).

For instructions on how to use the library, visit the wiki [here](https://github.com/steveohara/j2mod/wiki) 
=======
This is a project to bring the [j2mod](https://sourceforge.net/projects/j2mod/) library up to date.

j2mod has been actively maintained by [Julie Haugh](https://sourceforge.net/u/jfhaugh/) after forking it from [jamod](http://jamod.sourceforge.net/) and she has done 
absolutely sterling work in trying to iron out the myriad of wrinkles in this piece of work. It's about time she had some help so this 
project will endeavour to bring more collaboration and expertise to the fray.

The main driver for doing this work is to get away from the RxTxComm library and to use something actively supported and that brings its own native implementations.

The weapon of choice is the [jSerialComm](http://fazecast.github.io/jSerialComm/) library which is extremely well supported by Will Hedgecock and is actively developed.

The other goal of this project is to bring the codebase into line with JDK 1.6 and to fix all the known bugs.
>>>>>>> 21feed643893143065276b9da25929bd2bc38f62

# Releases
Stable releases can be downloaded here 

<<<<<<< HEAD
https://search.maven.org/search?q=g:com.ghgande
=======
http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22j2mod%22
>>>>>>> 21feed643893143065276b9da25929bd2bc38f62

Snapshot releases can be downloaded here 

https://oss.sonatype.org/content/repositories/snapshots/com/ghgande/j2mod

<<<<<<< HEAD
# Known Issues

* There are no unit tests for the RTU over TCP transport
* There is no way of adding `AbstractSerialTransportListener` to a `ModbusSlave` which means you cannot get informed of when the library is switching between send and receive
* A refactor is overdue to hide package components to encourage best practise usage patterns

# Dependencies

* [jSerialComm](http://fazecast.github.io/jSerialComm/)
The serial comms is handled by JSerialComm that includs native implementations for most platforms.
* [slf4j](https://www.slf4j.org/)
Logging facade to fit in with your application logging framework

# Including j2mod

    <dependency>
        <groupId>com.ghgande</groupId>
        <artifactId>j2mod</artifactId>
        <version>LATEST</version>
    </dependency>
    
# Announcements

I have maintained this library to be Java 1.6 compatible for the past 2 years but now 
that my time for supporting j2mod is ever more pressured, I have decided that v2.5.1 will be the last
version that is available for this JVM.

From 3.0.0, the library will have a minimum requirement of Java 1.8.
    
=======
# Roadmap

Not very much to add to the system but here are a couple of possibles;

* Register aggregator - at 4NG we have created an extended Transaction wrapper that consolidates multiple single register requests into a single, 
multi-register request by working out the 'distance' between registers and creating larger requests to reduce the comms overhead of multiple requests
* Handling ASCII payloads - some industrial Modbus slaves use an ASCII payload to represent their data i.e. a sequence of registers represent a floating point number in ASCII

# Known Issues

* The jSerialComm has some oddities with regards to Thread.sleep() calls that slowdown operation of the comms port. Every port open call incurs a 500ms delay and every settings change (baud rate, stop bit etc.) to an open port incurs a 200ms delay
* There are no unit tests for the RTU over TCP transport
>>>>>>> 21feed643893143065276b9da25929bd2bc38f62
