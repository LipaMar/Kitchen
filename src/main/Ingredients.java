
package main;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Ingredients")
public class Ingredients implements Serializable{
	private static final long serialVersionUID = 4467541836851739751L;
	@Id
	private Integer RecipeID;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "")
	private Set<Product> Products = new HashSet<Product>();
	
	public Integer getRecipeID() {
		return RecipeID;
	}
	public void setRecipeID(Integer recipeID) {
		RecipeID = recipeID;
	}
	public Set<Product> getProducts() {
		return Products;
	}
	public void setProducts(Set<Product> products) {
		Products = products;
	}

}
