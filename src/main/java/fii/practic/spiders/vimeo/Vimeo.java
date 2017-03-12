package fii.practic.spiders.vimeo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fii.practic.commons.Spider;
import fii.practic.spiders.vimeo.data.VimeoPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Vimeo spider implementation.
 */
public class Vimeo implements Spider {
    private static final Logger log = LoggerFactory.getLogger(Vimeo.class);

    public static final String BASE_URL = "https://vimeo.com";

    @Override
    public String getName() {
        return "Vimeo";
    }

    @Override
    public void start() throws IOException {
        log.debug("[STARTED] " + this.getName());

        Document node = this.readPage(BASE_URL);
        VimeoPage pageData = this.getData(node);
        log.debug(pageData.toString());

        // TODO get all videos (hint: VideoModule with type "category_menu")
    }

    /**
     * Setup connection and read page.
     */
    private Document readPage(String url) throws IOException {
        return Jsoup.connect(BASE_URL).timeout(10000).get();
    }

    /**
     * Extract data from page.
     */
    private VimeoPage getData(Document page) throws IOException {
        // We use plain string manipulation to extract the data from the page
        String dataStart = "vimeo.explore_data = ";
        String dataEnd = "\"page_type\":\"explore\"}";

        String content = page.body().toString();

        int startIndex = content.indexOf(dataStart) + dataStart.length();
        int endIndex = content.indexOf(dataEnd) + dataEnd.length();

        String rowData = content.substring(startIndex, endIndex);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(rowData, VimeoPage.class);

    }
}
