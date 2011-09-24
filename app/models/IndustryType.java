package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class IndustryType extends Model{

    public String industry_type;
    public String description;
    public Date created_on;

    @OneToMany(mappedBy = "industry_type")
    public List<Company> companies;

    public IndustryType(String industry_type, String description) {
        this.industry_type = industry_type;
        this.description = description;
        this.created_on = new Date();
        this.companies = new ArrayList<Company>();
    }
}
