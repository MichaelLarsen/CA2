package facades;

import com.google.gson.Gson;
import entities.Person;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Michael
 */
public class Facade {

    private final Gson gson = new Gson();
    private static Facade instance = new Facade();
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("KA_InheritancePU");

    private Facade() {
    }
    /*
     Pass in true to create a new instance. Usefull for testing.
     */

    public static Facade getFacade(boolean reset) {
        if (true) {
            instance = new Facade();
        }
        return instance;
    }

    public Person addPerson(String json) {
        EntityManager em = emf.createEntityManager();
        Person person = gson.fromJson(json, Person.class);

        em.getTransaction().begin();
        try {
            em.persist(person);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return person;
    }

    public String getPerson(Long id) {
        EntityManager em = emf.createEntityManager();
        Person person = null;
        try {
            person = em.getReference(Person.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return gson.toJson(person);
    }

    public String getPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Person.getPersonAll");
            Collection<Person> personList = query.getResultList();
            String personListJson = gson.toJson(personList);
            return personListJson;
        } finally {
            em.close();
        }
    }

    public int dropTables() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createNamedQuery("Person.dropAll");
            int rowsAffected = query.executeUpdate();
            em.getTransaction().commit();
            return rowsAffected;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return 0;
    }
}
