package com.example;

import io.reactivex.Observable;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RxApplicationTest {
    @Test
    public void testRuntime() throws Exception {
        StringBuilder sb = new StringBuilder();
        Observable.just("This","is","just","an","example").subscribe(s -> sb.append(s));
        assertEquals("Runtime Failure", "Thisisjustanexample", sb.toString());
    }
}
