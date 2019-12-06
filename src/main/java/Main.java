import entity.HibDEPT;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entity.HibSALGRADE;
import entity.HibEMP;

public class Main
{
    private Session    session = null;

    public Session createHibernateSession()
    {
        SessionFactory   sessionFactory  = null;
        ServiceRegistry  serviceRegistry = null;
        try {
            try {
                Configuration cfg = new Configuration()
                                        .configure("hibernate.cfg.xml")
                                        .addAnnotatedClass(HibSALGRADE.class)
                                        .addAnnotatedClass(HibEMP.class)
                                        .addAnnotatedClass(HibDEPT.class);
                serviceRegistry = new StandardServiceRegistryBuilder().
                                      applySettings(cfg.getProperties()).build();
                sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            } catch (Throwable e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }
            session = sessionFactory.openSession();
            System.out.println("Session created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return session;
    }

    
    public void recordsAdd()
    {
        try {
            System.out.println("Add in DB");
            Transaction tx = session.beginTransaction();
            HibSALGRADE grade = new HibSALGRADE(10L, 10000L, 20000L);
            session.save(grade);
            tx.commit();
            System.out.println("\tAdded!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    public void printSALGRADE()
    {
        System.out.println("\nRead from DB");
        String query = "select c from HibSALGRADE c";
            
        @SuppressWarnings("unchecked")
        List<HibSALGRADE> list = (List<HibSALGRADE>)session.createQuery(query).list();
        for (int i =0; i< list.size(); i++){
            System.out.println(list.get(i));
        }
    }
    
    public void printEMP()
    {
        System.out.println("\nRead from DB");
        String query = "select c from HibEMP c";
            
        @SuppressWarnings("unchecked")
        List<HibEMP> list = (List<HibEMP>)session.createQuery(query).list();
        for (int i =0; i< list.size(); i++){
            System.out.println(list.get(i));
        }
    }
    
    public void printDEPT()
    {
        System.out.println("\nRead from DB");
        String query = "select c from HibDEPT c";
            
        @SuppressWarnings("unchecked")
        List<HibDEPT> list = (List<HibDEPT>)session.createQuery(query).list();
        for (int i =0; i< list.size(); i++){
            System.out.println(list.get(i));
        }
    }
    
    
    public HibEMP findEMPById(long id){
        System.out.println("\nFind by ID");
        HibEMP empl = (HibEMP) session.load(HibEMP.class, id);
        return empl;
    }
    
    public HibDEPT findDEPTById(long id){
        System.out.println("\nFind by ID");
        HibDEPT dept = (HibDEPT) session.load(HibDEPT.class, id);
        return dept;
    }
    
    public HibSALGRADE findSALGRADEById(long id){
        System.out.println("\nFind by ID");
        HibSALGRADE sal = (HibSALGRADE) session.load(HibSALGRADE.class, id);
        return sal;
    }
    
    public List<HibEMP> findAllEMP(){
        System.out.println("\nFind by ID");
        String query = "select c from HibEMP c";
        List<HibEMP> list = (List<HibEMP>)session.createQuery(query).list();
        return list;
    }
    
    public List<HibDEPT> findAllDEPT(){
        System.out.println("\nFind by ID");
        String query = "select c from HibDEPT c";
        List<HibDEPT> list = (List<HibDEPT>)session.createQuery(query).list();
        return list;
    }
    
    public List<HibSALGRADE> findAllSALGRADE(){
        System.out.println("\nFind by ID");
        String query = "select c from HibSALGRADE c";
        List<HibSALGRADE> list = (List<HibSALGRADE>)session.createQuery(query).list();
        return list;
    }
    
    public void saveRecord(Object obj){
        try {
            System.out.println("Add in DB");
            Transaction tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
            System.out.println("\tAdded!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<HibEMP> findEMPByDept(long dept){
        String query = "SELECT c FROM HibEMP c WHERE c.dept = :custName";
        List<HibEMP> list = (List<HibEMP>)session.createQuery(query)
                            .setParameter("custName", findDEPTById(dept))
                            .list();
        return list;
    }
    
    public List<HibEMP> findEMPByNamedQ(long dept){
        List<HibEMP> list = (List<HibEMP>)session.createNamedQuery("HibEMP_findEMPByDept")
                            .setParameter("custName", findDEPTById(dept))
                            .list();
        return list;
    }
    
    public Session getSession(){
        return session;
    }
    
    public void closeSession(){
        session.close();
    }
    
    public Main(){
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args){
        Main test = new Main();
        HibDEPT dept = new HibDEPT(70L, "Test", "Town");
        HibEMP emp = new HibEMP(2345L, "Henry", "MAANGER", 4323L, 5000L, 0L, dept);
        
        test.createHibernateSession();
        //test.saveRecord(emp);
        
        List<HibEMP> list = test.findEMPByNamedQ(10L);
        for (int i =0; i< list.size(); i++){
            System.out.println(list.get(i));
        }
        
        //test.printDEPT();
        //test.printEMP();
        test.closeSession();
    }
}