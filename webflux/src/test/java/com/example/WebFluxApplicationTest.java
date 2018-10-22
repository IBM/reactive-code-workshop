package com.example;

import org.junit.Test;

import reactor.core.publisher.Flux;

import static org.junit.Assert.assertEquals;

public class WebFluxApplicationTest {
    @Test
    public void testRuntime() throws Exception {
        StringBuilder sb = new StringBuilder();
        Flux.just("This","is","just","an","example").subscribe(s -> sb.append(s));
        assertEquals("Runtime Failure", "Thisisjustanexample", sb.toString());
    }
}
