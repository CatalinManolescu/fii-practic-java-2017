package fii.practic.scraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import fii.practic.utils.OutputHelpers;
//import static fii.practic.utils.OutputHelpers.getUpToNCharacters;

import java.io.IOException;

/**
 * Created by xR86 on 11-Mar-17.
 */
public class ScraperApplication {
    public static void main(String[] args) {
        Document vimeoDocument = null;
        OutputHelpers ot = new OutputHelpers();

        try {
            vimeoDocument = Jsoup.connect("https://vimeo.com/").timeout(6000).get();


            System.out.println( ot.getUpToNCharacters(vimeoDocument.toString(), 100) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n----------------\n");
        //Elements vimeoElements = vimeoDocument.select("vimeo.explore_data");
        //System.out.println(vimeoElements);

        String rawPage = vimeoDocument.body().toString();

        String queryStart = "vimeo.explore_data = "; //start of js variable
        String queryEnd   = "\"explore\"}";          //end of js variable
        int startIndex = rawPage.indexOf(queryStart) + queryStart.length();
        int endIndex   = rawPage.indexOf(queryEnd) + queryEnd.length();

        String vimeoJsonString = rawPage.substring(startIndex, endIndex);
        //System.out.println(vimeoJsonString);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode vimeoJson = mapper.readTree(vimeoJsonString);
            System.out.println( ot.getUpToNCharacters(vimeoJson.toString(), 100) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
