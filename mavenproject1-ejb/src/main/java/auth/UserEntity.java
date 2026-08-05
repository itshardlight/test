
package auth;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Utable")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="UName")
    private String UName;
    
    @Column(name="UPass")
    private String UPass;
    
    @Column(name="URole")
    private String URole;
    
    
    //Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getUPass() {
        return UPass;
    }

    public void setUPass(String UPass) {
        this.UPass = UPass;
    }

    public String getURole() {
        return URole;
    }

    public void setURole(String URole) {
        this.URole = URole;
    }
    
    

 
}
