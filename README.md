# reactive-code-workshop
Think 2018 Reactive Code workshop

Welcome to the workshop!

We hope to help you "count the ways" in which you can write reactive applications in the following activities. If you aren't a savvy developer, we should be able keep you on a straight and narrow path to help you get an idea of the landscape. If you're an old-hand or a new hotshot, feel free to go off-roading, though we'll expect you to do a fair amount of self-rescue if you do so.

The lab will use a Skytap-hosted virtual machine(VM), which will contain all necessary artifacts for the lab.

This lab is divided into a few parts: 

* An overview of reactive programming concepts (15 min)
* A set of programming exercises: (3, 20 min each)
  - We'll describe each exercise
  - You will complete the exercise using RxJava
  - You will complete the exercise using Akka
* Last, we'll provide an overview of Lagom (15min)
  - You will complete an exercise using Lagom (15min)

## About the VM

The Skytap VMfeatures Ubuntu 16.04.3 LTS (xenial) as the operating system and has the following software installed:

* git 2.7.4
* sbt 0.13.16
* Java 8
* Apache Maven 3.3.9
* VS Code 1.21.1
* Docker 17.12.0-ce

This VM will update itself on launch, and will start the Lagom Chirper example service in the background (care of this fork: https://github.com/ebullient/lagom-java-chirper-example/blob/master/README.md). We will use the Chirper example as a source of events for this lab.

## Using Skytap

Each person in the lab will have their own VM environment.

Tips: 

* All commands will be performed from a Terminal window within the SkytapVM in the browser (not directly from your laptop, which will not have connectivity to the Skytap VM other than working with its desktop via the web browser)
* Click out of the browser before performing any laptop keyboard shortcuts, otherwise the keyboard shortcuts will be done in the Skytap VM Linux OS
* Use the right click copy/paste, don’t use keyboard shortcuts as they often don’t work.
* To copy from the Skytap VM into your laptop, right click in the VM to copy the content, go to the top clipboard icon and then copy the content again. This will add the content into your laptop's cut/paste buffer.



## Contacts

Erin Schnabel (schnabel@us.ibm.com / @ebullientworks)
Kiki Carter (kikia.carter@lightbend.com / @kikisworldrace)

