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

    public static void registerUser() {
        String userAlias = request.params.get("userAlias");
        String userEmail = request.params.get("userEmail");
        String userpass = request.params.get("userPassword");
        Boolean tnc = Boolean.valueOf(request.params.get("tnc"));
        String profile = "member" ;
        if(validation.hasErrors()){
            System.out.println("Errors");
            render("Users/register.html");
        }
        User existingUser = User.find("userAlias", userAlias).first();
        if (existingUser == null){
            User newUser = new User(userAlias, userEmail, userpass, profile, false, tnc).save();
            render("/Users/registered.html");
        }
        else{
            params.flash();
            flash.error("User Alias already exists. Please choose another one.");
            render("/users/register.html");
        }
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
