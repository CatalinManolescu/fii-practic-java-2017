package fii.practic.spiders.vimeo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fii.practic.commons.Spider;
import fii.practic.spiders.vimeo.data.VimeoModule;
import fii.practic.spiders.vimeo.data.VimeoModuleItem;
import fii.practic.spiders.vimeo.data.VimeoPage;
import javassist.NotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;

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
    public void start() throws Exception {

        this.processContent(BASE_URL);
    }

    /**
     * Read content from the specified URL and extract the needed information.
     *
     * @param url The URL to read from.
     */
    private void processContent(String url) throws IOException, InterruptedException, NotFoundException {
        Document node = this.readPage(url);
        String nodeContent = node.body().toString();
        VimeoPage pageData = this.getData(nodeContent);

        // if pageData is null then we are at the
        if (pageData == null) {
            // extract video info
            // TODO read all video pages and extract the video data

            // pause processing in order to not get blocked (should be done for each video page)
            Thread.sleep(2000);
            return ;
        }

        for (VimeoModule module : pageData.getModules()) {
            if ("category_menu".equals(module.getType())) {
                for (VimeoModuleItem moduleItem : module.getItems()) {
                    this.processContent(BASE_URL + moduleItem.getUrl());
                }
                return ;
            }
        }
    }

    /**
     * Setup connection and read page.
     */
    private Document readPage(String url) throws IOException {
        return Jsoup.connect(url).timeout(10000).get();
    }

    /**
     * Extract data from page.
     */
    private VimeoPage getData(String content) throws IOException, NotFoundException {
        // We use plain string manipulation to extract the data from the page
        String dataStart = "vimeo.explore_data = ";
        String pageTypeExplore = "\"page_type\":\"explore\"}";
        String pageTypeCategory = "\"page_type\":\"category\"}";

        int startIndex = this.computeIndex(content.indexOf(dataStart), dataStart);

        if (startIndex == -1) {
            // this is not a category page so there is no need to continue
            return null;
        }

        int endIndex = this.computeIndex(content.indexOf(pageTypeExplore), pageTypeCategory);

        if (endIndex == -1) {
            // pageTypeExplore value not found.
            // check for the next value
            endIndex = this.computeIndex(content.lastIndexOf(pageTypeCategory), pageTypeCategory);
        }

        if (endIndex == -1) {
            // if we get here then the page content has changed or there is a type that we do not check for
            throw new NotFoundException("Could not identify data source");
        }

        String rowData = content.substring(startIndex, endIndex);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(rowData, VimeoPage.class);
    }

    private int computeIndex(int startIndex, String needle) {
        int index = startIndex;
        if (startIndex != -1) {
            index += needle.length();
        }

        return index;
    }

}
