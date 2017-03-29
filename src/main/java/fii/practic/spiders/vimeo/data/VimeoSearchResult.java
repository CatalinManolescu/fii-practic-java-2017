package fii.practic.spiders.vimeo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO representing the video search results.
 */
public class VimeoSearchResult {
    private FilteredResults filtered;

    public FilteredResults getFiltered() {
        return filtered;
    }

    public static class FilteredResults {
        private int total;

        private int page;

        @JsonProperty("per_page")
        private int perPage;

        private Paging paging;

        private List<ResultItem> data;

        private Context context;

        public int getTotal() {
            return total;
        }

        public int getPage() {
            return page;
        }

        public int getPerPage() {
            return perPage;
        }

        public Paging getPaging() {
            return paging;
        }

        public List<ResultItem> getData() {
            return data;
        }

        public Context getContext() {
            return context;
        }
    }

    public static class Paging {
        private String next;
        private String previous;
        private String first;
        private String last;

        public String getNext() {
            return next;
        }

        public String getPrevious() {
            return previous;
        }

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        @Override
        public String toString() {
            return "Paging{" +
                    "next='" + next + '\'' +
                    ", previous='" + previous + '\'' +
                    ", first='" + first + '\'' +
                    ", last='" + last + '\'' +
                    '}';
        }
    }

    public static class ResultItem {
        private String type;
        private VimeoClip clip;

        public String getType() {
            return type;
        }

        public VimeoClip getClip() {
            return clip;
        }
    }

    public static class VimeoClip {
        private String uri;
        private String name;
        private String link;
        private int duration;

        @JsonProperty("created_time")
        private String createdTime;

        public String getUri() {
            return uri;
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
        }

        public int getDuration() {
            return duration;
        }

        public String getCreatedTime() {
            return createdTime;
        }
    }

    public static class Context {
        private String type;
        private String category;
        private String subcategory;

        public String getType() {
            return type;
        }

        public String getCategory() {
            return category;
        }

        public String getSubcategory() {
            return subcategory;
        }

        @Override
        public String toString() {
            return "Context{" +
                    "type='" + type + '\'' +
                    ", category='" + category + '\'' +
                    ", subcategory='" + subcategory + '\'' +
                    '}';
        }
    }
}
