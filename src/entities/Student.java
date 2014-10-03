package entities;

import com.google.gson.annotations.Expose;
import javax.persistence.Entity;

/**
 *
 * @author Michael
 */
@Entity
public class Student extends RoleSchool {

    @Expose
    private String semester;

    public Student() {
    }

    public Student(String semester) {
        super("Student");
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "{\"semester\":" + "\"" + semester + "\"" + "," + "\"roleName\":" + "\"Student\"}";
    }

}
