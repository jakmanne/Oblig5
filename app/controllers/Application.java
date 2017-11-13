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

    //GET metode som lastes når localhost9000 kalles. Definert i Routes mappen.
    public static Result index() {

        databasetest();
        return ok(index.render("ok"));
    }

     //POST metode som kalles fra viewet når jeg trykket på en knapp. Sender en redirect til viewet.
    //Return brukes når man vil sende noe til en tilhørende controller. mens redirect er for å sende til et view et annet sted. (som regel).
   public static Result addBar(){

       //Lager et bruker objekt fra requesten. DVS Når vi får inn JPA så oppretter vi en auksjon/bruker her.
       //User bruker = Form.form(User.class).bindFromRequest().get();
       //System.out.println(bruker.getNavn());
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
        System.out.println("test");


        Query query =  em.createNativeQuery("SELECT COUNT( * ) FROM UserInstance");
        String count = query.getSingleResult().toString();
        System.out.println(count);

        //TypedQuery<UserInstance> query2 =  em.createQuery("SELECT  *  FROM UserInstance", UserInstance.class);
        //List<UserInstance> listen = query2.getResultList();
        //System.out.println(listen.isEmpty());

        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(UserInstance.class));
        List<UserInstance> test = em.createQuery(cq).getResultList();
        System.out.println(test.get(1).getUsername());

        em.close();
        entityManagerFactory.close();

    }



}
