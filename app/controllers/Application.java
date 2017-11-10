package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    //For å kjøre denne i nettleseren skriv inn localhost9000/Hello/Jakob
    public static Result hello(String name) {
        return ok("Hello " + name + "!");
    }

}
