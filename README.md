# Reactive Java? Let us count the ways!  

<p style="float: right; padding-left: 10px">
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Jabberwocky.jpg/399px-Jabberwocky.jpg" width="200" align="right"/>
</p>

**Thank you to everyone that attended our session at Oracle CodeOne on Tuesday!**

This repository contains exercises that demonstrate different ways to build reactive applications, from using common building blocks such as Reactive Streams and RxJava to employing holistic frameworks such as Lagom from Lightbend. In this up-to-your-elbows-in-code session, you can experiment with various approaches so you’ll leave with a clear understanding of what reactive programming is and what tools you can use to build reactive applications with Java.

You will need a Java IDE of your choice: Eclipse, IntelliJ, VSCode, emacs or vi if you must, and an installation of maven that can pull dependencies from maven central.

## Getting Started

Clone this repo and run `mvn package`:

```console
$ git clone https://github.com/IBM/reactive-code-workshop.git
$ mvn package
```

This will download all dependencies, and do some quick verification to ensure you're good to go.

## What next

1. (optional) Open [the guided tour](https://ibm.github.io/reactive-code-workshop). Use spacebar to advance. That tour will overlap with the following steps as well.

2. Start with rxjava

    ```console
    $ cd rxjava2
    $ mvn package exec:exec
    ```

    Open the [readme](rxjava2/readme.md). Open the suggested references, and try the exercises in order. More help is in [the guided tour](https://ibm.github.io/reactive-code-workshop).

3. Next, try webflux

    ```console
    $ cd webflux
    $ mvn package spring-boot:run
    ```

    Open the [readme](webflux/readme.md), and try the exercises in order. More help is in [the guided tour](https://ibm.github.io/reactive-code-workshop).

4. Last, try akka

    ```console
    $ cd akka
    $ mvn package exec:java
    ```

    Open the [readme](akka/readme.md), and try the exercises in order. More help is in [the guided tour](https://ibm.github.io/reactive-code-workshop).
