package fii.practic.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

/**
 * Util class for starting a spider in a separate thread.
 */
public class SpiderRunner implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(SpiderRunner.class);
    private final Spider spiderInstance;

    public SpiderRunner(@NotNull Spider spiderInstance) {
        if (spiderInstance == null) {
            throw new NullPointerException("Spider instance cannot be null.");
        }
        this.spiderInstance = spiderInstance;
    }

    @Override
    public void run() {
        log.info("[SPIDER][" + this.spiderInstance.getName() + "] STARTED");

        try {
            this.spiderInstance.start();
        } catch (Exception e) {
            log.error("[SPIDER][" + this.spiderInstance.getName() + "] failed to run.",e);
        }

        log.info("[SPIDER][" + this.spiderInstance.getName() + "] STOP");
    }
}
