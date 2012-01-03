package controllers;

import models.User;

public class Security extends Secure.Security {

    static boolean authenticate(String userEmail, String password) {
        return User.connect(userEmail, password) != null;
    }
}
