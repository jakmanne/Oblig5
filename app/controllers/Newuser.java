package controllers;


import DAO.Database;
import Models.UserInstance;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.data.Form;

public class Newuser extends Controller {

    private static UserInstance newuser123;

    public static Result index() {
        // User bruker = Form.form(User.class).bindFromRequest().get();
        return ok(createuser.render("Her oppretter vi en ny bruker"));
    }

    public static Result createUser(){

        // binding the field values to the object user
        newuser123 = Form.form(UserInstance.class).bindFromRequest().get();

        //
        if (newuser123.getUsername() == null || newuser123.getPassword() == null || newuser123.getName() == null
                || newuser123.getPhone() == null) {
            return ok(createuser.render(" "));
        }

        Database DAO = new Database();
        System.out.println("navn: " + newuser123.getName());
        System.out.println("tlf: " + newuser123.getPhone());
        System.out.println("passord: " + newuser123.getPassword());
        System.out.println("rating: " + newuser123.getRating());
        System.out.println("bid: " + newuser123.getBids());
        System.out.println("prod: " + newuser123.getProducts());
        System.out.println("username: " + newuser123.getUsername());
        boolean result = DAO.createNewUser(newuser123);

        if(result){
            session("username", newuser123.getUsername());
          return ok(index.render("Bruker er opprettet"));
        }
        return ok(createuser.render(""));

    }

}
