package com.example.webflux;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class WebFluxApplication {

    private Flux<String> lines(){
        return Flux.fromIterable(() -> Jabberwocky.lines().iterator());
    }

    private Flux<String> words(){
        return Flux.fromIterable(() -> Jabberwocky.words().iterator());
    }

    private void dumpFluxToStdOut(Flux<String> f){
        f.subscribe(s->System.out.println(s));
    }

    private List<String> getAsList(Flux<String> f) {
        return f.collectList().block();
    }

    @Bean
    public CommandLineRunner myCommandLineRunner() {
        return args -> {
            Flux<String> output = lines();
            dumpFluxToStdOut(output);
        };
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebFluxApplication.class);
        // prevent SpringBoot from starting a web server
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    /**
     * .doOnNext(getDebugConsumer())
     * @return a debug consumer that prints the element it is passed
     */
    private <T> Consumer<T> getDebugConsumer() {
        return new Consumer<T>() {
            @Override
            public void accept(T t) {
                System.out.println(t);
            }
        };
    }
}
