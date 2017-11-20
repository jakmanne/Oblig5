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
import java.util.Random;

import static play.mvc.Controller.session;

public class Database {


    /**
        Get all the user from the database
     */
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

    /**
     *
     * @return all product from the database
     */
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

        temp = ( List<ProductInstance>) em.createQuery("SELECT p FROM ProductInstance p WHERE p.isactive like :active ")
                .setParameter("active", true)
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
        if(logininstence.getUsername().equals("")){
            return false;
        }
        UserInstance user = em.find(UserInstance.class, logininstence.getUsername());

        if (user != null && user.getPassword().equals(logininstence.getPassword())){
            System.out.println("Brukeren er funnet i systemet, + brukernavn og passord stemmer! ");
            userExcists = true;
        }
        else {
            System.out.println("Vi fant ikke brukeren i systemet");
        }
       /* List<UserInstance> temp = em.createQuery(
                "SELECT p FROM UserInstance p where p.Username LIKE :name and p.Password like :password ")
                .setParameter("name", logininstence.getUsername()).setParameter("password", logininstence.getPassword()).getResultList();
        // check if user exists
        if (temp.size() != 0){
            System.out.println("Vi fant brukeren i systemet med antall brurkere: " + temp.size());
            System.out.println("Brukerinfo: " + temp.get(0).toString());
            userExcists = true;
        } */

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
        UserInstance temp = em.find(UserInstance.class, username);
        em.close();
        entityManagerFactory.close();

        // check if it exists
        if (temp == null){
            System.out.println("Vi fant ikke brukeren i systemet, brukeren er ikke logget inn!!!");
        }
        return temp;
    }

    @play.db.jpa.Transactional
    public List<ProductInstance> findProducts(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<ProductInstance> temp = em.find(UserInstance.class, username).getBoughtproducts();

        // check if user exists
        if (temp.size() != 0){
            System.out.println("Vi fant brukeren i systemet med: " + temp.size() + " kjøpte produkter");
            System.out.println("Produktifo på produkt 0: " + temp.get(0).toString());
        }
        else {
            System.out.println("Vi fant ikke produktet i systemet");
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
        long generatedLong = new Random().nextLong();

        // if the generated value already exist, generate a new one
        if(em.find(ProductInstance.class, generatedLong) != null){
            generatedLong = new Random().nextLong();
        };
        product.setId(generatedLong);
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
