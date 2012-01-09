package controllers;

import models.User;
import play.Play;
import play.data.validation.Required;
import play.mvc.Before;
import play.mvc.Controller;

public class Authenticate extends Controller {

    @Before
    static void addDefaults(){
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void signIn(@Required String userEmail, @Required String userPassword){
        validation.required(userEmail, "Please Enter a valid Email");
        validation.required(userPassword, "Please Enter a valid Password");
        
        String encryptPassword = User.getPassword(userPassword);

        User user = User.find("byUserEmailAndPassword", userEmail, encryptPassword).first();
        if(validation.hasErrors()){
            params.flash(); // add http parameters to the flash scope
            validation.keep();
            System.out.println("Something Wrong!");
            index();
        }

        session.put("userAlias", user.userAlias);
        session.put("firstName", user.firstName);
        session.put("lastName", user.lastName);
        session.put("userEmail", user.userEmail);
        session.put("loggedIn", true);
//        System.out.println(user.userAlias);
        redirect("/mycubes");
    }

    public static void signOut(){
        session.clear();
        redirect("/");
    }

    public static void index(){
        render();
    }
}
