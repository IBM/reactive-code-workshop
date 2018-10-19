package com.example;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

public class AkkaApplication {

    private Source<String,NotUsed> lines() {
        return Source.fromIterator(() -> Jabberwocky.lines().iterator());
    }

    private Source<String,NotUsed> words() {
        return Source.fromIterator(() -> Jabberwocky.words().iterator());
    }

    private void dumpSourceToStdOut(Source<?,NotUsed> src) throws InterruptedException, ExecutionException {
        final ActorSystem system = ActorSystem.create("QuickStart");
        final Materializer materializer = ActorMaterializer.create(system);

        final CompletionStage<Done> done = src.runWith(Sink.foreach(a -> System.out.println(a)),materializer);
        done.thenRun(()->system.terminate());

        // Make it happen
        done.toCompletableFuture().get();
    }

    private void run() throws Exception {
        Source<String, NotUsed> src = lines();
        dumpSourceToStdOut(src);
    }

    public static void main(String[] args) throws Exception {
        AkkaApplication app = new AkkaApplication();
        app.run();
    }

    /**
     * .alsoTo(getDebugSink())
     * @return a sink that prints all elements it has been passed
     */
    private <T> Sink<T,CompletionStage<Done>> getDebugSink() {
        return Sink.foreach(t -> System.out.println(t));
    }
}
