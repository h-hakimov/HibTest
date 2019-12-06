package entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author DON
 */
@Entity
@Table(name="EMP")
@NamedQuery(name = "HibEMP_findEMPByDept",
            query = "SELECT c FROM HibEMP c WHERE c.dept = :custName")
public class HibEMP implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "EMPNO")
    private Long id;
    
    @Column(name= "ENAME")
    private String name;
    
    @Column(name= "JOB")
    private String job;
    
    @Column(name= "MGR")
    private Long manager;
    
    @Column(name= "HIREDATE")
    private Date date;
    
    @Column(name= "SAL")
    private Long sal;
    
    @Column(name= "COMM")
    private Long comm;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "DEPTNO")
    private HibDEPT dept;
    
    
    
    public HibEMP(){};
    
    public HibEMP(Long id, String name, String pos, Long managerid, Long sal, Long comm, HibDEPT dep){
        this.id = id;
        this.name = name;
        this.job = pos;
        this.manager = managerid;
        this.date = new Date(System.currentTimeMillis()); 
        this.sal = sal;
        this.comm = comm;
        this.dept = dep;
    }
    
    public static HibEMP create(Long id, String name, String pos, Long managerid, Long sal, Long comm, HibDEPT dep){
        HibEMP empl = new HibEMP();
        empl.setId(id);
        empl.setName(name);
        empl.setJob(pos);
        empl.setManager(managerid);
        empl.setDate(new Date(System.currentTimeMillis())); 
        empl.setSal(sal);
        empl.setComm(comm);
        empl.setDept(dep);
        return empl;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getSal() {
        return sal;
    }

    public void setSal(Long sal) {
        this.sal = sal;
    }

    public Long getComm() {
        return comm;
    }

    public void setComm(Long comm) {
        this.comm = comm;
    }

    public HibDEPT getDept() {
        return dept;
    }

    public void setDept(HibDEPT dept) {
        this.dept = dept;
    }

    
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HibEMP)) {
            return false;
        }
        HibEMP other = (HibEMP) object;
        if (hashCode() != other.hashCode()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getId() + ": " + getName() + ": " + getDept().getName() + ": " + getSal();
    }
    
}
