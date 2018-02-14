
#!/bin/bash
date >> ~/.workshop-log.txt
echo "Workshop Environment Update Script" >> ~/.workshop-log.txt

export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
# run vscode
/usr/bin/code
