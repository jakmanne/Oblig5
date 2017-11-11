package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;
import Models.User;

import views.html.*;

public class Application extends Controller {

    //GET metode som lastes når localhost9000 kalles. Definert i Routes mappen.
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

     //POST metode som kalles fra viewet når jeg trykket på en knapp. Sender en redirect til viewet.
    //Return brukes når man vil sende noe til en tilhørende controller. mens redirect er for å sende til et view et annet sted. (som regel).
   public static Result addBar(){

       //Lager et bruker objekt fra requesten. DVS Når vi får inn JPA så oppretter vi en auksjon/bruker her.
       User bruker = Form.form(User.class).bindFromRequest().get();
       System.out.println(bruker.getNavn());
       return redirect(routes.Application.index());
   }



}
