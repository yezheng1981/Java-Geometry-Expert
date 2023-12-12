#!/bin/bash

PACKAGE_VERSION=`cat ../wprover/Version.java | grep "sversion =" | awk '{print $6}' | sed s/\"//g | sed s/\;//`
