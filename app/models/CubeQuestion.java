package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class CubeQuestion extends Model {

    public String question;
    public String description;
    public Date created_on;

    public CubeQuestion(String questions, String description){
        this.description = description;
        this.question = questions;
        this.created_on = new Date();
    }
}
