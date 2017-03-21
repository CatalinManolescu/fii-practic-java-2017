package fii.practic;

import fii.practic.commons.SpiderRunnerFactory;
import fii.practic.spiders.vimeo.Vimeo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class SpiderApplication {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
		SpringApplication.run(SpiderApplication.class, args);

        try {
            executor.submit(SpiderRunnerFactory.createSpiderRunner(Vimeo.class));
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
