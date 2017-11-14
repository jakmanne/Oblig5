package DAO;

import Models.ProductInstance;
import Models.UserInstance;
import org.h2.engine.User;
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

        List<ProductInstance> products;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        products = em.createQuery("SELECT p FROM ProductInstance p").getResultList();

        em.close();
        entityManagerFactory.close();
        return products;

    }

    @play.db.jpa.Transactional
    public void getActiveProducts(){

        List<ProductInstance> products;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        //products = em.createQuery("SELECT p FROM ProductInstance WHERE isactive  = 'false'").getResultList();
        //System.out.println(products.size());

        em.close();
        entityManagerFactory.close();


    }

    @play.db.jpa.Transactional
    public void createNewUser(UserInstance user){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(user);

        em.close();
        entityManagerFactory.close();


    }






}
