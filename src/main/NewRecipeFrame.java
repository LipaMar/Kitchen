package main;

import java.util.Set;

import javax.swing.*;

import Entities.*;

public class NewRecipeFrame extends JFrame {
	public final int FRAME_WIDTH = 600;
	public final int FRAME_HEIGHT = 500;
	private String title;
	private String steps;
	private Set<Ingredient> ingredients;

	public Recipe makeNewRecipe() {
		init();
		buildGui();
		return new Recipe(title, steps, ingredients);
	}

	private void init() {
		setTitle("Nowy przepis");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void buildGui() {
		JPanel pane = new JPanel();
		pane.add(new JLabel("Tytu³"));
		JTextField titleTextField = new JTextField(30);
		JPanel stepsPane = new JPanel();
		stepsPane.setBorder(BorderFactory.createTitledBorder("Kroki"));
		JTextArea stepsTextArea = new JTextArea();
		stepsPane.add(stepsTextArea);
		pane.add(stepsPane);
		add(pane);
		
	}
}
