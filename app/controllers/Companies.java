package controllers;

import models.*;
import net.sf.oval.ConstraintViolation;
import play.Play;
import play.data.validation.*;
import play.data.validation.Error;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.List;

public class Companies extends Controller {

    @Before
    static void addDefaults(){
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void show(String orgName){
        Company company = Company.find("byOrgName", orgName.replace('-', ' ')).first();
        render(company);
    }

    public static void index(){
        List<IndustryType> companyCategories = IndustryType.findAll();
        List<Company> companies = Company.find("order by OrgName asc").fetch();
        List<CubeReview> cubeReviewsList = CubeReview.find("order by created_on desc").fetch(5);
        List<Article> topViewed = Application.getTopViewedArticles();
        List<Company> topReviewed= Company.getTopReviewed(companies);

        render(topReviewed, companyCategories, companies, topViewed, cubeReviewsList);
    }

    public static void category(String category){
        IndustryType industryType = IndustryType.find("industry_type like ?", category.replace('-', ' ')).first();
        category = industryType.industry_type;
        List<Company> categoryCompanies = Company.find("industry_type_id", industryType.id).fetch();
        render(categoryCompanies, category);
    }
    
    public static void search(){
        
        String searchString = request.params.get("searchCompany");

        validation.required(searchString);
        if(validation.hasErrors()){
//            for(Error error : validation.errors()) {
//                System.out.println(error.message());
//            }
            render("/errors/search.html");
        }
        
        Company company = Company.find("orgName like ?","%"+searchString+"%").first();
        List<Company> similarCompanies = Company.find("orgName like ?","%"+searchString+"%").fetch();

        
        if(validation.hasErrors()){
            for(Error error : validation.errors()) {
                System.out.println(error.message());
            }
            render("/errors/search.html");
        }
        renderTemplate("Companies/show.html", company, similarCompanies);
    }
    
}
