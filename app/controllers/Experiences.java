package controllers;

import models.*;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Experiences extends Controller {

    static List<String> qs = Arrays.asList("Your opportunities for professional growth at ThoughtWorks?",
            "Your opportunities for career advancement within ThoughtWorks?",
            "Your compensation compared with similar jobs elsewhere?",
            "Your benefits package compared with similar employers?",
            "Information and knowledge sharing within ThoughtWorks?",
            "Communications from management about important issues and changes?",
            "ThoughtWorks as a place you would recommend to others to work?",
            "ThoughtWorks as a place you are proud to work?",
            "Feedback you receive about your job performance?",
            "Recognition and praise you receive when you do a good job?",
            "Leadership abilities of Senior management?",
            "Competence of Senior management?",
            "Management support in permitting time off when you think it's necessary?",
            "Employer support in balancing between work life and personal life?",
            "Fairness in how promotions are given and people are treated?",
            "The level of respect shown by management toward employees?",
            "Overall, how satisfied are you with ThoughtWorks as a place to work?");

    @Before
    static void addDefaults(){
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void index() {
        List<Company> companies = Company.findAll();
        List<Company> topRated = getTopRated(companies);
        List<Article> topViewed = Application.getTopViewedArticles();
        render(companies, topRated, topViewed);
    }

    private static List<Company> getTopRated(List<Company> companies) {
        double ratingSum = 0;
        Iterator<Company> companyIterator = companies.iterator();
        while(companyIterator.hasNext()){
            Company company = (Company)companyIterator.next();
            for (Integer i=0; i < company.companyRatings.size(); i++){
                ratingSum += company.companyRatings.get(i).rating;
            }
            DecimalFormat twoDecimal = new DecimalFormat("#.##");
            if(ratingSum > 0 && company.companyRatings.size() > 0){
               double ratingPecentage = Double.valueOf(twoDecimal.format(ratingSum/company.companyRatings.size()));
            }
        }

        return null;
    }

    public static void postExperience(String orgName) {
        List<Company> companies = Company.findAll();
        List<Company> topRated = getTopRated(companies);
        List<Article> topViewed = Application.getTopViewedArticles();
        List<CubeQuestion> cubeQuestions = CubeQuestion.find("order by id asc").fetch();
        Company company = Company.find("byOrgName", orgName).first();
        render(cubeQuestions, company, topRated, topViewed);
    }

    public static void yourSay(String orgName){
        Company company = Company.find("byOrgName", orgName).first();
        if(session.contains("loggedIn") || session.contains("anonymousUserId")){
            render("/Experiences/review.html",company,session.get("anonymousUserId"));
        }
        render("/Experiences/yoursay.html",company);
    }

    public static void saveUserInfo(){
        System.out.println(Http.Request.current().params.get("jobStatus"));
        System.out.println(Http.Request.current().params.get("jobTitle"));
        Company company = Company.findById(Long.parseLong(Http.Request.current().params.get("employerId")));
        getUser();
        render("/Experiences/review.html",company,session.get("anonymousUserId"));
    }

    private static void review() {
    }

    private static void getUser() {
        AnonymousUser anonymousUser = null;
        if(!session.contains("loggedIn")){
            if(!session.contains("anonymoususerId")){

                anonymousUser = new AnonymousUser(request.params.get("employerCityName"),
                        request.params.get("employerCountryName"), request.params.get("jobStatus"),
                        request.params.get("jobTitle"), request.params.get("jobEndingYear"),
                        request.remoteAddress.toString(), null).save();

                session.put("anonymousUserId", anonymousUser.id);
            }

        }
        else if(session.contains("loggedIn")){
            User user = User.find("userEmail", session.get("userEmail")).first();
            anonymousUser = new AnonymousUser(request.params.get("employerCityName"),
                    request.params.get("employerCountryName"), request.params.get("jobStatus"),
                    request.params.get("jobTitle"), request.params.get("jobEndingYear"),
                    request.remoteAddress.toString(), user).save();

            session.put("anonymousUserId", anonymousUser.id);
        }
    }

    public static void saveReview(){
        Company company = Company.findById(Long.parseLong(Http.Request.current().params.get("employerId")));
        AnonymousUser anonymousUser = AnonymousUser.find("id", Long.parseLong(session.get("anonymousUserId"))).first();
        List<CubeQuestion> cubeQuestions = CubeQuestion.find("order by id asc").fetch();
        CubeReview cubeReview = new CubeReview(Http.Request.current().params.get("reviewType"),company, Http.Request.current().params.get("headlineAnswer"),
                Http.Request.current().params.get("proAnswer"), Http.Request.current().params.get("conAnswer"),
                Http.Request.current().params.get("adviceAnswer"), anonymousUser, true).save();

        render("/Experiences/rating.html",company, anonymousUser, cubeQuestions);

    }

    public static void saveRating(){
        Company company = Company.find("Id", Long.parseLong(request.params.get("companyId"))).first();
        AnonymousUser anonymousUser = AnonymousUser.find("id", Long.parseLong(session.get("anonymousUserId"))).first();
        List<CubeQuestion> cubeQuestions = CubeQuestion.find("order by id asc").fetch();

        for(CubeQuestion question : cubeQuestions){
            if(request.params.get("a"+question.id)!=null){
                new CubeRating(anonymousUser, company, Integer.parseInt(request.params.get("quest"+question.id)), Integer.parseInt(request.params.get("a"+question.id))).save();
            }
        }

        flash.put("reviewStatus","Success");

        render("/Experiences/saved.html");
    }

    public static void showpage(){
        render("/Experiences/saved.html");
    }
}