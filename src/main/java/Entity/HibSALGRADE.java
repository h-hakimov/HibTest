/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SALGRADE")
public class HibSALGRADE implements Serializable{
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name = "GRADE")
    private Long id;
    
    @Column(name= "LOSAL")
    private Long losal;

    @Column(name= "HISAL")
    private Long hisal;

    public HibSALGRADE(long id, long lo, long hi){
        this.id = id;
        losal = lo;
        hisal = hi;
    }
    
    public HibSALGRADE(long lo, long hi){
        losal = lo;
        hisal = hi;
    }
    
    public HibSALGRADE(){}
    
    public Long getLosal() {
        return losal;
    }

    public void setLosal(Long losal) {
        this.losal = losal;
    }

    public Long getHisal() {
        return hisal;
    }

    public void setHisal(Long hisal) {
        this.hisal = hisal;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof HibSALGRADE)) {
            return false;
        }
        HibSALGRADE other = (HibSALGRADE) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getId() + ": " + getHisal() + ": " + getLosal();
    }
    
}
