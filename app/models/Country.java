package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Country extends Model {
    public String country;
    public String description;

    public Country(String country, String description){
        this.country = country;
        this.description = description;
    }

    public String toString(){
        return country;
    }
}
