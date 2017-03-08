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
	
	private String ManName;
	private String ManPassword;

	// 构造器
	public Man() {

	}
	public String getManName() {
		return ManName;
	}

	public void setManName(String manName) {
		ManName = manName;
	}

	public String getManPassword() {
		return ManPassword;
	}

	public void setManPassword(String manPassword) {
		ManPassword = manPassword;
	}
	
}
