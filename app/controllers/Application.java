package controllers;

import models.*;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.List;

public class Application extends Controller {

    @Before
    static void addDefaults(){
//        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void index() {
        List<Article> homePagePost = Article.find("order by submit_date desc").fetch(5);

        List<Article> homePageArticleList = Article.find("order by article_view desc").fetch();
        Company homeCompany = Company.findHomeCompany();
//        Company recentReview = Company.findLatestReview();
        List<CubeReview> cubeReviewsList = CubeReview.find("order by created_on desc").fetch();
        List<CubeRating> cubeRatings = homeCompany.getCompanyRatings();
        String ratingGraphData = CubeRating.getGraphData(cubeRatings);
        render(homePagePost, ratingGraphData, cubeRatings, homePageArticleList, homeCompany, cubeReviewsList);
    }

    public static void show(Long id) {
        Article article = Article.findById(id);
        render(article);
    }

    public static void showArticle(String title) {
        Article article = Article.find("byTitle", title).first();
        render(article);
    }

    public static void postComment(Long id, String content, String userAlias){
        Article article = Article.findById(id);
        User user = User.find("byUserEmailAndUserAlias", session.get("userEmail"),session.get("userAlias")).first();
        article.addComment(article.id,user,content);
        System.out.println("Comment Posted");
    }

//    public static void signIn(String userEmail, String userPassword){
//        System.out.println(userEmail + "-" + userPassword);
//    }

}