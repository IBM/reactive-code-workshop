# reactive-code-workshop
Think 2018: Reactive Java? Let us count the ways!

<!-- TOC depthFrom:2 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [About the Lab](#about-the-lab)
- [Let's get started](#lets-get-started)
- [Reactive Concepts](#reactive-concepts)
- [Excercises!!](#excercises)
- [About the VM](#about-the-vm)
  - [Using Skytap](#using-skytap)
- [About chirper](#about-chirper)
- [References](#references)
- [Contacts](#contacts)

<!-- /TOC -->

## About the Lab

We hope to help you "count the ways" in which you can write reactive applications in the following activities. If you aren't a savvy developer, we should be able keep you on a straight and narrow path to help you get an idea of the landscape. If you're an old-hand or a new hotshot, feel free to go off-roading, though we'll expect you to do a fair amount of self-rescue if you do so.

We have divided this lab into a few parts:

* An overview of reactive programming concepts (15 min)
* A set of programming exercises:
  - We'll describe each exercise
  - You will complete the exercise using RxJava
  - You will complete the exercise using Akka
  - You might be adventurous and try WebFlux
* Last, we'll provide an overview of Lagom (15min)
  - You will complete an exercise using Lagom (15min)

## Let's get started

Please open the Skytap VM for this lab (#6915) in the browser. The Ubuntu desktop should be ready and waiting with VS Code open and code staring you in the face.

First, we need to [Open a terminal window](https://www.wikihow.com/Open-a-Terminal-Window-in-Ubuntu) and run a script:

        $ ~/reactive-code-workshop/scripts/helloWorkshop.sh


While that runs...

## Reactive Concepts

Reactive programming

  * Within a single system
  * Logic and dataflow management
    - Reactive streams
    - ReactiveX: Observables, Flowables

Reactive systems

  * Event driven
  *

## Excercises!!

* [Rest can be reactive, too](ReactiveREST.md)
* [Rest can be reactive, too](ReactiveTransformation.md)


## About the VM

The lab will use a Skytap-hosted virtual machine (VM), which will contain all necessary artifacts for the lab. The Skytap VM features Ubuntu 16.04.3 LTS (xenial) as the operating system and has the following software installed:

* git 2.7.4
* sbt 0.13.16
* Java 8
* Apache Maven 3.3.9
* VS Code 1.21.1
* Docker 17.12.0-ce

This VM will update itself on launch, and will start the Lagom Chirper example service in the background (care of this fork: https://github.com/ebullient/lagom-java-chirper-example/blob/master/README.md). We will use the Chirper example as a source of events for this lab.

### Using Skytap

Each person in the lab will have their own VM environment, which should be accessed from the browser.

Tips:

* All commands will be performed from a Terminal window within the SkytapVM in the browser (not directly from your laptop, which will not have connectivity to the Skytap VM other than working with its desktop via the web browser)
* Click out of the browser before performing any laptop keyboard shortcuts, otherwise the keyboard shortcuts will be done in the Skytap VM Linux OS
* Use the right click copy/paste, don’t use keyboard shortcuts as they often don’t work.
* To copy from the Skytap VM into your laptop, right click in the VM to copy the content, go to the top clipboard icon and then copy the content again. This will add the content into your laptop's cut/paste buffer.

[Skytap help: Accessing VMs with your browser](https://help.skytap.com/VMClient.html)

## About chirper

This lab relies on a [variant](https://github.com/ebullient/lagom-java-chirper-example) of the [Lagom Chirper Example](https://github.com/lagom/lagom-java-chirper-example), which is running in the background of your VM. Our variant relies on Docker Compose to remove as many moving parts as possible, and to trim the footprint on the VM. There are two aliases we use to work with this background system: `think-compose` and `think-run`. See the `~/lagom-java-chirper-example` directory if you're curious.

## References

* Marble diagrams
  - https://medium.com/@jshvarts/read-marble-diagrams-like-a-pro-3d72934d3ef5
  - http://reactivex.io/documentation/observable.html


## Contacts

Erin Schnabel (schnabel@us.ibm.com / @ebullientworks)
Kiki Carter (kikia.carter@lightbend.com / @kikisworldrace)
