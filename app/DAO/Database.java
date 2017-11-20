package DAO;

import Models.LoginInstance;
import Models.ProductInstance;
import Models.UserInstance;
import org.h2.engine.User;
import play.db.jpa.JPA;
import scala.Product;
import Models.ProductInstance;
import play.data.Form;
import play.mvc.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

import static play.mvc.Controller.session;

public class Database {


    @play.db.jpa.Transactional
    public List<UserInstance> getAllUsers(){

        List<UserInstance> temp;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        temp = em.createQuery("SELECT p FROM UserInstance p").getResultList();

        em.close();
        entityManagerFactory.close();
        return temp;
    }

    @play.db.jpa.Transactional
    public List<ProductInstance> getAllProducts(){

        List<ProductInstance> temp;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        temp = em.createQuery("SELECT p FROM ProductInstance p").getResultList();

        em.close();
        entityManagerFactory.close();
        return temp;

    }

    @play.db.jpa.Transactional
    public List<ProductInstance> getActiveProducts(){

        List<ProductInstance> temp;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        temp = em.createQuery("SELECT p FROM ProductInstance WHERE p.isactive like :isactive ")
                .setParameter("isactive", true)
                .getResultList();
        System.out.println(temp.size());

        em.close();
        entityManagerFactory.close();

        return temp;
    }
    @play.db.jpa.Transactional
    public boolean checkUser(LoginInstance logininstence){
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        boolean userExcists = false;
        List<UserInstance> temp = em.createQuery(
                "SELECT p FROM UserInstance p where p.Username LIKE :name and p.Password like :password ")
                .setParameter("name", logininstence.getUsername())
                .setParameter("password", logininstence.getPassword())
                .getResultList();

        // check if user exists
        if (temp.size() != 0){
            System.out.println("Vi fant brukeren i systemet med antall brurkere: " + temp.size());
            System.out.println("Brukerinfo: " + temp.get(0).toString());
            userExcists = true;
        }
        else {
            System.out.println("Vi fant ikke brukeren i systemet");
        }
        em.close();
        entityManagerFactory.close();
        return userExcists; // return if there exist an user or not
    }

    @play.db.jpa.Transactional
    public UserInstance findUser(String username){
        System.out.println("finner bruker med brukernavn: " + username);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        UserInstance temp;
       if(username == null){
           temp = em.find(UserInstance.class, "admin");
       }else{
           temp = em.find(UserInstance.class, username);
       }

        em.close();
        entityManagerFactory.close();

        // check if it exists a
        if (temp == null){
            System.out.println("Vi fant ikke brukeren i systemet, brukeren er ikke logget inn!!!");
            return null;
        }
        return temp;
    }

    @play.db.jpa.Transactional
    public List<ProductInstance> findProducts(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<ProductInstance> temp = em.createQuery(
                "SELECT p FROM ProductInstance as p, UserInstance as u WHERE u.Username LIKE :name AND p.buyer.Username LIKE u.Username")
                .setParameter("name", username)
                .getResultList();

        // check if user exists
        if (temp.size() != 0){
            System.out.println("Vi fant brukeren i systemet med antall brurkere: " + temp.size());
            System.out.println("Brukerinfo: " + temp.get(0).toString());
        }
        else {
            System.out.println("Vi fant ikke brukeren i systemet");
        }
        em.close();
        entityManagerFactory.close();
        return temp; // return if there exist an user or not
    }

    @play.db.jpa.Transactional
    public void createNewProduct(ProductInstance product){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
       // System.out.println(" Produktinfo" + product.toString() + "\n");
        em.persist(product);
        em.getTransaction().commit();
        System.out.println("\n\n It should have worked");
        em.close();
        entityManagerFactory.close();
    }

    @play.db.jpa.Transactional
    public boolean createNewUser(UserInstance user){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
            System.out.println("Bruker opprettet");
        } catch(Exception e){
            System.out.println("Some error: "+ e.getMessage());
            return false;
        }finally {
            em.close();
            entityManagerFactory.close();
        }
        return true;
    }

}
