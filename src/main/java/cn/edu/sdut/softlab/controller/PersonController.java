package cn.edu.sdut.softlab.controller;

import java.io.Serializable;
import java.util.Iterator;

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
public class PersonController implements Serializable {

	private static final long serialVersionUID = 1L;
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

	// login方法
	public void login() throws IllegalStateException, SecurityException, HeuristicMixedException,
			HeuristicRollbackException, RollbackException, SystemException {
		try {
			emf = Persistence.createEntityManagerFactory("Person");
			em = emf.createEntityManager();
			// 断点测试
			System.out.println("打印输出newPerson:   " + newPerson.toString());
			System.out.println("打印输出em:  " + em.toString());// 测试结果，EntityManager注入失败
			em.getTransaction().begin();// 至关重要的一步：开启事务
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

	// 查询
	public void query() {

		try {
			emf = Persistence.createEntityManagerFactory("Person");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			System.out.println("查看em对象:" + em.toString());

			javax.persistence.Query query = em.createQuery("select person from Person person where person.name = :name");
			//打断点测试
			System.out.println("查看Query对象:" + query.toString());
			((javax.persistence.Query) query).setParameter("name", "gaoziqiang");
			
			//对query对象进行
			System.out.println("查看NewQuery对象:" + query.toString());
			
            query.getResultList();
           
            if(query.getResultList().size() > 0) {
            	System.out.print("成功查询到对象");
            	 Iterator it = query.getResultList().iterator();
                 
                 while(it.hasNext()) {
                 	Person person = (Person) it.next();
                 	System.out.println("查询结query的Name: " + person.getName());
                 }
            	//query.getResultList().size();
            }else {
            	System.out.println("该对象不存在");
            }
           /* query.getSingleResult().toString();
			//强制类型转换 Object --> Person
            Person result1 = (Person) ((javax.persistence.Query) query).getSingleResult();
			List<Person> singleResult = (List<Person>) query.getSingleResult();
			List<Person> personList = singleResult;
			if(personList.size() > 0){
				System.out.println("查看result对象:" + personList.toString());

				System.out.println("打印输出Name:  " + personList.get(0));
				
			}else {
				System.out.println("当前没有该对象");
			}*/
			/*personList.size();
			if(result1.equals(null)) {
				System.out.println("当前没有该对象");
			}else {
				System.out.println("查看result对象:" + result1.toString());

				System.out.println("打印输出Name:  " + result1.getName());
			}*/
			

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
			System.out.println("查询成功!");
		}

		// result.getCode();
	}
	
	
	
	public void query2() {

		try {
			emf = Persistence.createEntityManagerFactory("Person");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			System.out.println("查看em对象:" + em.toString());

			javax.persistence.Query query = em.createQuery("select person from Person person where person.name = :name");
			//打断点测试
			System.out.println("查看Query对象:" + query.toString());
			((javax.persistence.Query) query).setParameter("name", "xuqian");
			
			//对query对象进行
			System.out.println("查看NewQuery对象:" + query.toString());
			
            
            query.getSingleResult().toString();
			//强制类型转换 Object --> Person
            Person result1 = (Person) ((javax.persistence.Query) query).getSingleResult();
			
			System.out.println("查询结果的姓名: " + result1.getName());
			

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("没有发现该对象!!!");
			//e.printStackTrace();
		} finally {
			em.close();
			emf.close();
			System.out.println("查询成功!");
		}

		// result.getCode();
	}
}
