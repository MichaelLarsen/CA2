package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "ROLE_SCHOOL")
@NamedQueries({
    @NamedQuery(name = "RoleSchool.dropAll", query = "DELETE FROM RoleSchool r"),
})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RoleSchool implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ROLE_ID")
    private Long id;
    
    private String roleName;
    
//    @JoinColumn(name = "FK_", referencedColumnName = "P_ID")
    @ManyToOne (cascade=CascadeType.ALL)
    private Person person;
    
    public RoleSchool() {
    }

    public RoleSchool(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setPerson(Person person) {
        this.person = person;
        if (!person.getRoles().contains(this)) {
            person.getRoles().add(this);
        }
    }

    public Person getPerson() {
        return person;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleSchool)) {
            return false;
        }
        RoleSchool other = (RoleSchool) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RoleSchool[ id=" + id + " ]";
    }
    
}
