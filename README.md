Optimised Onion Routing [![Build Status](https://travis-ci.org/hawkw/cs250-s2016-finalproject.svg?branch=master?style=flat-square)](https://travis-ci.org/hawkw/cs250-s2016-finalproject) [![Codacy](https://img.shields.io/codacy/e27821fb6289410b8f58338c7e0bc686.svg?maxAge=2592000?style=flat-square)]() [![Sputnik](https://sputnik.ci/conf/badge)](https://sputnik.ci/app#/builds/hawkw/cs250-s2016-finalproject)
=====================

Aubrey Collins & Hawk Weisman, CMPSC250 Spring 2016 Final Project 

Building & Running
------------------

This project is built using SBT, the Simple Build Tool for Scala projects. 
A shell script for Unix systems, also called `sbt`, is included in the 
project. If the correct version of SBT is not already installed, running the 
shell script will automatically download SBT and install it locally (root 
access is not required).

Running the command `$ ./sbt` with no arguments will launch an [interactive SBT
 console](http://www.scala-sbt.org/0.13/docs/Running.html) where you can run 
 SBT commands. Alternatively, you can pass SBT commands (such as `compile`) 
 as arguments to the shell script, for example, `$ ./sbt compile`.
 
The main SBT commands of interest are:
 + `compile` will compile the project
 + `test` will run all unit tests
