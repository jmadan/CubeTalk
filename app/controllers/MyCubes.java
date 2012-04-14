package controllers;

import models.AnonymousUser;
import models.Article;
import models.User;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.List;

public class MyCubes extends Controller{

    @Before
    static void addDefaults(){
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void index() {
        if(session.get("userAlias") == null){
            redirect("/authenticate/index");
        }

        List<Article> myArticles = Application.getMyArticles();
        User user = User.find("byUserAliasAndUserEmail", session.get("userAlias"), session.get("userEmail")).first();
        AnonymousUser anonymousUser = AnonymousUser.find("userId", user.id).first();
//        System.out.println("anonymousUser:"+ anonymousUse);
        render(user, anonymousUser, myArticles);
    }
}
