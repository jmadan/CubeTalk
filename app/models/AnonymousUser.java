package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AnonymousUser extends Model {

    public String job_status;
    public String job_year;
    public String job_title;
    public String company_country;
    public String company_city;

    public User user;

    @OneToMany(mappedBy = "anonymousUser")
    public List<CubeReview> cubeReviews;


    public AnonymousUser(String company_city, String company_country, String job_status, String job_title, String job_year, User user) {
        this.company_city = company_city;
        this.company_country = company_country;
        this.job_status = job_status;
        this.job_title = job_title;
        this.job_year = job_year;
        this.user = user;
        this.cubeReviews = new ArrayList<CubeReview>();
    }

    public String toString(){
        return job_title+"-"+job_status+"-"+job_year;
    }

}
