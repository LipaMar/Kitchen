package main;
import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="PRODUCTS")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Pid")
    private Integer Id;
	@Column(name = "Name",unique=true)
	private String Name;
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="products")
	private Set<Recipe> recipes=new HashSet<>(0);
	
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
	public Set<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}
	
}
