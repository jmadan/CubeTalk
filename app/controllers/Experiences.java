package controllers;

import models.*;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.Arrays;
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

    public static void show() {
        List<Company> companies = Company.findAll();
        render(companies);
    }

    public static void postExperience(String orgName) {
        System.out.println(orgName);
        List<CubeQuestion> cubeQuestions = CubeQuestion.find("order by id asc").fetch();
        Company company = Company.find("byOrgName", orgName).first();
        render(cubeQuestions, company);
    }

    public static void saveReview(){

        Company company = Company.findById(Long.parseLong(Http.Request.current().params.get("employerId")));
        AnonymousUser anonymousUser = null;

        if(!session.contains("anonymousUserId")){
            anonymousUser = new AnonymousUser(Http.Request.current().params.get("job_status"),Http.Request.current().params.get("jobEndingYear"),
                Http.Request.current().params.get("job_title"), Http.Request.current().params.get("employerCountryName"),Http.Request.current().params.get("employerCityName")).save();

            session.put("anonymousUserId", anonymousUser.id);
        }
        else{
            anonymousUser = AnonymousUser.find("id", session.get("anonymousUserId")).first();
        }

        CubeReview cubeReview = new CubeReview(Http.Request.current().params.get("reviewType"),company,Http.Request.current().params.get("headlineAnswer"),
                Http.Request.current().params.get("proAnswer"),Http.Request.current().params.get("conAnswer"),
                Http.Request.current().params.get("adviceAnswer"),anonymousUser,true).save();

        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest1")), Integer.parseInt(Http.Request.current().params.get("a1"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest2")), Integer.parseInt(Http.Request.current().params.get("a2"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest3")), Integer.parseInt(Http.Request.current().params.get("a3"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest4")), Integer.parseInt(Http.Request.current().params.get("a4"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest5")), Integer.parseInt(Http.Request.current().params.get("a5"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest6")), Integer.parseInt(Http.Request.current().params.get("a6"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest7")), Integer.parseInt(Http.Request.current().params.get("a7"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest8")), Integer.parseInt(Http.Request.current().params.get("a8"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest9")), Integer.parseInt(Http.Request.current().params.get("a9"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest10")), Integer.parseInt(Http.Request.current().params.get("a10"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest11")), Integer.parseInt(Http.Request.current().params.get("a11"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest12")), Integer.parseInt(Http.Request.current().params.get("a12"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest13")), Integer.parseInt(Http.Request.current().params.get("a13"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest14")), Integer.parseInt(Http.Request.current().params.get("a14"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest15")), Integer.parseInt(Http.Request.current().params.get("a15"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest16")), Integer.parseInt(Http.Request.current().params.get("a16"))).save();
        new CubeRating(anonymousUser, company, Integer.parseInt(Http.Request.current().params.get("quest17")), Integer.parseInt(Http.Request.current().params.get("a17"))).save();

    }

}
