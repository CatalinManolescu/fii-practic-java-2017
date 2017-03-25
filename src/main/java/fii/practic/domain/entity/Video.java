package fii.practic.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Video data that will be persisted.
 */
@Document(indexName = "spider", type = "video")
public class Video {
    @Id
    private String id;
    private String name;
    private VideoType videoType;
    private String url;
    private String embeded;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmbeded() {
        return embeded;
    }

    public void setEmbeded(String embeded) {
        this.embeded = embeded;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", videoType=" + videoType +
                ", url='" + url + '\'' +
                '}';
    }
}
