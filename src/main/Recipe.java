package main;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "RECIPES")
public class Recipe implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Rid")
	private Integer id;
	@Column(name = "Name")
	private String name;
	@Column(name = "Steps")
	private String steps;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="RECIPES_PRODUCTS", joinColumns = {@JoinColumn(name="Rid")},inverseJoinColumns= {@JoinColumn(name="Pid")})
	private Set<Product> products = new HashSet<Product>(0);
	public Recipe() {
		id = null;
		steps = null;
	}
	public Recipe(String name) {
		this.name=name;
		steps="";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	public Integer getId() {
		return id;
	}
	
	public Set<Product> getProducts(){
		return this.products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
