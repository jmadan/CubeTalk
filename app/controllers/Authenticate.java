package controllers;

import models.User;
import play.data.validation.Required;
import play.mvc.Controller;

public class Authenticate extends Controller {

    public static void signIn(@Required String userEmail, @Required String userPassword){
        User user = User.find("byUserEmailAndPassword", userEmail, userPassword).first();
        if(validation.hasErrors()){
            System.out.println("Something Wrong!");
            redirect("/");
        }

        session.put("userAlias", user.userAlias);
        session.put("firstName", user.firstName);
        session.put("lastName", user.lastName);
        session.put("userEmail", user.userEmail);
        session.put("loggedIn", true);
        System.out.println(user.userAlias);
        redirect("/");
//        redirect(@MyAccount.show(), session.get("user"))
    }

    public static void signOut(){
        session.clear();
        redirect("/");
    }
}
