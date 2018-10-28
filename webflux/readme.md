# Simple Spring WebFlux Java Base Project

A simple base project to allow exploration of Spring WebFlux

## Compile and run

```console
$ cd webflux
$ mvn package spring-boot:run
```

## References to have handy

Yay for tabbed browsers!

* [WebFlux Operators](http://projectreactor.io/docs/core/release/reference/index.html#which-operator)
* [WebFlux Flux Javadoc](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html)
* [Reactor by example](https://www.infoq.com/articles/reactor-by-example)

## Exercises

We will be exploring reactive concepts by playing with Observable operator composition.

* Open `src/main/java/com/example/webflux/WebFluxApplication.java` in your IDE.
* Edit the `myCommandLineRunner()` method to complete the exercises below.

### Goal

Complete as many of the steps within each Exercise as you can.

* Steps within an exercise are related (later steps often require earlier steps, e.g.)
* At the end of each step, use `dumpFluxToStdOut` to print the result.
**This will need some type munging**; this is intentional.

### Hints

* Use `.doOnNext(getDebugConsumer())` between operations if you get confused.
* A cheap way to convert something to a string is with `.map(x -> "" + x)`

### Exercise 1: map, filter, merge

Start with stream of `words()`:

1. Use `map` to make all words lowercase and remove all punctuation (`[^a-zA-Z]`).

    For example, in `src/main/java/com/example/webflux/WebFluxApplication.java`, edit the `myCommandLineRunner()` and add the following:

    ```java
        Flux<String> src_1_1 = words()
            .map(s -> s.toLowerCase().replaceAll("[^a-zA-Z]", ""));
        dumpFluxToStdOut(src_1_1);
    ```

    Use ` mvn package spring-boot:run` from the command line to rebuild and run (run inside your IDE of choice if you prefer). The poem Jabberwocky should now be followed by a list of punctuation-free, lower case words.

2. Apply `filter` to create a stream of words beginning with `b`
3. Create a separate stream of words beginning with `g`
4. `merge` the two streams
5. ***Optional: distinct, scan***
    * replace all words in the two current streams with their lengths.
    * use `scan` to total the number of chars in words starting `b`
    * use `scan` to total the number of chars in `distinct` words starting `g`
6. ***Really Really Optional: count***
    * `count` all `b` words
    * `count` `distinct` `g` words

    Note: If you try these, you'll notice that count produces an incompatible type for `dumpFluxToStdOut`. You will need to find another way to subscribe to see what happens.

### Exercise 2: Nested Flux and substreams

#### 2a. More Flux!

Start with the stream of lines (available from the `lines()` method):

1. split each line in the stream into an array of words. e.g. `Flux<String[]>`
2. split each line in the stream into a stream of words. e.g. `Flux<Flux<String>>`

#### 2b. Collapse nested Flux

Add operators to the stream to allow `dumpFluxToStdOut` to print individual words again.

1. Use `flatMap` to recombine stream from step 1 above into a stream of words (`Flux<String>`).
2. Use `flatMap` to recombine stream from step 2 above into a stream of words (`Flux<String>`).

#### 2c. GroupedFlux

Start with stream of (optionally lowercase, punctuation-free)  `words()`:

1. Use `groupBy` to group by first letter
2. Use `flatMap` to re-merge the groups so you can output the stream of words. Observe the order of the words!
3. ***Optional: distinct***
    * Include only `distinct` words
4. ***Really Really Optional***
    * `count` how many groups
    * `count` how many words are in each group

#### 2d. Controlled collapse

Start with stream of (optionally lowercase, punctuation-free) `words()`:

1. Use `map` to create a stream of string lengths
2. Use `zipWith` to combine to combine the two streams (lengths and words) into `Flux<Tuple2<Integer,String>>`
3. Use `map` to show tuples as single `Flux<String>` via `dumpFluxToStdOut`, e.g. "length: string"
4. ***Optional***
    * Use `groupBy` to group `Flux<Tuple2<Integer,String>>` by length
    * Use `flatMap` and `dumpFluxToStdOut` to show content via `Flux<String>`, e.g. "length: string"
5. ***Really Really Optional***
    * What happens if you use `concatMap` instead of `flatMap`? Why?

## Other References

[WebFlux RestController](https://docs.spring.io/spring/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html#web-reactive-server-annotation) -- integration of WebFlux with REST API
