package controllers;

import DAO.Database;
import Models.ProductInstance;
import Models.UserInstance;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.createuser;
import views.html.myuser;
import views.html.userpage;
import java.util.List;

public class UserPage extends Controller {

    private static List<UserInstance> temp;

    public static Result viewuserpage() {

        Database DAO = new Database();
        temp = DAO.getAllUsers();
        return ok(userpage.render("Min brukerside"));
    }



    public static List<UserInstance> getTemp() {
        return temp;
    }

    public static void setTemp(List<UserInstance> temp) {
        UserPage.temp = temp;
    }


}
