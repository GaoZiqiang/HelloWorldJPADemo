package cn.edu.sdut.softlab.model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.*;


/**
 * The persistent class for the food_tree_table database table.
 * 
 */
@Entity
@Named("food_tree_table")
public class FoodTreeTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer nodeId;

	private String name;

	private Integer parentId;

	public FoodTreeTable() {
	}

	public Integer getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}