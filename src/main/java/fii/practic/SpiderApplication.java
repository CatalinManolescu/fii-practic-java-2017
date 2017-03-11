package fii.practic;

import fii.practic.commons.Spider;
import fii.practic.spiders.vimeo.Vimeo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiderApplication.class, args);
        Spider spider = new Vimeo();

        try {
            spider.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
