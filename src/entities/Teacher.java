package entities;

import com.google.gson.annotations.Expose;
import javax.persistence.Entity;

/**
 *
 * @author Michael
 */
@Entity
public class Teacher extends RoleSchool {
    
    @Expose
    private String degree;
    
    public Teacher() {
    }

    public Teacher(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }


    @Override
    public String toString() {
        return "I am a teacher";
    }
    
}
