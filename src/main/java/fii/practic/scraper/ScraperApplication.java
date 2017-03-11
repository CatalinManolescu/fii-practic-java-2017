package fii.practic.scraper;

import java.util.Timer;

/**
 * Created by xR86 on 11-Mar-17.
 */
public class ScraperApplication {
    public static void main(String[] args){
        Timer taskTimer = new Timer();
        ScraperTask scrapeTask = new ScraperTask();

        // This task is scheduled to run every 30 seconds
        taskTimer.scheduleAtFixedRate(scrapeTask, 0, 30000); //30000(task timeout) > 6000(get timeout)
    }
}
