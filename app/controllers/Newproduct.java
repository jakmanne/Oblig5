package controllers;

import DAO.Database;
import Models.ProductInstance;
import Models.UserInstance;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.createproduct;
import views.html.index;
import views.html.login;

import java.util.Calendar;
import java.util.Date;

public class Newproduct extends Controller {

    private static ProductInstance product;

    public static Result index() {
        String user = session("username");
        if(user == null) {
            return ok(login.render("You have to log in"));
        }
        return ok(createproduct.render("Her oppretter vi et nytt produkt"));
    }

    public static Result createProduct(){
        Database DAO = new Database();
        product = Form.form(ProductInstance.class).bindFromRequest().get();

        // set values
        product.setIsactive(true);
        product.setIspurchased(false);

        // find user and set user
        UserInstance user = DAO.findUser(session("username"));
        product.setSeller(user);

        // set timers
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, Integer.parseInt(product.getTime()));
        Date newDate = now.getTime();
        Date databaseDate = new Date();
        product.setDatabaseTimestamp(databaseDate);
        product.setTimestamp(newDate);

        DAO.createNewProduct(product);
        return ok(index.render("Produkt er opprettet"));
    }
}
