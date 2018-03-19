# Kept Live Web Socket Example

### Resume sbt 

Open VS Code  
Find the `akka-streams-chirper-client` folder

sbt should still be running in the Terminal window for the akka exercises 

        > nextExercise
        > pullSolution
        > reload
        > man e 

## Description

In this final Akka Http part, we will be showing connecting to a live socket that stays open to keep getting chirps. 

In the first exercises, you'll notice that when the data is finished, the socket closes.

Here, there are some changes made to make sure the socket stays open even when there is no data.

### Step 3.a (review code)

Find the method 

        private void userActivityLiveStream(String userId)

You'll see that it is very similar to the historical chirps version that we just finished, with one exception:

        // using Source.maybe materializes into a completable future
        // which will allow us to complete the source later
        final Flow<Message, Message, CompletableFuture<Optional<Message>>> flow =
          Flow.fromSinkAndSourceMat(
            Sink.foreach(System.out::println),
            Source.maybe(),
            Keep.right());

### Step 3.b (homework: update code)

Find method 

        private Flow<Message, Entity.Chirp, NotUsed> messageToChirpFlow()

  
Back to [Reactive Events](../ReactiveEventSource.md)  
Onward to [Coda](../README.md#in-summary)
