# Simple Rx Java Base Project

A simple base project to allow exploration of RXJava2

## Compile

```console
$ mvn package
```

## Run

Use either:

```console
$ mvn exec:exec
$ java -jar target/rxjava-base-1.0-SNAPSHOT.jar
```

## References to have handy

Yay for tabbed browsers!

* [ReactiveX Operators](http://reactivex.io/documentation/operators.html)
* [RxJava2 Observable Javadoc](http://reactivex.io/RxJava/2.x/javadoc/2.0.8/io/reactivex/Observable.html)

## Exercises

We will be exploring reactive concepts by playing with Observable Operator composition.

Open `src/main/java/com/example/RxApplication.java` in your IDE. We will edit the `run()` method to mess about with Observables and Operators.

### Goal

Complete as many of the steps within each Exercise as you can within the time alloted.

* Steps within an exercise are related (later steps often require earlier steps, e.g.)
* At the end of each step, use `dumpObservableToStdOut` to print the result.
**This will need some type munging**; this is intentional.

### Hints

* Use `.doOnNext(getDebugConsumer())` between operations if you get confused.
* A cheap way to convert something to a string is with `.map(x -> "" + x)`

### Exercise 1: map, filter, merge

Start with stream of `words()`:

1. Use `map` to make all words lowercase and remove all punctuation (`[^a-zA-Z]`). 

    For example, in `src/main/java/com/example/RxApplication.java`, edit the `run()` and add the following:

    ```java
        // Exercise 1:
        Observable<String> ex_1_1 = words()
            .map(word -> word.toLowerCase().replaceAll("^[a-zA-Z]",""));
        dumpObservableToStdOut(ex_1_1);
    ```

    Use ` mvn package exec:exec` from the command line to rebuild and run (run inside your IDE of choice if you prefer). The poem Jabberwocky should now be followed by a list of punctuation-free, lower case words. If so, carry on and see how far you can get (use hints above and listed references), **stop before Exercise 2**.

    If you are stuck, ask for help. ;)

2. Apply `filter` create stream of words beginning with `b`
3. Create a second stream of words beginning with `g`
4. `merge` the two streams
5. ***Optional: distinct, scan***
    * replace all words in the two current streams with their lengths.
    * use `scan` to find the number of chars in words starting `b`
    * use `scan` to find the number of chars in `distinct` words starting `g`
6. ***Really really optional: count***
    * `count` all `b` words
    * `count` `distinct` `g` words

    Note: If you try these, you'll notice that count produces an incompatible type for `dumpObservableToStdOut`, which means you'll need to find another way to subscribe to that observable to see what happens.

### Exercise 2: nested observables and substreams

#### 2a. Make lots of observables

Start with stream of `lines()`:

1. process of stream of lines to split into words `Observable<String[]>`
2. process stream of lines into `Observable<Observable<String>>` of words

#### 2b. Collapse lots of observables back together

Make `dumpObservableToStdOut` print individual words again.

1. Use `flatMap` to recombine list from step 1 above
2. Use `flatMap` to recombine list from step 2 above

#### 2c. Group lots of observables

Start with stream of (optionally lowercase, punctuation-free) `lines()`:

1. Use `groupBy` to group words by first letter
2. Use `flatMap` to re-merge: observe order of words
3. ***Optional: distinct***
    * Include only `distinct` words
4. ***Really really optional***
    * `count` how many words are in each group

#### 2d. Controlled collapse

Start with stream of (optionally lowercase, punctuation-free) words:

1. Use `map` make a stream of string lengths
2. Use `zipWith` to combine streams (lengths and words) into `Observable<Pair>`
3. Use `map` to show pairs as single `Observable<String>`, e.g. "length: string"
4. ***Optional***
    * Use `groupBy` to group `Observable<Pair>` by length
    * Use `flatMap` to show content as single as single `Observable<String>`, e.g. "length: string"
5. ***Really really optional***
    * What happens if you use `concatMap` instead of `flatMap`? Why?
