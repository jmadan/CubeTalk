package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

@Entity
public class User extends Model {

    public String firstName;
    public String lastName;
    public String userAlias;
    public String userEmail;
    public String password;
    public Date registeredDate;
    public String profile;
    public boolean approved;
    public Boolean terms_condition;

    @OneToMany(mappedBy="author", fetch = FetchType.LAZY)
    public List<Article> articles;

    @OneToMany(mappedBy = "anonymousUser", fetch = FetchType.LAZY)
    public  List<CubeReview> cubeReviews;

    public User(String firstName, String lastName, String userAlias, String userEmail, String password, String profile, boolean approved, boolean terms_condition){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAlias = userAlias;
        this.userEmail = userEmail;
        this.password = password;
        this.registeredDate = new Date();
        this.approved = false;
        this.profile = profile;
        this.terms_condition = terms_condition;
    }

    public User(String userAlias, String userEmail, String password, String profile, boolean approved, boolean terms_condition){
        this.userAlias = userAlias;
        this.userEmail = userEmail;
        this.password = getPassword(password);
        this.profile = profile;
        this.approved = approved;
        this.terms_condition = terms_condition;
        this.registeredDate = new Date();
    }

    public static User connect(String userEmail, String password){
        return find("byUserEmailAndPassword", userEmail, password).first();
    }

    public static String getPassword(String data) {
        StringBuffer sb = new StringBuffer();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(data.getBytes("UTF-8"));
            byte[] digestBytes = messageDigest.digest();


            String hex = null;

            for (int i = 0; i < digestBytes.length; i++) {
                //Convert it to positive integer and then to Hex String

                hex = Integer.toHexString(0xFF & digestBytes[i]);

                //Append "0" to the String to made it exactly 128 length (SHA-512 alogithm)
                if (hex.length() < 2)
                    sb.append("0");
                sb.append(hex);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return new String(sb);
    }
    

}
