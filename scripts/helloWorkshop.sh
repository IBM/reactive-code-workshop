#!/bin/bash

echo "Welcome to our Reactive Workshop!

This script will ensure that your VM has the latest and greatest,
and is ready to go.

In the meanwhile, let's learn about reactive concepts... "
date >> ~/.workshop-log.txt
echo "Workshop Environment Update Script" >> ~/.workshop-log.txt

# fetch latest from git
cd ~
for x in reactive-code-workshop akka-streams-chirper-client rxjava2-chirper-client webflux-chirper-client wallet-exercise; do
  echo "

  Updating $x"
  cd $x
  git fetch --all
  git reset --hard origin/master
  cd ~
done
