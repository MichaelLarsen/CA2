package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Michael, Sebastian, Emil og Andreas
 */
@Entity
@Table(name = "ASSISTENT_TEACHER")
public class AssistantTeacher extends RoleSchool {
    
    public AssistantTeacher() {
        super("AssistantTeacher");
    }

    @Override
    public String toString() {
        return "{\"roleName\":" + "\"AssistantTeacher\"}";
    }
    
}
