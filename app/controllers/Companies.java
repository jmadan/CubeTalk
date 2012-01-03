package controllers;

import models.*;
import play.Play;
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
        System.out.println(company.orgName);
        render(company);
    }

    public static void index(){
        List<IndustryType> companyCategories = IndustryType.findAll();
        List<Company> companies = Company.find("order by OrgName asc").fetch();
        List<CubeReview> cubeReviewsList = CubeReview.find("order by created_on desc").fetch();
        List<Article> topViewed = Application.getTopViewedArticles();
        
        List<IndustryType> industryTypeList = IndustryType.getTopReviewedIndustry();

        render(companyCategories, companies, topViewed, cubeReviewsList);
    }

    public static void category(String category){

        List<Company> categoryCompanies = Category.find("category like ?", category.replace('-', ' ')).fetch();
        System.out.println(categoryCompanies.size());
        render(categoryCompanies);
    }
    
}
