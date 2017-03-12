package fii.practic.commons;

/**
 * Base interface for all spiders.
 */
public interface Spider {
    String getName();
    void start() throws Exception;
}
