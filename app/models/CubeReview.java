package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class CubeReview extends Model{

    public String review_type;
    public String review_heading;

    @Lob
    public String company_pro;

    @Lob
    public String company_con;

    @Lob
    public String company_advice;

    public Date created_on;

    @ManyToOne
    public Company company;

    public AnonymousUser anonymousUser;

    public Boolean approved = false;

    public CubeReview(String review_type, Company company, String review_heading, String company_pro, String company_con,
                      String company_advice, AnonymousUser anonymousUser, Boolean approved) {

        this.review_type = review_type;
        this.company = company;
        this.review_heading = review_heading;
        this.company_advice = company_advice;
        this.company_pro = company_pro;
        this.company_con = company_con;
        this.anonymousUser = anonymousUser;
        this.created_on = new Date();
        this.approved = approved;
    }
}
