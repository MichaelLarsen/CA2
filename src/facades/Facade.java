package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Person;
import entities.RoleSchool;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Michael
 */
public class Facade implements FacadeInterface {

    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//    private final Gson gson = new Gson();
    private static Facade instance = new Facade();
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("KA_InheritancePU");

    private Facade() {
    }
    /*
     Pass in true to create a new instance. Usefull for testing.
     */

//    public static Facade getFacade(boolean reset) {
//        if (true) {
//            instance = new Facade();
//        }
//        return instance;
//    }
    public static Facade getFacade(boolean reset) {
        if (reset) {
            instance = new Facade();
        }
        return instance;
    }
    
    
    @Override
    public String getPersonsAsJSON() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Person.getPersonAll");
            Collection<Person> personList = query.getResultList();
            String personListJson = gson.toJson(personList);
            return personListJson;
        }
        finally {
            em.close();
        }
    }

    
    @Override
    public String getPersonAsJSON(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person addPersonFromGson(String json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoleSchool addRoleFromGson(String json, long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public Person addPerson(String json) {
        EntityManager em = emf.createEntityManager();
        Person person = gson.fromJson(json, Person.class);

        em.getTransaction().begin();
        try {
            em.persist(person);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }
        return person;
    }

    public RoleSchool addRole(String json) {
        EntityManager em = emf.createEntityManager();
        RoleSchool role = gson.fromJson(json, RoleSchool.class);

        em.getTransaction().begin();
        try {
            em.persist(role);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }
        return role;
    }

    public String getPerson(Long id) {
        EntityManager em = emf.createEntityManager();
        Person person = null;
        try {
            person = em.getReference(Person.class, id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
        }
        finally {
            em.close();
        }
    }

    public int dropTables() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query1 = em.createNamedQuery("RoleSchool.dropAll");
            Query query2 = em.createNamedQuery("Person.dropAll");
            int rowsAffected = query1.executeUpdate();
            rowsAffected += query2.executeUpdate();
            em.getTransaction().commit();
            return rowsAffected;
        }
        catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }
        return 0;
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    

}
