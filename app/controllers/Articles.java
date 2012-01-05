package controllers;

import models.*;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.List;

public class Articles extends Controller {

    @Before
    static void addDefaults(){
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void show(String title) {
        Article article = Article.find("title", title.replace('-', ' ')).first();
        article.article_view += 1;
        article.save();

        List<Article> topViewed = Application.getTopViewedArticles();
        Company homeCompany = Company.find("orgName", "ThoughtWorks").first();
        List<CubeReview> cubeReviewsList = CubeReview.find("order by created_on desc").fetch();
        List<CubeRating> cubeRatings = homeCompany.getCompanyRatings();

        render(article, cubeReviewsList, cubeRatings, topViewed);
    }

    public static void index(int page){
        if(page == 0){
            page = 1;
        }
        List<Article> articlesSize = Article.find("order by submit_date desc").fetch();
        int noOfPages = (int) Math.ceil((double)articlesSize.size()/10);
        List<Article> articles = Article.find("order by submit_date desc").fetch(page, 10);
        List<CubeReview> cubeReviewsList = CubeReview.find("order by created_on desc").fetch();
        List<Article> topViewed = Application.getTopViewedArticles();
        render(articles, page, noOfPages, topViewed, cubeReviewsList);
    }

    public static void postComment(Long id, String content, String userAlias){
        Article article = Article.findById(id);
        User user = User.find("byUserEmailAndUserAlias", session.get("userEmail"),session.get("userAlias")).first();
        article.addComment(article.id,user,content);
    }

    public Article previous() {
        return Article.find("submit_date < ? order by submit_date desc").first();
    }

    public Article next() {
        return Article.find("submit_date > ? order by submit_date asc").first();
    }

}