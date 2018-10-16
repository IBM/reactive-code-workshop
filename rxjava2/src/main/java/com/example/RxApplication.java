package com.example;

import io.reactivex.Observable;

public final class RxApplication {

    private Observable<String> demoStrings(){
		return Observable.just("This","is","just","an","example");
    }

    private Observable<Integer> demoInts(){
        return Observable.range(0,100);
    }

    private void dumpObservableToStdOut(Observable<?> f){
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
