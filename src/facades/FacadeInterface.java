/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facades;

import entities.Person;
import entities.RoleSchool;

/**
 *
 * @author Seb
 */
public interface FacadeInterface {
    public String getPersonsAsJSON();
    public String getPersonAsJSON(long id);
    public Person addPersonFromGson(String json);
    public RoleSchool addRoleFromGson(String json, long id);
    public Person delete(long id);
}
