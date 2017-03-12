package fii.practic.spiders.vimeo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * DTO representing the video from the Vimeo data structure.
 */
public class VimeoVideo {
    @JsonProperty("clip_id")
    private long clipId;

    private Map<String, String> thumbnail;

    private ClipDuration duration;

    private String title;

    private String url;

    public long getClipId() {
        return clipId;
    }

    public Map<String, String> getThumbnail() {
        return thumbnail;
    }

    public ClipDuration getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "VimeoVideo{" +
                "clipId=" + clipId +
                ", thumbnail=" + thumbnail +
                ", duration=" + duration +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
