package controllers;

import DAO.Database;
import Models.ProductInstance;
import Models.UserInstance;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.userpage;

import java.util.List;

public class UserPage extends Controller {

    private static UserInstance userinstance;
    private static List<ProductInstance> productinstance;

    public static Result viewuserpage() {
        String user = session("username");
        if(user == null) {
            return ok(login.render("You need to log in"));
        }
        Database DAO = new Database();
        userinstance = DAO.findUser(user);
        productinstance = DAO.findProducts(user);
        return ok(userpage.render("Min brukerside"));
    }
    public static UserInstance getUserinstance() {
        return userinstance;
    }
    public static void setUserinstance(UserInstance userinstance) {
        UserPage.userinstance = userinstance;
    }

    public static List<ProductInstance> getProductinstance() { return productinstance; }
    public static void setProductinstance(List<ProductInstance> productinstance) { UserPage.productinstance = productinstance; }
}
