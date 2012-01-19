package models;

import play.db.jpa.Blob;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Company extends Model {

    public String orgName;
    public String website;
    public Boolean approved = false;
    public Date created_on;
    public Date updated_on;
    public Blob logo;

    @Lob
    public String overview;

    @ManyToOne
    public IndustryType industry_type;

    @OneToMany(mappedBy="company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<CompanyAddress> companyAddresses;

    @OneToMany(mappedBy="company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<CubeReview> companyReviews;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    public List<CubeRating> companyRatings;


    public Company (String orgName, String website, String overview, IndustryType industry_type, Boolean approved, Blob logo) {
        this.orgName = orgName;
        this.website = website;
        this.overview = overview;
        this.created_on = new Date();
        this.industry_type = industry_type;
        this.approved = approved;
        this.companyAddresses = new ArrayList<CompanyAddress>();
        this.companyReviews = new ArrayList<CubeReview>();
        this.companyRatings = new ArrayList<CubeRating>();
        this.logo = logo;
    }

    public List<CubeRating> getCompanyRatings() {

        return companyRatings;
    }

    public List<CubeReview> getCompanyReviews(Long id) {
        return companyReviews = CubeReview.find("byCompany_Id", id).fetch();
    }


    public static List<Company> findTopRated() {
        List<Company> companies = Company.findAll();
        List<Company> sortedCompanies = new ArrayList<Company>();
        Map ratingMap = new HashMap();
        int rating_sum = 0;
        for(Company company : companies){
            for(int i=0; i<company.companyRatings.size(); i++)
                rating_sum += company.companyRatings.get(i).rating.intValue();
            ratingMap.put(company.orgName, rating_sum);
        }
        Set set = MapUtil.sortByValue(ratingMap);
        sortedCompanies = MapUtil.toList(set);

//        SortedMap sortedData = new TreeMap(new MapValueSort.ValueComparer(ratingMap));
//        sortedData.putAll(ratingMap);
//        sortedCompanies = (List<Company>) MapValueSort.toList(sortedData);
        return sortedCompanies;
    }

    public static Company getHomeCompany() {
        Company company = homePageCompany();
        if(company != null){
            while (company.companyReviews.size() == 0)
            {
                company = homePageCompany();
            }
        }

        return company;
    }

    public static Company homePageCompany() {
        return Company.find("order by RAND()").first();
//        return Company.find("orgName", "Thoughtworks").first();
    }

    public static String getRatingGraph(List<CubeRating> ratings){
        return CubeRating.getGraphData(ratings);
    }

    public static List<Company> latestReviewed(){
        return Company.find("id = cubereview.company_id order by cubereview.created_on desc").fetch();
    }

    public String toString(){
        return orgName;
    }

    public static List<Company> getTopReviewed(List<Company> companies) {
        List<Company> sortedCompanies = new ArrayList<Company>();
        Map reviewMap = new HashMap();
        for(Company company:companies){
//            System.out.println(company.orgName + "--"+ company.companyReviews.size());
            if(company.companyReviews.size()>0){
                reviewMap.put(company.orgName, company.companyReviews.size());
            }
        }

        Set set = MapUtil.sortByValue(reviewMap);
        sortedCompanies = MapUtil.toList(set);
//        MapValueSort.printMap(reviewMap);
//        SortedMap sortedData = new TreeMap(new MapValueSort.ValueComparer(reviewMap));
//        sortedData.putAll(reviewMap);
//        MapValueSort.printMap(sortedData);
//        sortedCompanies = (List<Company>) MapValueSort.toList(sortedData);
        return sortedCompanies;
    }
}