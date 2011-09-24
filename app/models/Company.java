package models;

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

    @Lob
    public String overview;

    @ManyToOne
    public IndustryType industry_type;

    @OneToMany(mappedBy="company", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    public List<CompanyAddress> companyAddresses;

    @OneToMany(mappedBy="company", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    public List<CubeReview> companyReviews;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    public List<CubeRating> companyRatings;


    public Company (String orgName, String website, String overview, IndustryType industry_type, Boolean approved) {
        this.orgName = orgName;
        this.website = website;
        this.overview = overview;
        this.created_on = new Date();
        this.industry_type = industry_type;
        this.approved = approved;
        this.companyAddresses = new ArrayList<CompanyAddress>();
        this.companyReviews = new ArrayList<CubeReview>();
        this.companyRatings = new ArrayList<CubeRating>();
    }

    public List<CubeRating> getCompanyRatings(Long id) {
        return companyRatings = CubeRating.find("byCompany_Id", id).fetch();
    }

    public List<CubeReview> getCompanyReviews(Long id) {
        return companyReviews = CubeReview.find("byCompany_Id", id).fetch();
    }


    public static List<Company> findTopRated() {
        List<Company> topRated = new ArrayList<Company>();
        List<Company> companies = Company.findAll();
        System.out.println(companies.size());
        Iterator i = companies.iterator();
        int rating_sum = 0;
        for(Company company : companies){

        }
        return null;
    }

    public static Company findHomeCompany() {
        return Company.find("order by RAND()").first();
    }

    public static Company findLatestReview() {
        List<Company> companies = Company.findAll();
        Collections.sort(companies, new Comparator() {
            public int compare(Object o, Object o1) {

                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        return null;
    }
}
