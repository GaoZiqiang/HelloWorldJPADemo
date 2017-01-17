package cn.edu.sdut.softlab.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@RequestScoped
@Named("person")
@Entity
public class Person {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // auto是默认值，可不写

	private Long id;

	private String name;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	//test方法

	/*
	 * public Message(String text) { this.text = text; }
	 */

}
