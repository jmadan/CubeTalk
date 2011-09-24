package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class CompanyAddress extends Model {

    @Lob
    public String address;

    public String country;
    public String address_type;
    public Date created_on;

    @ManyToOne
    public Company company;

    public CompanyAddress(String address, String country, String address_type, Company company){
        this.address = address;
        this.country = country;
        this.address_type = address_type;
        this.created_on = new Date();
        this.company = company;
    }
}
