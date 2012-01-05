package controllers;

import models.User;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

public class MyCubes extends Controller{

    @Before
    static void addDefaults(){
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void index() {
        if(session.isEmpty() == false){
            System.out.println("I am here");
            redirect("/authenticate/index");
        }
        User user = User.find("byUserAliasAndUserEmail", session.get("userAlias"), session.get("userEmail")).first();
        render(user);
    }
}
