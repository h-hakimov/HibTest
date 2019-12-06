package entity;


import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 *
 * @author DON
 */
@Entity
@Table(name = "DEPT")
public class HibDEPT implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "DEPTNO")
    private Long id;
    
    @Column(name= "DNAME")
    private String name;
    
    @Column(name = "LOC")
    private String loc;
    
    @OneToMany(mappedBy="dept", fetch = FetchType.LAZY)
    private List<HibEMP> emps;
    
    public HibDEPT(){}
    
    public HibDEPT(long id, String name, String location){
        this.id = id;
        this.name = name;
        loc = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HibDEPT)) {
            return false;
        }
        HibDEPT other = (HibDEPT) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getId() + ": " + getName() + ": " + getLoc();
    }
    
}
