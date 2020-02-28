package main;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "UNITS")
public class Unit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Uid")
	private Integer Id;
	@Column(name = "Name")
	private String Name;

	@OneToMany(mappedBy = "Unit", cascade = CascadeType.ALL)
	private Set<Product> Products;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Set<Product> getProducts() {
		return Products;
	}

}
