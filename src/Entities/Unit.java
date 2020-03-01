package Entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "UNITS")
public class Unit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Uid")
	private Integer id;
	@Column(name = "Name")
	private String name;

	@OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
	private Set<Product> Products;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return Products;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
}
