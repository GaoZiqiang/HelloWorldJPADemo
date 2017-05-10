package cn.edu.sdut.softlab.model;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Named("man")
@Entity
public class Man {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // auto是默认值，可不写
	private Long id;
	private String manName;
	private String manPassword;

	// 构造器
	public Man() {

	}

	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	public String getManPassword() {
		return manPassword;
	}

	public void setManPassword(String manPassword) {
		this.manPassword = manPassword;
	}

}