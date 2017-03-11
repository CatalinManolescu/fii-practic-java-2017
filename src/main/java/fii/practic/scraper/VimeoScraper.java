package fii.practic.scraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import fii.practic.utils.OutputHelpers;
//import static fii.practic.utils.OutputHelpers.getUpToNCharacters;

import java.io.IOException;

/**
 * Created by xR86 on 11-Mar-17.
 */
public class VimeoScraper {
    //Private instance variables
    private JsonNode vimeoScrapedContent;
    private int timeout;

    VimeoScraper() {
        vimeoScrapedContent = null;
        timeout = 6000;
    }

    //Set & Get
    JsonNode getVimeoScrapedContent(){
        return this.vimeoScrapedContent;
    }

    //Utility functions
    private Document getSite(String URL){
        Document vimeoDocument = null;
        OutputHelpers ot = new OutputHelpers();
        try {
            vimeoDocument = Jsoup.connect(URL).timeout(this.timeout).get();
            //System.out.println( ot.getUpToNCharacters(vimeoDocument.toString(), 100) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vimeoDocument;
    }

    void scrapeContent(){
        //OutputHelpers ot = new OutputHelpers();
        //Elements vimeoElements = vimeoDocument.select("vimeo.explore_data");
        //System.out.println(vimeoElements);

        String rawPage = getSite("https://vimeo.com/").body().toString();

        String queryStart = "vimeo.explore_data = "; //start of js variable
        String queryEnd   = "\"explore\"}";          //end of js variable
        int startIndex = rawPage.indexOf(queryStart) + queryStart.length();
        int endIndex   = rawPage.indexOf(queryEnd) + queryEnd.length();

        String vimeoJsonString = rawPage.substring(startIndex, endIndex);
        //System.out.println(vimeoJsonString);

        ObjectMapper mapper = new ObjectMapper();
        try {
            this.vimeoScrapedContent = mapper.readTree(vimeoJsonString);
            //System.out.println( ot.getUpToNCharacters(vimeoJson.toString(), 100) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
