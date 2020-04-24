package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import Entities.*;

public class RecipesCard extends JPanel {
	private Repository db;
	public RecipesCard() {
		db = new Repository();
		JButton createRecipe = createNewRecipeButton();
		add(createRecipe);
	}
	private JButton createNewRecipeButton() {
		JButton result = new JButton("Nowy przepis");
		
		result.addActionListener(ActionListener-> {
			AddRecipeFrame frame = new AddRecipeFrame();
			frame.makeNewRecipe();
			});
		
		return result;
	}
}
