package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class CubeRating extends Model implements Serializable {

    public AnonymousUser anonymousUser;
    public Company company;
//    public CubeQuestion cubeQuestion;
    public Integer questionID;
    public Integer rating;

    public CubeRating(AnonymousUser anonymousUser, Company company, Integer questionID, Integer rating){
        this.anonymousUser = anonymousUser;
        this.company = company;
        this.questionID = questionID;
        this.rating = rating;
    }

}
