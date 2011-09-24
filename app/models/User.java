package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class User extends Model {

//    @Id public int id;
    public String firstName;
    public String lastName;
    public String userAlias;
    public String userEmail;
    public String password;
    public Date registeredDate;

//    @OneToMany(mappedBy="User")
//    public List<Articles> articles;

    public User(String firstName, String lastName, String userAlias, String userEmail, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAlias = userAlias;
        this.userEmail = userEmail;
        this.password = password;
        this.registeredDate = new Date();
//        this.articles = new ArrayList<Articles>();
    }

    public User(String userAlias, String userEmail, String password){
        this.userAlias = userAlias;
        this.userEmail = userEmail;
        this.password = password;
        this.registeredDate = new Date();
    }

    public static User connect(String userEmail, String password){
        return find("byUserEmailAndPassword", userEmail, password).first();
    }



}
