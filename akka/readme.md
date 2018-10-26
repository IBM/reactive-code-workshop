# Simple Akka Java Base Project

A simple base project to allow exploration of Akka

## Compile and run

```console
$ cd akka
$ mvn package exec:java
```

## References to have handy

Yay for tabbed browsers!

* [Akka Operators](https://doc.akka.io/docs/akka/2.5/stream/operators/index.html)
* [Akka Source Javadoc](https://doc.akka.io/japi/akka/current/akka/stream/javadsl/Source.html)

## Exercises

We will be exploring reactive concepts by playing with Observable operator composition.

* Open `src/main/java/com/example/AkkaApplication.java` in your IDE.
* Edit the `run()` method to complete the exercises below.

### Goal

Complete as many of the steps within each Exercise as you can (either within the allotted time, or without looking for help outside of the above references).

* Steps within an exercise are related (later steps often require earlier steps, e.g.)
* At the end of each step, use `dumpSourceToStdOut` to print the result.
**This will need some type munging**; this is intentional.

### Hints

* Use `.alsoTo(getDebugSink())` between operations if you get confused.
* A cheap way to convert something to a string is with `.map(x -> "" + x)`

### Exercise 1: map, filter, merge

1. Use `map` to make all words lowercase and remove all punctuation (`[^a-zA-Z]`).

    For example, in `src/main/java/com/example/AkkaApplication.java`, edit the `run()` and add the following:

    ```java
        // Exercise 1:
        System.out.println("=== exercise 1.1");
        Source<String, NotUsed> src_1_1 = words();
        dumpSourceToStdOut(src_1_1);
    ```

    Use `mvn package exec:java` from the command line to rebuild and run (run inside your IDE of choice if you prefer). The poem Jabberwocky should now be followed by a list of punctuation-free, lower case words.

2. Apply `filter` to create a stream of words beginning with `b`
3. Create a second stream of words beginning with `g`
4. `merge` the two streams
5. ***Optional: distinct, scan***
    * replace all words in the two current streams with their lengths.
    * use `scan` to total the number of chars in words starting `b`
    * use `scan` to total the number of chars in `distinct` words starting `g`
6. ***Really Really Optional: count***
    * `count` how many words started with 'b'
    * `count` how many `distinct` words started with 'g'

    *Note:* Akka doesn't have `distinct` but you can use other operations or tricks to create your own version.

### Exercise 2: nested sources and substreams

#### 2a. Make lots of things!

Start with the stream of lines (available from the `lines()` method):

1. split each line in the stream into an array of words, e.g.  `Source<String[],NotUsed>`
2. split each line in the stream into a stream of words, e.g. `Source<Source<String,NotUsed>,NotUsed>`

#### 2b. Collapse lots of things back together

Add operators to the stream to allow `dumpSourceToStdOut` to print the individual words again.

1. Use `flatMapConcat` or `flatMapMerge` to convert the stream from step 1 above into a stream of words (`Source<String,NotUsed>`)
2. Use `flatMapConcat` or `flatMapMerge` to convert the stream from step 2 above into a stream of words (`Source<String,NotUsed>`)

#### 2c. Grouping in Akka (subflows)

Start with a stream of (optionally lowercase, punctuation-free) `words()`:

1. Use `groupBy` to group words by first letter
2. Use `mergeSubstreams` to re-merge substreams: observe order of words
3. ***Optional: distinct***
    * Include only `distinct` words
4. ***Really Really Optional***
    * `count` how many words are in each group

#### 2d. Controlled collapse

Start with stream of (optionally lowercase, punctuation-free) `words()`:

1. Use `map` to create a stream of string lengths
2. Use `zipWith` to combine the stream of lengths and stream of words into `Source<Pair,NotUsed>`
3. Use `mergeSubstreams` to show group contents as single `Source<String,NotUsed>`, e.g. "length: string"
4. ***Optional***
    * Use `groupBy` to group `Source<Pair,NotUsed>` by length
5. ***Really Really Optional***
    * What happens if you use `concatSubstreams` instead of `mergeSubstreams`? Why?
