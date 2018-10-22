package com.example;

import java.util.Arrays;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import static org.junit.Assert.assertEquals;

public class AkkaApplicationTest {

    private String dumpSourceToString(Source<?,NotUsed> f) throws InterruptedException, ExecutionException {
        final ActorSystem system = ActorSystem.create("QuickStart");
        final Materializer materializer = ActorMaterializer.create(system);

        StringBuilder s = new StringBuilder();
        final CompletionStage<Done> done = f.runWith(Sink.foreach(a -> s.append(a)),materializer);

        done.thenRun(()->system.terminate());
        done.toCompletableFuture().get();

        return s.toString();
    }

    @Test
    public void testRuntime() throws Exception{
        String result = dumpSourceToString(Source.from(Arrays.asList("This","is","just","an","example")));
        assertEquals("Runtime Failure", "Thisisjustanexample", result);
    }
}
