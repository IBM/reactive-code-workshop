# Simple Akka Java Base Project

A simple base project to allow exploration of Akka

## Compile

```console
$ mvn package
```

## Run

```console
$ mvn exec:java
```

## References to have handy

Yay for tabbed browsers!

* [Akka Operators](https://doc.akka.io/docs/akka/2.5/stream/operators/index.html)
* [Akka Source Javadoc](https://doc.akka.io/japi/akka/current/akka/stream/javadsl/Source.html)

## Exercises

Your goal for each section is to achieve the required bits in the time allotted (for the lab). Do the extras if you finish early, or on your own time later (we hope you get as obsessed with doing them as we did).

At the end of each step, you should use `dumpSourceToStdOut` to print the result. In some cases, this will need some type munging to get back to the right type; this is intentional, but here are some hints:

* Use `.alsoTo(getDebugSink())` between operations if you get confused.
* A cheap way to convert something to a string is with `.map(x -> "" + x)`

*Note:* Akka doesn't have `distinct` but maybe you can create your own version.

### Exercise 1: map, filter, merge

1. process the stream of words: make them all lowercase and remove all punctuation (`[^a-zA-Z]`).
2. `filter` the stream of words to those beginning with 'b'
3. create a second stream of words containing only those beginning with 'g'
4. `merge` the two streams into a new stream of words
5. ***Optional: distinct, scan***
    * replace all words in the two current streams with their lengths.
    * use `scan` to total the number of chars in words starting 'b'
    * use `scan` to total the number of chars in `distinct` words starting 'g'
6. ***Really Really Optional: count***
    * `count` how many words started with 'b'
    * `count` how many `distinct` words started with 'g'

### Exercise 2: nested sources and substreams

#### 2a. Make lots of things!

1. process of stream of lines to split into words `Source<String[],NotUsed>`
2. process stream of lines into `Source<Source<String,NotUsed>,NotUsed>` of words

#### 2b. Collapse lots of observables back together

Make `dumpSourceToStdOut` print individual words again.

1. Use `flatMapConcat` or `flatMapMerge` to recombine list from step 1 above into `Source<String,NotUsed>`
2. Use `flatMapConcat` or `flatMapMerge` to process list from step 2 above into `Source<String,NotUsed>`

#### 2c. Grouping in Akka (subflows)

Start with stream of (optionally lowercase, punctuation-free) words:

1. Use `groupBy` to group words by first letter
2. Use `mergeSubstreams` to re-merge substreams: observe order of words
3. ***Optional: distinct***
    * Include only `distinct` words
4. ***Really Really Optional***
    * `count` how many words are in each group

#### 2d. Controlled collapse

Start with stream of (optionally lowercase, punctuation-free) words:

1. Use `map` to create a stream of string lengths
2. Use `zipWith` to combine the stream of lengths and stream of words into `Source<Pair,NotUsed>`
3. Use `map` to convert each pair to a string representation of itself
3. Use `mergeSubstreams` to show group contents as single `Source<String,NotUsed>`
4. ***Optional***
    * Use `groupBy` to group `Source<Pair,NotUsed>` by length
5. ***Really Really Optional***
    * What happens if you use `concatSubstreams` instead of `mergeSubstreams`? Why?
