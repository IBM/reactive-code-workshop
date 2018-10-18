package com.example.webflux;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;


@SpringBootApplication
public class WebFluxApplication {
    static Flux<String> fallback = Flux.just("This","is","just","an","example");

    private Flux<String> demoStrings(){
        return Flux.using(
            () -> Jabberwocky.getReader(),
            reader -> Flux.fromIterable(() -> reader.lines().iterator()),
            reader -> Jabberwocky.tryToClose(reader) // must handle the IOException in WebFlux
        ).onErrorResume(throwable -> fallback);
    }

    private Flux<Integer> demoInts(){
       return Flux.range(0,100);
    }

    private void dumpFluxToStdOut(Flux<String> f){
        f.subscribe(s->System.out.println(s));
    }

    @Bean
    public CommandLineRunner myCommandLineRunner() {
        return args -> {
            Flux<String> output = demoStrings();
            dumpFluxToStdOut(output);
        };
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebFluxApplication.class);
        // prevent SpringBoot from starting a web server
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}
