#!/bin/bash
SCRIPTDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
PROJECTDIR=$SCRIPTDIR/projects

if [ ! -d $PROJECTDIR ]; then
  mkdir $PROJECTDIR
fi

for GITURL in $(cat repolist.txt); do
  DIRNAME=$(echo $GITURL | sed -e 's#.*/##')
  echo Processing project $DIRNAME with git url $GITURL.git
  if [ -d $PROJECTDIR/$DIRNAME ]; then
    echo ".. updating existing repo"
    cd $PROJECDIR/$DIRNAME
    git fetch --all
    git reset --hard origin/master
  else
    echo ".. grabbing new repo"
    cd $PROJECTDIR
    git clone $GITURL.git
  fi
done
