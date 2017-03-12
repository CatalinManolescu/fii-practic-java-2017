package fii.practic.spiders.vimeo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO representing an module item from the Vimeo data structure.
 */
public class VimeoModuleItem {
    private int uniqid;

    private String type;

    @JsonProperty("stream_id") //make relation between JSON item and Java property
    private String streamId;

    @JsonProperty("collection_items") //make relation between JSON item and Java property
    private List<VimeoVideo> items;

    private String url;

    public int getUniqid() {
        return uniqid;
    }

    public String getType() {
        return type;
    }

    public String getStreamId() {
        return streamId;
    }

    public List<VimeoVideo> getItems() {
        return items;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "VimeoModuleItem{" +
                "uniqid=" + uniqid +
                ", type='" + type + '\'' +
                ", streamId='" + streamId + '\'' +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
