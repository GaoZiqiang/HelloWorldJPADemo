package cn.edu.sdut.softlab.model;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Named("person")
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto是默认值，可不写
    private Long id;
    private String name;
    private String password;

    // 构造器
    public Person() {

    }

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
}