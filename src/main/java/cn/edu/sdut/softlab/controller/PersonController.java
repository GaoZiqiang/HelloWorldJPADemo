package cn.edu.sdut.softlab.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.model.Person;

@Named("controller")
@RequestScoped
public class PersonController {
	//定义全局变量
    EntityManagerFactory emf = null;
    EntityManager em = null;

    //引入UserTransaction
    private UserTransaction utx;
    private Person newPerson = new Person();

    // newPerson的getter和setter方法
    public Person getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(Person newPerson) {
        this.newPerson = newPerson;
    }

    // login方法
    public void addPerson() throws IllegalStateException, SecurityException, HeuristicMixedException,
            HeuristicRollbackException, RollbackException, SystemException {
        try {
        	utx.begin();
            /*emf = Persistence.createEntityManagerFactory("Person");
            em = emf.createEntityManager();*/
            // 断点测试
            System.out.println("打印输出newPerson:   " + newPerson.toString());
            System.out.println("打印输出em:  " + em.toString());// 测试结果，EntityManager注入失败
            //em.getTransaction().begin();// 至关重要的一步：开启事务
            em.persist(newPerson);
            //em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //em.close();
        	
            //emf.close();
            utx.commit();
            System.out.println("存入成功!");
        }

    }
}