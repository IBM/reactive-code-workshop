package com.example;

import java.util.Arrays;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

public class AkkaApplication {

    private Source<String,NotUsed> demoStrings(){
        return Source.from(Arrays.asList("This","is","just","an","example"));
    }

    private Source<Integer,NotUsed> demoInts(){
        return Source.range(0,100);
    }

    private void dumpSourceToStdOut(Source<?,NotUsed> f) throws InterruptedException, ExecutionException {
        final ActorSystem system = ActorSystem.create("QuickStart");
        final Materializer materializer = ActorMaterializer.create(system);

        final CompletionStage<Done> done = f.runWith(Sink.foreach(a -> System.out.println(a)),materializer);

        done.thenRun(()->system.terminate());
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
