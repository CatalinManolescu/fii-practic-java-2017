package fii.practic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Application configuration.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "fii.practic.domain.repository")
@ComponentScan(basePackages = {"fii.practic.domain.control"})
public class SpiderConfig {

}
