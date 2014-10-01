package entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Michael
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Person.getPersonAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.dropAll", query = "DELETE FROM Person p"),
})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="P_ID")
    private Long id;
    
    @Expose
    @Column(name = "first_name", length = 20)
    private String firstName;
    
    @Expose
    @Column(name = "last_name", length = 30)
    private String lastName;
    
    @Expose
    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE)
    private Collection<RoleSchool> roles = new ArrayList();
    
    @Expose
    @Column(length = 40)
    private String email;
    
    @Expose
    @Column(length = 10)
    private String phone;
    
    public Person() {
    }

    public Person(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    
    public void addRole(RoleSchool role) {
        this.roles.add(role); // slet this
        if (role.getPerson() != this) {
            role.setPerson(this);
        }
    }
    
    public Collection<RoleSchool> getRoles() {
        return roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLasttName(String lasttName) {
        this.lastName = lasttName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"firstName\":" + "\"" + firstName + "\"" + ",\"lastName\":" + "\"" + lastName + "\"" + ",\"email\":" + "\"" + email + "\"" + ",\"phone\":" + "\"" + phone + "\"" +"}";
    }
}
