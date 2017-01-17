package cn.edu.sdut.softlab.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import cn.edu.sdut.softlab.model.Person;

@RequestScoped
@Named("controller")
public class PersonController {
	// Message ms = new Message();
	@Inject
	private UserTransaction utx;
	private EntityManager em;

	private Person newPerson = new Person();

	// newPerson的getter和setter方法
	public Person getNewPerson() {
		return newPerson;
	}

	public void setNewPerson(Person newPerson) {
		this.newPerson = newPerson;
	}

	public void login() throws NotSupportedException, SystemException, IllegalStateException, SecurityException,
			HeuristicMixedException, HeuristicRollbackException, RollbackException {
		try {
			utx.begin();
			
			System.out.println(newPerson.toString());
			em.persist(newPerson);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			utx.commit();
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
