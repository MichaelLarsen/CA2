package entities;

import javax.persistence.Entity;

/**
 *
 * @author Michael
 */
@Entity
public class Student extends RoleSchool {
    private String semester;

    public Student() {
    }

    public Student(String semester) {
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
        return "I am a student";
    }
    
}
