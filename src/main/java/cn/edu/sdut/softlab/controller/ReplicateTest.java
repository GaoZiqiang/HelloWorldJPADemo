package cn.edu.sdut.softlab.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cn.edu.sdut.softlab.model.Person;
import cn.edu.sdut.softlab.model.Man;

@Named("replicate")
@RequestScoped
public class ReplicateTest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emfA = null;
	EntityManagerFactory emfB = null;
	EntityManager emA = null;
	EntityManager emB = null;

	private Person newPerson = new Person();
	private Man newMan = new Man();

	

	// newPerson的getter和setter方法
	public Person getNewPerson() {
		return newPerson;
	}

	public void setNewPerson(Person newPerson) {
		this.newPerson = newPerson;
	}
	
	public Man getNewMan() {
		return newMan;
	}

	public void setNewMan(Man newMan) {
		this.newMan = newMan;
	}
	public void replicateTest() {
		try {
			System.out.println("开始测试!");
			//emfA = Persistence.createEntityManagerFactory("Person");
			emfB = Persistence.createEntityManagerFactory("cn.edu.sdut.softlab.model.Man");
			//问题:无法创建Man这个Entity的emf
			//打断点测试
			//System.out.println("------断点测试emfA:   "  + emfA.toString());
			System.out.println("------断点测试emfB:   "  + emfB.toString());
		} catch (Exception e) {
			System.out.println("出错了!!!");
		} finally {
			
			System.out.println("操作结束!");
		}
	}
}
