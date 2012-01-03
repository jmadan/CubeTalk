package controllers;

import play.classloading.enhancers.ControllersEnhancer;

public class Home extends Application {

    @ControllersEnhancer.ByPass
    public static void index(){
        Application.index();
    }

    @ControllersEnhancer.ByPass
    public static void about(){
        Application.about();
    }

    @ControllersEnhancer.ByPass
    public static void feedback(){
        Application.feedback();
    }

}
