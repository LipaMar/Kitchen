package main;

import java.util.*;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Entities.Ingredient;
import Entities.Recipe;

public class RecipesPanel extends JPanel {
	private Repository db = new Repository();
	public RecipesPanel() {
		JTextArea text = new JTextArea();
		List<Recipe> recipes = db.getAllRecipes();
		Set<Ingredient> list = recipes.get(0).getIngredients();
		String txt = "";
		for (Ingredient ingredient : list) {
			txt+=ingredient.getProduct().getName()+" "+ingredient.getQuantity();//+" "+ingredient.getProduct().getUnit().getName()+"\n";
		}
		text.setText(txt);
		this.add(text);
	}
}
