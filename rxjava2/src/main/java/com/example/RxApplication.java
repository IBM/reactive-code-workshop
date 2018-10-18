package com.example;

import io.reactivex.Observable;

public final class RxApplication {
    static Observable<String> fallback = Observable.just("This","is","just","an","example");

    private Observable<String> demoStrings() {
        return Observable.using(
            () -> Jabberwocky.getReader(),
            reader -> Observable.fromIterable(() -> reader.lines().iterator()),
            reader -> reader.close()  // potential IOException is caught
        ).onErrorResumeNext(throwable -> fallback);
    }

    private Observable<Integer> demoInts(){
       return Observable.range(0,100);
     }

    private void dumpObservableToStdOut(Observable<String> f){
        f.subscribe(s->System.out.println(s));
    }

    private void run(){
        Observable<String> result = demoStrings();
        dumpObservableToStdOut(result);
    }

    public static void main(String[] args) throws Exception{
      RxApplication app = new RxApplication();
      app.run();
    }
}
