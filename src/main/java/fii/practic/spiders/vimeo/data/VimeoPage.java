package fii.practic.spiders.vimeo.data;

import java.util.List;

/**
 * DTO representing the root object of the Vimeo data structure.
 */
public class VimeoPage {
    private List<VimeoModule> modules;

    public List<VimeoModule> getModules() {
        return modules;
    }

    @Override
    public String toString() {
        String page = "VimeoPage[";

        for (VimeoModule module : modules) {
            page += module + ",";
        }

        // remove last comma
        page = page.substring(0, page.length() -1);

        return page + "]";
    }
}
