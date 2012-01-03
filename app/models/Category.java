package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Category extends Model {
    public String category;
    public String description;
    public Date created_on;

    @OneToMany(mappedBy="category", cascade= CascadeType.ALL)
    public List<Article> articles;

    public Category(String category, String description){
        this.category = category;
        this.description = description;
        this.created_on = new Date();
        this.articles = new ArrayList<Article>();
    }

    public String toString(){
        return category;
    }
}
