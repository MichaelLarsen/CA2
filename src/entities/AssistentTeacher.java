package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "ASSISTENT_TEACHER")
public class AssistentTeacher extends RoleSchool {

    @Override
    public String toString() {
        return "entities.AssistentTeacher[ id=" + getId() + " ]";
    }
    
}
