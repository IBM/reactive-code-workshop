#!/bin/bash
shopt -s expand_aliases

date >> ~/.workshop-log.txt
echo "Workshop Environment Update Script" >> ~/.workshop-log.txt

# kill the update manager process
echo passw0rd | sudo -S killall update-manager

cd ~

# Update code (there is an update pending, distracting)
./vscode.sh
# Help keep code tidy-ish
code --install-extension EditorConfig.editorconfig

# Install node.js
wget -qO- https://raw.githubusercontent.com/creationix/nvm/v0.33.2/install.sh | bash
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm
nvm install --lts


# update existing github repos to ebullient fork
cd ~
for x in lagom-java-chirper-example rxjava2-chirper-client webflux-chirper-client; do
  cd $x
  git remote set-url origin https://github.com/ebullient/$x.git
  git fetch --all
  git reset --hard origin/master
  cd ~
done

# Update directories for new projects
for x in wallet-exercise akka-streams-chirper-client; do
  echo "Updating $x" >> ~/.workshop-log.txt
  if [ -d ~/$x ]; then
    cd ~/$x
    git fetch --all
    git reset --hard origin/master
  else
    cd ~
    git clone https://github.com/kikiya/$x.git
  fi
done

eval $(~/lagom-java-chirper-example/deploy/compose/run.sh env)
if ! grep "deploy/compose/run.sh env" ~/.bashrc; then
  echo 'eval $(~/lagom-java-chirper-example/deploy/compose/run.sh env)' >> ~/.bashrc
fi

# Start chirper in the background
cd lagom-java-chirper-example
think-compose pull
think-compose build proxy
sbt -DbuildTarget=compose clean docker:publishLocal
think-run start
think-run wait
cd ~

# clean up old images
docker system prune -f

if [ -z "${DOCKER_MACHINE}" ]; then
  export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
  # run vscode
  /usr/bin/code
fi
