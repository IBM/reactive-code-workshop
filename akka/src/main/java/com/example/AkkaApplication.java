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
    Stream<String> jabberwocky = Jabberwocky.getReader().lines();

    private Source<String,NotUsed> demoStrings(){
        return Source.fromIterator(() -> jabberwocky.iterator());
    }

    private Source<Integer,NotUsed> demoInts(){
        return Source.range(0,100);
    }

    private void dumpSourceToStdOut(Source<?,NotUsed> src) throws InterruptedException, ExecutionException {
        final ActorSystem system = ActorSystem.create("QuickStart");
        final Materializer materializer = ActorMaterializer.create(system);

        final CompletionStage<Done> done = src.runWith(Sink.foreach(a -> System.out.println(a)),materializer);

        done
        .thenRun(()->system.terminate())
        .thenRun(()->jabberwocky.close());

        // Make it happen
        done.toCompletableFuture().get();
    }

    private void run() throws Exception{
        dumpSourceToStdOut(demoStrings());
    }

    public static void main(String[] args) throws Exception{
        AkkaApplication app = new AkkaApplication();
        app.run();
      }
}
