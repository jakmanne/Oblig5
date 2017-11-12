package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.*;
import play.data.Form;
import play.mvc.*;
import Models.User;

import views.html.*;

public class Second extends Controller {

    public static Result secondIndex() {
        User bruker = Form.form(User.class).bindFromRequest().get();
        System.out.println(bruker.getNavn());

        return ok(second.render(bruker.getNavn()));
    }


}
