package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import Entities.*;

public class RecipesPanel extends JPanel {
	private Repository db = new Repository();
	public RecipesPanel() {
		JButton createRecipe = createNewRecipeButton();
		add(createRecipe);
	}
	private JButton createNewRecipeButton() {
		JButton result = new JButton("Nowy przepis");
		
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewRecipeFrame frame = new NewRecipeFrame();
				frame.makeNewRecipe();
			}
		});
		
		return result;
	}
}
