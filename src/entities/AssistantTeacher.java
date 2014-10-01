package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "ASSISTENT_TEACHER")
public class AssistantTeacher extends RoleSchool {

    @Override
    public String toString() {
        return "I am an assistant teacher";
    }
    
}
