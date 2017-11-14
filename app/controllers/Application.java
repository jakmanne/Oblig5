package controllers;

import Models.UserInstance;
import play.data.Form;
import play.mvc.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import java.util.List;


import views.html.*;


public class Application extends Controller {


    private static List<UserInstance> users;

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
        //TypedQuery<UserInstance> query2 =  em.createQuery("SELECT  *  FROM UserInstance", UserInstance.class);
        //List<UserInstance> listen = query2.getResultList();
        //System.out.println(listen.isEmpty());
        long tStart = System.currentTimeMillis();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(UserInstance.class));
        users = em.createQuery(cq).getResultList();
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;

        em.close();
        entityManagerFactory.close();
    }

    public static double getElapsedSeconds() {
        return elapsedSeconds;
    }

    public static List<UserInstance> getUsers() {
        return users;
    }



}
