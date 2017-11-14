package controllers;

import DAO.Database;
import Models.ProductInstance;
import Models.UserInstance;
import play.data.Form;
import play.mvc.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


import views.html.*;


public class Application extends Controller {


    private static List<UserInstance> users;
    private static List<ProductInstance> products;

    public static  double elapsedSeconds;

    public static Result index() {

        Database DAO = new Database();
        users = DAO.getAllUsers();
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
