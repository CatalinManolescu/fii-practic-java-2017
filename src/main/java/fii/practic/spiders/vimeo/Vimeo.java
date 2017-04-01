package fii.practic.spiders.vimeo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fii.practic.ApplicationContextHolder;
import fii.practic.commons.Spider;
import fii.practic.domain.control.VideoControl;
import fii.practic.domain.entity.Video;
import fii.practic.domain.entity.VideoType;
import fii.practic.spiders.vimeo.data.VimeoModule;
import fii.practic.spiders.vimeo.data.VimeoModuleItem;
import fii.practic.spiders.vimeo.data.VimeoPage;
import fii.practic.spiders.vimeo.data.VimeoSearchResult;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Vimeo spider implementation.
 */
public class Vimeo implements Spider {
    private static final Logger log = LoggerFactory.getLogger(Vimeo.class);

    public static final String BASE_URL = "https://vimeo.com";

    private VideoControl videoControl;

    @Override
    public String getName() {
        return "Vimeo";
    }

    @Override
    public void start() throws Exception {
        this.videoControl = ApplicationContextHolder.getBean(VideoControl.class);
        this.processContent(BASE_URL);
    }

    /**
     * Read content from the specified URL and extract the needed information.
     *
     * @param url The URL to read from.
     */
    private void processContent(String url) throws IOException, InterruptedException, ParseException {
        log.debug("Loading data from: " + url);
        Document node = this.readPage(url);
        String nodeContent = node.body().toString();
        VimeoPage pageData = this.getData(nodeContent);

        // if pageData is null then we are at the video search page
        if (pageData == null) {
            // extract video info
            VimeoSearchResult searchResult = this.getSearchResultData(nodeContent);
            if (searchResult != null) {
                // save the data
                this.saveData(searchResult.getFiltered().getData());

                // keep this while testing
                if (searchResult.getFiltered().getPage() > 2) {
                    // stop execution
                    return;
                }

                // pause processing in order to not get blocked
                Thread.sleep(1000);

                // process next page
                VimeoSearchResult.Paging paging = searchResult.getFiltered().getPaging();
                if (!StringUtils.isBlank(paging.getNext())) {
                    // check for and clean the query params
                    int queryParamsIndex = url.indexOf('?');
                    if (queryParamsIndex > -1) {
                        url = url.substring(0, queryParamsIndex);
                    }

                    this.processContent(url + paging.getNext());
                }
            }

            return ;
        }

        // navigate through categories
        for (VimeoModule module : pageData.getModules()) {
            if ("category_menu".equals(module.getType())) {
                for (VimeoModuleItem moduleItem : module.getItems()) {
                    // pause processing in order to not get blocked
                    Thread.sleep(500);
                    this.processContent(BASE_URL + moduleItem.getUrl());

                    // keep this while testing
                    break; // stop execution
                }
                return ;
            }
        }
    }

    private void saveData(List<VimeoSearchResult.ResultItem> resultItemList) {
        List<Video> newVideos = new ArrayList<>();

        Video video;
        for (VimeoSearchResult.ResultItem item : resultItemList) {
            video = new Video();
            VimeoSearchResult.VimeoClip clip = item.getClip();

            video.setVideoType(this.getVideoType(item.getType()));
            video.setVideoSource(this.getName());
            video.setName(clip.getName());
            video.setUrl(clip.getLink());

            // TODO get the remaining data

            newVideos.add(video);
        }

        videoControl.save(newVideos);
    }

    private VideoType getVideoType(String type) {
        VideoType videoType = VideoType.lookup(type);

        if (videoType == null) {
            videoType = VideoType.UNKNOWN;
        }

        return videoType;
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
    private VimeoPage getData(String content) throws IOException, ParseException {
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
            throw new ParseException("Could not identify data source", -1);
        }

        String rowData = content.substring(startIndex, endIndex);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(rowData, VimeoPage.class);
    }

    private VimeoSearchResult getSearchResultData(String content) throws IOException {
        // We use plain string manipulation to extract the data from the page
        String dataStart = "var data = ";
        String dataEnd = "}};";

        int startIndex = this.computeIndex(content.indexOf(dataStart), dataStart);

        if (startIndex == -1) {
            // this is not a category page so there is no need to continue
            return null;
        }

        content = content.substring(startIndex);
        int endIndex = this.computeIndex(content.indexOf(dataEnd), dataEnd);

        String rowData = content.substring(0, endIndex);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper.readValue(rowData, VimeoSearchResult.class);
    }

    private int computeIndex(int startIndex, String needle) {
        int index = startIndex;
        if (startIndex != -1) {
            index += needle.length();
        }

        return index;
    }

}
