package fii.practic.spiders.youtube;

import fii.practic.commons.Spider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Youtube scrapper implementation.
 */
public class Youtube implements Spider {
    private static final Logger log = LoggerFactory.getLogger(Youtube.class);

    public static final String BASE_URL = "https://www.youtube.com";

    @Override
    public String getName() {
        return "Youtube";
    }

    @Override
    public void start() throws Exception {
        log.debug("[STARTED] " + this.getName());
    }
}
