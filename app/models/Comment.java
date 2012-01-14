package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Comment extends Model {

    @Lob
    public String comment;

    public Date created_on;
    public String userAlias;
    public String userEmail;
    public Boolean approved;

    @ManyToOne
    public Article article;

    public Comment(Article article, String comment, String userAlias, String userEmail, Boolean approved){
        this.article = article;
        this.comment = comment;
        this.userAlias = userAlias;
        this.userEmail = userEmail;
        this.created_on = new Date();
        this.approved = approved;
    }

}
