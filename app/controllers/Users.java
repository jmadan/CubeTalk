package controllers;

import models.CubeMail;
import models.User;
import play.Play;
import play.data.validation.Required;
import play.mvc.Before;
import play.mvc.Controller;

public class Users extends Controller{

    @Before
    static void addDefaults(){
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void register(){
        render();
    }

    public static void registerUser(@Required String userAlias,@Required String userEmail,@Required String password) {
        if(validation.hasErrors()){
            render("Users/register.html");
        }
        User newUser = new User(userAlias, userEmail, password).save();
    }

    public static void forgot(){
        render();
    }
    
    public static void forgotPassword(@Required String userEmail){

        userEmail = request.params.get("userEmail");

        if(validation.hasErrors()){
            render("/Users/forgot.html");
        }
        
        if(!request.params.get("userEmail").isEmpty()) {
            User user = User.find("userEmail", request.params.get("userEmail")).first();
            CubeMail.forgotPassword(user);
        }
        
        redirect("/Users/forgot.html");

    }
}
