#!/bin/bash

importNvmIfInstalled () {
  if [ -d ~/.nvm ]; then 
    if [ -f ~/.nvm/nvm.sh ]; then
      . ~/.nvm/nvm.sh
    fi
  fi
}

testCommand () {
  hash $1 2>/dev/null
  RC=$?
  CMDVER=""
  if [ $RC -eq 0 ]; then
    CMDVER=$( $@ 2>&1 | head -n 1)
  else
    #wasn't a command, is it a bash alias?
    type $1 2>/dev/null
    RC=$?
    if [ $RC -eq 0 ]; then
      CMDVER=$( $@ 2>&1 | head -n 1)
    else
      (>&2 echo -e "$1 \t! MISSING ! rc=$RC")
    fi
  fi

  if [ -n "$CMDVER" ]; then
    echo -e "$1 \t: $CMDVER"
  fi
}

importNvmIfInstalled

testCommand nvm --version
testCommand mvn --version
testCommand fish --version
testCommand git --version
testCommand java -version
testCommand docker -v
testCommand docker-compose -version
