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

import cn.edu.sdut.softlab.model.Person;


@Named("controller")
@RequestScoped
public class PersonController {
	// Message ms = new Message();
	

	EntityManagerFactory emf = null;
	EntityManager em = null;
	
	private Person newPerson = new Person();
	

	// newPerson的getter和setter方法
	public Person getNewPerson() {
		return newPerson;
	}

	public void setNewPerson(Person newPerson) {
		this.newPerson = newPerson;
	}

	public void login() throws IllegalStateException, SecurityException, HeuristicMixedException, HeuristicRollbackException, RollbackException, SystemException {
		try {
			emf = Persistence.createEntityManagerFactory("Person");
			em = emf.createEntityManager();
			
			//断点
			System.out.println("打印输出newPerson:   " + newPerson.toString());
			System.out.println("打印输出em:  " + em.toString());//测试结果，EntityManager注入失败
			em.getTransaction().begin();//至关重要的一步：开启事务
			em.persist(newPerson);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
			System.out.println("存入成功!");
		}

	}

	/*// 插入信息
	public void insertMessage(String name, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldJPAPU");
		System.out.println("打印emf: " + emf.toString());

		Person person = new Person();
		person.setName(name);
		person.setPassword(password);

		EntityManager em = emf.createEntityManager();
		System.out.println("打印em: " + em.toString());
		em.getTransaction().begin();

		em.persist(person);

		em.getTransaction().commit();

		em.close();
		emf.close();
	}*/

}
