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


    public AnonymousUser(String job_status, String job_year, String job_title, String company_country, String company_city, User user) {
        this.job_status = job_status;
        this.job_title = job_title;
        this.job_year = job_year;
        this.company_country = company_country;
        this.company_city = company_city;
        this.user = user;
        this.cubeReviews = new ArrayList<CubeReview>();
    }

}
