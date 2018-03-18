
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

# switch to ebullient fork of lagom-java-chirper-example
cd ~/lagom-java-chirper-example
git remote set-url origin https://github.com/ebullient/lagom-java-chirper-example.git
git fetch --all
git reset --hard origin/master

eval $(~/lagom-java-chirper-example/deploy/compose/run.sh env)
echo 'eval $(~/lagom-java-chirper-example/deploy/compose/run.sh env)' >> ~/.bashrc

# Start chirper in the background
think-compose pull
think-compose build proxy
sbt -DbuildTarget=compose clean docker:publishLocal
think-run start
think-run wait

# checkout akka-streams-chirper-client
if [ ! -d akka-streams-chirper-client ]; then
  git clone https://github.com/mckeeh3/akka-streams-chirper-client.git
else
  cd ~/akka-streams-chirper-client
  git fetch --all
  git reset --hard origin/master
fi

# checkout akka-streams-chirper-client
if [ ! -d wallet-exercise ]; then
  git clone https://github.com/kikiya/wallet-exercise.git
else
  cd ~/wallet-exercise
  git fetch --all
  git reset --hard origin/master
fi

# update other existing github repos
cd ~
for x in rxjava2-chirper-client webflux-chirper-client; do
  cd $x
  git fetch --all
  git reset --hard origin/master
  cd ~
done

# clean up old images
docker system prune -f

if [ -z "${DOCKER_MACHINE}" ]; then
  export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
  # run vscode
  /usr/bin/code
fi