# Simple Spring WebFlux Java Base Project

A simple base project to allow exploration of Spring WebFlux

## Compile

```console
$ mvn package
```

## Run

Use either:

```console
$ mvn spring-boot:run
$ java -jar target/webflux-base-1.0-SNAPSHOT.jar
```

## References to have handy

Yay for tabbed browsers!

* [WebFlux Operators](http://projectreactor.io/docs/core/release/reference/index.html#which-operator)
* [WebFlux Flux Javadoc](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html)
* [Reactor by example](https://www.infoq.com/articles/reactor-by-example)

## Exercises

Your goal for each section is to achieve the required bits in the time allotted (for the lab). Do the extras if you finish early, or on your own time later (we hope you get as obsessed with doing them as we did).

At the end of each step, you should use `dumpFluxToStdOut` to print the result. In some cases, this will need some type munging to get back to the right type; this is intentional, but here are some hints:

* Use `.doOnNext(getDebugConsumer())` between operations if you get confused.
* A cheap way to convert something to a string is with `.map(x -> "" + x)`

You'll be doing this work in `com.example.WebFluxApplication#myCommandLineRunner()`.

### Exercise 1: map, filter, merge

1. process the stream of `words()`: make them all lowercase and remove all punctuation (`[^a-zA-Z]`).
2. using `filter` create the stream of words beginning with 'b'
3. create a separate stream of lowercase, punctuation free words beginning with 'g'
4. `merge` the two streams
5. ***Optional: distinct, scan***
    * replace all words in the two current streams with their lengths.
    * use `scan` to total the number of chars in words starting 'b'
    * use `scan` to total the number of chars in `distinct` words starting 'g'
6. ***Really Really Optional: count***
    * `count` all 'b' words
    * `count` `distinct` 'g' words

### Exercise 2: Nested Flux and substreams

#### 2a. More Flux!

1. process of stream of lines to split into words as `Flux<String[]>`
2. process of stream of lines to split into words as `Flux<Flux<String>>`

#### 2b. Collapse nested Flux

Use `dumpFluxToStdOut` print individual words again.

1. Use `flatMap` to recombine stream from step 1 above into `Flux<String>`
2. Use `flatMap` to recombine stream from step 2 above into `Flux<String>`

#### 2c. GroupedFlux

Start with stream of (optionally lowercase, punctuation-free) words:

1. Use `groupBy` to group by first letter
2. Use `flatMap` to re-merge: observe order of words
3. ***Optional: distinct***
    * Include only `distinct` words
4. ***Really Really Optional***
    * `count` how many groups
    * `count` how many words are in each group

#### 2d. Controlled collapse

Start with stream of (optionally lowercase, punctuation-free) words:

1. Use `map` to create a stream of string lengths
2. Use `zipWith` to combine the stream of lengths with the original stream of words into `Flux<Tuple2<Integer,String>>`
3. Use `map` to show tuples as single `Flux<String>` via `dumpFluxToStdOut` (e.g. "length: string")
4. ***Optional***
    * Use `groupBy` to group `Flux<Tuple2<Integer,String>>` by length
    * Use `flatMap` and `dumpFluxToStdOut` to show content via `Flux<String>`, e.g. "length: string"
5. ***Really Really Optional***
    * What happens if you use `concatMap` instead of `flatMap`? Why?
