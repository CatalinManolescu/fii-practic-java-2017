package fii.practic.domain.entity;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple enum for defining video types.
 */
public enum VideoType {
    MOVIE,
    CLIP,
    UNKNOWN;


    private static final Map<String, VideoType> BUCKET = new HashMap<>();

    static {
        for (VideoType value : VideoType.values()) {
            BUCKET.put(value.name().toLowerCase(), value);
        }

    }

    public static VideoType lookup(String type) {
        if (StringUtils.isBlank(type)) {
            throw new NullPointerException("'type' cannot be null.");
        }

        return BUCKET.get(type.toLowerCase());
    }
}
