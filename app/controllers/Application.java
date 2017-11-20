package controllers;

import DAO.Database;
import Models.ProductInstance;
import Models.UserInstance;
import play.mvc.*;
import java.util.List;

import views.html.*;


public class Application extends Controller {

    private static List<UserInstance> users;
    private static List<ProductInstance> products;
    private static List<ProductInstance> p;

    public static  double elapsedSeconds;

    public static Result index() {


       String user = session("username");
        if(user == null) {
            return ok(login.render("You need to log in"));
        }
        Database DAO = new Database();
        products = DAO.getAllProducts();
        return ok(index.render("Place a Bid"));
    }

   public static Result addBar(){
       return redirect(routes.Application.indexWithName("test"));
   }

   public static Result indexWithName(String name){
       return ok(index.render(name));
   }


    public static double getElapsedSeconds() {
        return elapsedSeconds;
    }

    public static List<UserInstance> getUsers() {
        return users;
    }

    public static List<ProductInstance> getProducts() {
        return products;
    }



}
