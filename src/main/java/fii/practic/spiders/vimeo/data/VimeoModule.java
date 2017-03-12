package fii.practic.spiders.vimeo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO representing an module from the Vimeo data structure.
 */
public class VimeoModule {
    private int uniqid;

    private String type;

    @JsonProperty("stream_id") //make relation between JSON item and Java property
    private String streamId;

    @JsonProperty("collection_items") //make relation between JSON item and Java property
    private List<VimeoModuleItem> items;

    public int getUniqid() {
        return uniqid;
    }

    public String getType() {
        return type;
    }

    public String getStreamId() {
        return streamId;
    }

    public List<VimeoModuleItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "VimeoModule{" +
                "uniqid=" + uniqid +
                ", type='" + type + '\'' +
                ", streamId='" + streamId + '\'' +
                '}';
    }
}
