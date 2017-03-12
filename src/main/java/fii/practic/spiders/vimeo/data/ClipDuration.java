package fii.practic.spiders.vimeo.data;

/**
 * Vimeo clip duration.
 * It contains the data in seconds and as formatted string.
 */
public class ClipDuration {
    private int raw;
    private String formatted;

    public int getRaw() {
        return raw;
    }

    public String getFormatted() {
        return formatted;
    }

    @Override
    public String toString() {
        return "ClipDuration{" +
                "raw=" + raw +
                ", formatted='" + formatted + '\'' +
                '}';
    }
}
