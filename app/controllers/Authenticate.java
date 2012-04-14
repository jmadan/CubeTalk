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


        if(validation.hasErrors()){
            params.flash(); // add http parameters to the flash scope
            validation.keep();
            System.out.println("Something Wrong!");
            index();
        }

        String encryptPassword = User.getPassword(userPassword);
        User user = User.find("byUserEmailAndPassword", userEmail, encryptPassword).first();
        if(user == null){
            params.flash();
            flash.error("Login failed. Please check your email/password combination and try again.");
            System.out.println("No User found.");
            index();
//            render("/authenticate/index.html");
        }
        else{
            session.put("userId", user.id);
            session.put("userAlias", user.userAlias);
            session.put("firstName", user.firstName);
            session.put("lastName", user.lastName);
            session.put("userEmail", user.userEmail);
            session.put("loggedIn", true);
//        System.out.println(user.userAlias);
            redirect("/mycubes");
        }

    }

    public static void signOut(){
        session.clear();
        redirect("/");
    }

    public static void index(){
        render();
    }
}
