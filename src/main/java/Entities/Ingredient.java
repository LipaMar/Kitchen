
package Entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredient implements Serializable {

	private static final long serialVersionUID = 4467541836851739751L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Rid")
	private Recipe recipe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Pid")
	private Product product;

	@Column(name = "Quantity")
	private Integer Quantity;

	public Ingredient(Integer id, Recipe recipe, Product product, Integer quantity) {
		Id = id;
		this.recipe = recipe;
		this.product = product;
		Quantity = quantity;
	}

	public Integer getId() {
		return Id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

}
