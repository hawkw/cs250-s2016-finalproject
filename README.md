Finger Trees in Scala [![Build Status](https://travis-ci
.org/hawkw/cs250-s2016-finalproject.svg?branch=master)](https://travis-ci.org/hawkw/cs250-s2016-finalproject) [![Codacy Badge](https://api.codacy.com/project/badge/grade/a7e9e2b8213c43f7afd419e1750f90f1)](https://www.codacy.com/app/hawk/cs250-s2016-finalproject)
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