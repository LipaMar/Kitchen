package Entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RECIPES")
public class Recipe implements Serializable {
	private static final long serialVersionUID = -1732292348825442609L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Rid")
	private Integer id;

	@Column(name = "Title")
	private String title;

	@Column(name = "Steps")
	private String steps;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private Set<Ingredient> ingredients;

	public Recipe() {
		id = null;
		steps = null;
	}

	public Recipe(String title, String steps, Set<Ingredient> ingredients) {
		this.title = title;
		this.steps = steps;
		this.ingredients = ingredients;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
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

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
