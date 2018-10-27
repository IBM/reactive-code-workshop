---
theme: black
transition: slide
highlightTheme: solarized-dark
---

## Reactive Java? <br />Let us count the ways!

<br />

Erin Schnabel<br /><small>@ebullientworks</small>

Ozzy Osborne<br /><small>@ozzydweller</small>

---

## What is Reactive?

Different way of solving problems

* Asynchronous
* ***Streams*** of elements
  * Potentially unbounded
* Application "reacts" to elements as they arrive
  * Parallel (order not guaranteed)

---

## Reactive Manifesto

* Responsive: rapid and consistent response times
* Resilient: replication, containment, isolation, delegation
* Elastic: "react to *changes in input rate*"; avoid contention points & bottlenecks
* **Message Driven:**
  * *asynchronous* message passing
  * *non-blocking* communication

<small>https://www.reactivemanifesto.org/</small>

---

## Reactive Streams

* A [Publisher](http://www.reactive-streams.org/reactive-streams-1.0.2-javadoc/org/reactivestreams/Publisher.html) provides sequenced elements
* A [Subscriber](http://www.reactive-streams.org/reactive-streams-1.0.2-javadoc/org/reactivestreams/Subscriber.html) handles notifications (e.g. *onNext*)
* Publishers emit elements according to Subscriber demand (backpressure, etc.)
* *Focused on Stream lifecycle, not operators*

<small>Reactive Streams: http://www.reactive-streams.org/<br/>
Java 9: java.util.concurrent.Flow</small>

--

## "Hot" vs. "Cold" Publisher

* Hot: shared view for all subscribers (multicast)
  * Continuous stream of events, independent of subscriber

* **Cold**: unique view for each subscriber (unicast)
  * No events emitted until there is an subscriber

---

## Playing with Operators

Reactive libraries have subtly different terminology

* RxJava
* Spring WebFlux (Spring 5)
* Akka

--

### Understanding Marble Diagrams

![Rx Marble Diagram](RxMarbleDiagram.png)

--

### `map` operator

![map operator](map.png)

--

### `filter` operator

![filter operator](filter.png)

--

### `merge` operator

![merge operator](merge.png)

--

### `scan` operator

![scan operator](scanSeed.png)

<small>This variation specifies a starting value.</small>

--

## Chaining operators

A single operator is not very powerful.

_Operators are **chained** to create more complex solutions._

---

## ReactiveX

* An _Observable_ emits a sequence of items
* _Single_ emits one item
* An _observer_ **subscribes** to an _Observable_.

* _Flowable_ (RxJava2, backpressure behavior) is like _Observable_

<small>http://reactivex.io/</small><br />
<small>RX(JS|.net|Scala|Clojure|Swift|Python|Lua|Ruby|andmore)</small>

---

## RxJava Exercises

Open the following in your favorite browser:

* [Readme: RxJava2 Exercises](https://github.com/IBM/reactive-code-workshop/tree/master/rxjava2)
* [ReactiveX Operators](http://reactivex.io/documentation/operators.html)
* [RxJava2 Observable Javadoc](http://reactivex.io/RxJava/2.x/javadoc/2.0.8/io/reactivex/Observable.html)

```console
$ cd rxjava2
$ mvn package exec:exec
```

---

## RxJava: Exercise 1

1. lowercase & strip punctuation using `map`
2. create stream of `'b'` words
3. create stream of `'g'` words
4. merge streams (hint: `mergeWith`)

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/rxjava2#exercise-1-map-filter-merge)</small>

--

### RxJava: Exercise 1 -- Checkpoint

```java
Observable<String> fixedWords = words()
  .map(word -> word.toLowerCase().replaceAll("[^a-zA-Z]",""));
dumpObservableToStdOut(fixedWords);

Observable<String> bwords = fixedWords
  .filter(word -> word.startsWith("b"));
dumpObservableToStdOut(bwords);

Observable<String> gwords = fixedWords
  .filter(word -> word.startsWith("g"));
dumpObservableToStdOut(gwords);

Observable<String> result = bwords.mergeWith(gwords);
dumpObservableToStdOut(result);
```

---

### `groupBy` operator

![groupBy operator](groupBy.png)

---

### `flatMap` operator

![flatMap operator](flatMap.png)

---

### `zipWith` operator

![zipWith operator](zip.i.png)

---

## RxJava: Exercise 2a

1. streams of arrays
2. streams of streams (hint `Observable.fromArray`)

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/rxjava2#2a-make-lots-of-observables)</small>

--

### RxJava: Exercise 2a -- Checkpoint

```java
Observable<String> filtered = lines()
  .filter(line -> line.length()>0);

Observable<String[]> aResult = filtered
  .map(line -> line.split(" "));
dumpObservableToStdOut(aResult.map(x -> ""+x));

Observable<Observable<String>> sResult = filtered
  .map(line -> Observable.fromArray(line.split(" ")));
dumpObservableToStdOut(sResult.map(x -> ""+x));
```

--

## RxJava: Exercise 2b

1. `flatMap` an array
2. `flatMap` a stream of streams

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/rxjava2#2b-collapse-lots-of-observables-back-together)</small>

--

### RxJava: Exercise 2b -- Checkpoint

```java
Observable<String> awords = aResult
  .flatMap( arr -> Observable.fromArray(arr) );
dumpObservableToStdOut(awords);

Observable<String> swords = sResult
  .flatMap( obs -> obs );
dumpObservableToStdOut(swords);
```

--

## RxJava: Exercise 2c

1. `groupBy` to group by first letter
2. `flatMap` to merge the stream of streams

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/rxjava2#2c-group-lots-of-observables)</small>

--

### RxJava: Exercise 2c -- Checkpoint

```java
Observable<String> grouped = words()
  .groupBy( word -> word.charAt(0) ) // 1
  .doOnNext(getDebugConsumer())      // see groups
  .flatMap( obs -> obs );            // 2
dumpObservableToStdOut(grouped);

```

--

## RxJava: Exercise 2d

1. stream of lengths
2. stream of lengths and words, via zipWith
3. mapping pairs to string output

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/rxjava2#2d-controlled-collapse)</small>

--

### RxJava: Exercise 2d -- Checkpoint

```java
Observable<String> zipped = words()
  .map( word -> word.length() )                // 1
  .zipWith( words(), Pair::new )               // 2
  .doOnNext(getDebugConsumer())                // see pairs
  .map( pair -> pair.getA()+": "+pair.getB()); // 3
dumpObservableToStdOut(zipped);
```

---

## Spring Reactor Core and `WebFlux`

* Reactor is rooted in Reactive Streams
* API alignment with RxJava where possible
  * **Rx `Observable` -> `Flux`**
  * **Rx `Single` -> `Mono`** 
* Tight integration with Spring concepts: [WebFlux RestController](https://docs.spring.io/spring/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html#web-reactive-server-annotation)

---

## WebFlux Exercises

Open the following in your favorite browser:

* [Readme: WebFlux Exercises](https://github.com/IBM/reactive-code-workshop/tree/master/webflux)
* [WebFlux Operators](http://projectreactor.io/docs/core/release/reference/index.html#which-operator)
* [WebFlux Flux Javadoc](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html)
* [Reactor by example](https://www.infoq.com/articles/reactor-by-example)

```console
$ cd webflux
$ mvn package spring-boot:run
```

---

## WebFlux: Exercise 1

1. lowercase & strip punctuation using `map`
2. create stream of `'b'` words
3. create stream of `'g'` words
4. merge streams (hint: `mergeWith`)

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/webflux#exercise-1-map-filter-merge)</small>

--

### WebFlux: Exercise 1 -- Checkpoint

```java
Flux<String> fixedWords = words()
  .map(word -> word.toLowerCase().replaceAll("[^a-zA-Z]",""));
dumpFluxToStdOut(fixedWords);

Flux<String> bwords = fixedWords
  .filter(word -> word.startsWith("b"));
dumpFluxToStdOut(bwords);

Flux<String> gwords = fixedWords
  .filter(word -> word.startsWith("g"));
dumpFluxToStdOut(gwords);

Flux<String> result = bwords.mergeWith(gwords);
dumpFluxToStdOut(result);
```

---

## WebFlux: Exercise 2a

1. streams of arrays
2. streams of streams (hint `Flux.fromArray`)

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/webflux#2a-more-flux)</small>

--

### WebFlux: Exercise 2a -- Checkpoint

```java
Flux<String> filtered = lines().filter(line -> line.length()>0);
dumpFluxToStdOut(filtered);

Flux<String[]> arrResult = filtered
  .map(line -> line.split(" "));
dumpFluxToStdOut(arrResult.map(x -> ""+x));

Flux<Flux<String>> fluxResult = filtered
  .map(line -> Flux.fromArray(line.split(" ")));
dumpFluxToStdOut(fluxResult.map(x -> ""+x));
```

--

## WebFlux: Exercise 2b

1. `flatMap` an array
2. `flatMap` a stream of streams

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/webflux#2b-collapse-nested-flux)</small>

--

### WebFlux: Exercise 2b -- Checkpoint

```java
Flux<String> awords = arrResult
  .flatMap( arr -> Flux.fromArray(arr) );
dumpFluxToStdOut(awords);

Flux<String> fwords = fluxResult
  .flatMap( flx -> flx );
dumpFluxToStdOut(fwords);
```

--

## WebFlux: Exercise 2c

1. `groupBy` to group by first letter
2. `flatMap` to merge the stream of streams

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/webflux#2c-groupedflux)</small>

--

### WebFlux: Exercise 2c -- Checkpoint

```java
Flux<String> grouped = words()
  .groupBy( word -> word.charAt(0) ) // 1
  .doOnNext(getDebugConsumer())      // see groups
  .flatMap( flx -> flx );            // 2
dumpFluxToStdOut(grouped);
```

--

## WebFlux: Exercise 2d

1. stream of lengths
2. stream of lengths and words, via `zipWith`
3. mapping pairs to string output (hint, default `Pair` impl!)

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/webflux#2d-controlled-collapse)</small>

--

### WebFlux: Exercise 2d -- Checkpoint

```java
Flux<String> result = words()
  .map( word -> word.length() )      // 1
  .zipWith( words() )                // 2
  .doOnNext(getDebugConsumer())      // see pairs
  .map( pair -> pair.getT1()+": "+pair.getT2());
dumpFluxToStdOut(result);
```

---

## Akka

Akka focuses on how messages flow

* Source: something with exactly one output stream
* Sink: something with exactly one input stream
* Flow: something with exactly one input and one output stream

![Akka shapes](akka-linear-flow.png)
<small>https://blog.redelastic.com/diving-into-akka-streams-2770b3aeabb0</small>

---

### `merge` operator

<small>A "Fan-in" operator</small>

![merge operator](akka-merge.png)
<small>https://blog.redelastic.com/diving-into-akka-streams-2770b3aeabb0</small>

---

## Akka Exercises

Open the following in your favorite browser:

* [Readme: Akka Exercises](https://github.com/IBM/reactive-code-workshop/tree/master/akka)
* [Akka Operators](https://doc.akka.io/docs/akka/2.5/stream/operators/index.html)
* [Akka Source Javadoc](https://doc.akka.io/japi/akka/current/akka/stream/javadsl/Source.html)

```console
$ cd akka
$ mvn package exec:java
```

---

### Akka: Exercise 1

1. lowercase & strip punctuation using `map`
2. create stream of 'b' words
3. create stream of 'g' words
4. merge streams (hint: actually `merge`)

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/akka#exercise-1-map-filter-merge)</small>

--

#### Akka: Exercise 1 -- Checkpoint

```java
Source<String,NotUsed> fixedWords = words()
  .map(word -> word.toLowerCase().replaceAll("[^a-zA-Z]",""));
dumpSourceToStdOut(fixedWords);

Source<String, NotUsed> bwords = fixedWords
  .filter(word -> word.startsWith("b"));
dumpSourceToStdOut(bwords);

Source<String, NotUsed> gwords = fixedWords
  .filter(word -> word.startsWith("g"));
dumpSourceToStdOut(gwords);

Source<String, NotUsed> result = bwords.merge(gwords);
dumpSourceToStdOut(result);
```

---

### Akka: Exercise 2a

1. streams of arrays
2. streams of streams (hint `Source.from` / `Arrays.asList`)

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/akka#2a-make-lots-of-things)</small>

--

#### Akka: Exercise 2a -- Checkpoint

```java
Source<String,NotUsed> filtered = lines()
  .filter(line -> line.length()>0);

Source<String[],NotUsed> arrResult = filtered
  .map(line -> line.split(" "));
dumpSourceToStdOut(arrResult.map(x -> ""+x));

Source<Source<String,NotUsed>,NotUsed> srcResult = filtered
  .map(line -> Source.from(Arrays.asList(line.split(" "));
dumpSourceToStdOut(srcResult.map(x -> ""+x));
```

--

### `flatMapConcat` operator

<small>A "Flattening" operator</small>

![flatMapConcat operator](akka-flatMapConcat1.png)<br />
![flatMapConcat operator](akka-flatMapConcat2.png)

<small>https://doc.akka.io/docs/akka/current/stream/stream-substream.html</small>

--

### Akka: Exercise 2b

1. `flatMapConcat` an array
2. `flatMapConcat` a stream of streams

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/akka#2b-collapse-lots-of-things-back-together)</small>

--

#### Akka: Exercise 2b -- Checkpoint

```java
Source<String,NotUsed> awords = arrResult
  .map(x->Source.from(Arrays.asList(x)))
  .flatMapConcat(src-> src);
dumpSourceToStdOut(awords);

Source<String,NotUsed> swords = srcResult
  .flatMapConcat(src-> src);
dumpSourceToStdOut(swords);
```

--

### `groupBy` operator

<small>A "Nesting" operator</small>

![groupBy operator](akka-groupBy.png)
<small>https://doc.akka.io/docs/akka/current/stream/stream-substream.html</small>

--

### `mergeSubstreams` operator

<small>A "Nesting" operator</small>

![groupBy operator](akka-groupBy-mergeSubstream.png)
<small>https://doc.akka.io/docs/akka/current/stream/stream-substream.html</small>

--

### Akka: Exercise 2c

1. `groupBy` to group by first letter (first arg is max group count!)
2. `mergeSubstreams` (NOT flatMap!!) to merge substreams

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/akka#2c-grouping-in-akka-subflows)</small>

--

#### Akka: Exercise 2c -- Checkpoint

```java
Source<String,NotUsed> grouped = words()
  .groupBy(26, word -> word.charAt(0)) // 1
  .alsoTo(getDebugSink())              // see groups
  .mergeSubstreams();                  // 2
dumpSourceToStdOut(grouped);
```

<small>Something different happens when you view the grouped elements with akka</small>

--

### Akka: Exercise 2d

1. Use `map` to create a stream of string lengths
2. Use `zipWith` to combine the stream of lengths and stream of words into `Source<Pair,NotUsed>`
3. Use `map` to show group contents as single `Source<String,NotUsed>`, e.g. "length: string"

<small>more details in the [readme](https://github.com/IBM/reactive-code-workshop/tree/master/akka#2d-controlled-collapse)</small>

--

#### Akka: Exercise 2d -- Checkpoint

```java
Source<Integer, NotUsed> lengths = fixedWords
  .map(s -> s.length());                // 1

Source<Pair<Integer, String>, NotUsed> pairs = lengths
  .zip(fixedWords);                    // 2
dumpSourceToStdOut(pairs);             // Different!

Source<String, NotUsed> result = pairs
  .map(pair -> pair.first() + ": " + pair.second());
dumpSourceToStdOut(result);
```

---

## Reactive frameworks

* [Lagom](https://www.lagomframework.com/) (Lightbend: Akka and Play)
  * [Akka Actor Model](https://doc.akka.io/docs/akka/current/actors.html)
* [Vert.x](https://vertx.io/) (Rx*, or Reactor, or Akka)

* Standardizing operators
  * [MicroProfile Reactive Streams](https://github.com/eclipse/microprofile-reactive-streams)
  * [https://github.com/reactor/reactive-streams-commons](https://github.com/reactor/reactive-streams-commons) (dormant)

---

## Resources

* [Reactive Streams Glossary of Terms](https://github.com/reactive-streams/reactive-streams-jvm/blob/v1.0.1/README.md#glossary)
* [Reactive Manifesto Glossary of Terms](https://www.reactivemanifesto.org/glossary) 
* [A Journey into Reactive Streams](https://blog.redelastic.com/a-journey-into-reactive-streams-5ee2a9cd7e29) -- Kevin Webber<br />
A mostly impl neutral overview of Reactive Streams API usage, with diagrams, scenarios etc.. 
