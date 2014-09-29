/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;

/**
 *
 * @author Michael
 */
public class Facade {
    private final Gson gson = new Gson();
    private static Facade instance = new Facade();
    
    private Facade() {
  }
  /*
    Pass in true to create a new instance. Usefull for testing.
  */
  public static Facade getFacade(boolean reset){
    if(true){
      instance = new Facade();
    }
    return instance;
  }
  
  public void addPerson(){
      
  }
  
  public void getPerson(){
      
  }
  
   public void getPersons(){
      
  }
}
