package controllers;

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

        databasetest();
        return ok(index.render("Liste Over alle brukere i databasen"));
    }

   public static Result addBar(){


       return redirect(routes.Application.indexWithName("test"));
   }

   public static Result indexWithName(String name){
       return ok(index.render(name));
   }

    @play.db.jpa.Transactional
    public static void databasetest() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query =  em.createNativeQuery("SELECT COUNT( * ) FROM UserInstance");
        String count = query.getSingleResult().toString();

        long tStart = System.currentTimeMillis();

        users = em.createQuery("SELECT p FROM UserInstance p").getResultList();

        products = em.createQuery("SELECT p FROM ProductInstance p").getResultList();

        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;

        List<UserInstance> listPersons = em.createQuery("SELECT p FROM UserInstance p").getResultList();

        em.close();
        entityManagerFactory.close();
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
