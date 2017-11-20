package controllers;

import DAO.Database;
import Models.LoginInstance;
import Models.ProductInstance;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.createproduct;
import views.html.createuser;
import views.html.index;
import views.html.login;

public class Login extends Controller {

    private static LoginInstance logininstance;

    public static Result index() {
        return ok(login.render("You need to login use the webpage.."));
    }

    public static Result login() {
        session().clear();
        Database DAO = new Database();
        logininstance = Form.form(LoginInstance.class).bindFromRequest().get();

        System.out.println("Vi å logge inn med denne infoen: " + logininstance.getPassword() + ", " + logininstance.getUsername());
        if (logininstance.getPassword() == null || logininstance.getUsername() == null) {
            return ok(login.render("You have to fill out every field"));
        }
        // Do a check if user is valid or not
        boolean userExist = DAO.checkUser(logininstance);

        // user exists, give access to the other pages
        if (userExist) {
            session("username", logininstance.getUsername());
            return ok(index.render("User is valid, you are logged in."));

        }
        return ok(login.render("User or password is invalid"));
    }


    public static Result logout() {
        session().remove("username");
        return ok(login.render("You need to sign in"));
    }
}
