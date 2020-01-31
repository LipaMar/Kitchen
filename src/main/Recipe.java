package main;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="Recipes")
public class Recipe implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "DishName")
	private String DishName;
	@Column(name = "Preparation")
	private String Preparation;
	@Column(name = "Ingredients")
	private Ingredients Ingredients;

}
