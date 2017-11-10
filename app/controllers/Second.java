package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Second extends Controller {

    public static Result Index(String name) {
        return ok(second.render("Your new application is ready.") + name);
    }


}
