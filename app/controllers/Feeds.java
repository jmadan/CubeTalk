package controllers;

import models.RSSReader;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

public class Feeds extends Controller{

    @Before
    static void addDefaults(){
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void show() {
//        SyndFeed feed = null;
//        try{
//            URL url = new URL("http://feeds.bbci.co.uk/news/technology/rss.xml");
//
//            SyndFeedInput input = new SyndFeedInput();
//
//            feed = input.build(new XmlReader(url));
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//            System.out.println(ex.getMessage());
//        }
//        String response = WS.url("http://newsrss.bbc.co.uk/rss/newsonline_world_edition/front_page/rss.xml").get().getString();
//        RSSReader rssReader = new RSSReader();
//        RssFeedReader rssFeedReader = new RssFeedReader("http://feeds.feedburner.com/TechCrunch/");
//        Feed feed = rssFeedReader.readFeed();

        new RSSReader().readRss();
        render();
    }
}