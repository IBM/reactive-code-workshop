package com.example;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public final class RxApplication {

    private Observable<String> lines() {
        return Observable.fromIterable(() -> Jabberwocky.lines().iterator());
    }

    private Observable<String> words() {
        return Observable.fromIterable(() -> Jabberwocky.words().iterator());
    }

    private void dumpObservableToStdOut(Observable<String> f){
        f.subscribe(s->System.out.println(s));
    }

    private List<String> getAsList(Observable<String> f) {
         return f.toList().blockingGet();
    }

    private void run(){
        // This is the starting example
        Observable<String> result = lines();
        // This will both _subscribe_ to the observable, and emit the result
        dumpObservableToStdOut(result);
    }

    public static void main(String[] args) throws Exception{
      RxApplication app = new RxApplication();
      app.run();
    }

    /**
     * .doOnNext(getDebugConsumer())
     * @return a debug consumer that prints the element it is passed
     */
    private <T> Consumer<T> getDebugConsumer() {
        return new Consumer<T>() {
            @Override
            public void accept(T t) throws Exception {
                System.out.println(t);
            }
        };
    }
}
