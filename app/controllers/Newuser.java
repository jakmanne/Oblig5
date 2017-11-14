package controllers;


import DAO.Database;
import Models.UserInstance;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.data.Form;

public class Newuser extends Controller {

    public static Result index() {
        // User bruker = Form.form(User.class).bindFromRequest().get();

        return ok(createuser.render("Her oppretter vi en ny bruker"));
    }

    public static Result createUser(){

        Database DAO = new Database();

        UserInstance user = Form.form(UserInstance.class).bindFromRequest().get();
        UserInstance newuser123 = new UserInstance();
        newuser123 = Form.form(UserInstance.class).bindFromRequest().get();

        DAO.createNewUser(newuser123);
        return ok(createuser.render("Bruker er opprettet"));

    }


}
