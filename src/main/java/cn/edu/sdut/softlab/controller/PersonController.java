package cn.edu.sdut.softlab.controller;

import java.io.Serializable;
import java.util.Iterator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;
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
    //引入UserTransaction对象 UserTransaction是个接口
	//private UserTransaction utx;
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
			//utx.begin();
			emf = Persistence.createEntityManagerFactory("Person");
			//断点测试
			System.out.println("------输出emf: " + emf.toString());
			//em = emf.createEntityManager();
			// 断点测试
			System.out.println("打印输出newPerson:   " + newPerson.toString());
			//System.out.println("打印输出em:  " + em.toString());// 测试结果，EntityManager注入失败
			/*em.getTransaction().begin();// 至关重要的一步：开启事务
			em.persist(newPerson);
			em.getTransaction().commit();*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//em.close();
			//emf.close();
			//utx.commit();
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

			javax.persistence.Query query = em
					.createQuery("select person from Person person where person.name = :name");
			// 打断点测试
			System.out.println("查看Query对象:" + query.toString());
			((javax.persistence.Query) query).setParameter("name", "gaoziqiang");

			// 对query对象进行
			System.out.println("查看NewQuery对象:" + query.toString());

			query.getResultList();

			if (query.getResultList().size() > 0) {
				System.out.print("成功查询到对象");
				Iterator it = query.getResultList().iterator();

				while (it.hasNext()) {
					Person person = (Person) it.next();
					System.out.println("查询结query的Name: " + person.getName());
				}
				// query.getResultList().size();
			} else {
				System.out.println("该对象不存在");
			}
			/*
			 * query.getSingleResult().toString(); //强制类型转换 Object --> Person
			 * Person result1 = (Person) ((javax.persistence.Query)
			 * query).getSingleResult(); List<Person> singleResult =
			 * (List<Person>) query.getSingleResult(); List<Person> personList =
			 * singleResult; if(personList.size() > 0){
			 * System.out.println("查看result对象:" + personList.toString());
			 * 
			 * System.out.println("打印输出Name:  " + personList.get(0));
			 * 
			 * }else { System.out.println("当前没有该对象"); }
			 */
			/*
			 * personList.size(); if(result1.equals(null)) {
			 * System.out.println("当前没有该对象"); }else {
			 * System.out.println("查看result对象:" + result1.toString());
			 * 
			 * System.out.println("打印输出Name:  " + result1.getName()); }
			 */

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

			javax.persistence.Query query = em
					.createQuery("select person from Person person where person.name = :name");
			// 打断点测试
			System.out.println("查看Query对象:" + query.toString());
			((javax.persistence.Query) query).setParameter("name", "gaoziqiang111");
			// 对query对象进行
			System.out.println("查看NewQuery对象:" + query.toString());

			query.getSingleResult().toString();
			// 强制类型转换 Object --> Person
			Person result1 = (Person) ((javax.persistence.Query) query).getSingleResult();
			PersistenceUnitUtil puu = emf.getPersistenceUnitUtil();
			System.out.println("查询结果的姓名: " + result1.getName());
			System.out.println("使用PersistenceUnitUtil的getIdentifier方法检测result1的状态: " + puu.getIdentifier(result1));

			em.contains(result1);
			System.out.println("打印查看result1的我状态: " + em.contains(result1));
			//detach一下
			em.detach(result1);
			//检测方法1 contains()方法
			System.out.println("再次打印查看result1的我状态: " + em.contains(result1));

			puu.getIdentifier(result1);
			//检测方法2 getIdentifier()方法
			System.out.println("再次使用PersistenceUnitUtil的getIdentifier方法检测result1的状态: " + puu.getIdentifier(result1));
			// 进行remove操作
			// em.detach(result1);
			// em.remove(result1);
			/*
			 * result1.setName("gaoziqiang111");
			 * result1.setPassword("gaoziqiang111");
			 * System.out.println("打点测试result1对象的Name属性: " + result1.getName()
			 * ); em.persist(result1);//可以不用merger()方法 //em.persist(result1);
			 * System.out.println("persist成功!");
			 */

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("没有发现该对象!!!");
			// e.printStackTrace();
		} finally {
			em.close();
			emf.close();
			System.out.println("查询成功!");
		}

		// result.getCode();
	}

	public void remove() {

	}
	
	//检索
	
	public void simpleRetrieval() {
		try {
			emf = Persistence.createEntityManagerFactory("Person");
			//EntityManager emA = getDatabaseA().createEntityManager();
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Person personA = em.find(Person.class,29);
			//Person personB  = em.find(Person.class, 29);
			//打断点测试
			System.out.println("打印personA的情况: " + personA.toString());
			/*assertTrue(personA == personB);
			assertTrue(personA.equals(personB));
			assertTrue(personA.getName().equals(personB.getName()));*/

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("检索出错");
		} finally {
			em.close();
			emf.close();
			System.out.println("查询成功!");
		}

		// result.getCode();
	}
	
	
	public void replicateTest() {
		try {
			emf = Persistence.createEntityManagerFactory("Person");
			emf = Persistence.createEntityManagerFactory("Person");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			//中间操作
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("检索出错");
		} finally {
			em.close();
			emf.close();
			System.out.println("查询成功!");
		}
	}
}
