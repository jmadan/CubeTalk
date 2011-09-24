package controllers;

import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

public class MyCube extends Controller{

    @Before
    static void addDefaults(){
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void show() {

    }
}
