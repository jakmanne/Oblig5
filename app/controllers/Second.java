package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.data.Form;

public class Second extends Controller {

    public static Result secondIndex() {
       // User bruker = Form.form(User.class).bindFromRequest().get();
        //System.out.println(bruker.getNavn());

        return ok(second.render("test"));
    }


}
