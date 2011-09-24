package controllers;

import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

public class Articles extends Controller {

    @Before
    static void addDefaults(){
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void show(String title) {
        System.out.println(title);
//        Article article = Article.find("byTitle", title.replace('_', ' ')).first();
//        article.article_view += 1;
//        article.save();
//        render(article);
    }

}