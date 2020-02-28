package main;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {
	private static final long serialVersionUID = -6425688262558835094L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Pid")
	private Integer Id;

	@Column(name = "Name", unique = true)
	private String Name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Uid")
	private Unit Unit;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<Ingredient> ingredients;

	public Product() {
		Name = null;
	}

	public Product(String name) {
		Name = name;
	}

	public Integer getId() {
		return Id;
	}

	public String getName() {
		return Name;
	}

	public void setID(Integer Id) {
		this.Id = Id;
	}

	public void setName(String name) {
		Name = name;
	}

	public Unit getUnit() {
		return Unit;
	}

	public void setUnit(Unit unit) {
		Unit = unit;
	}
}
