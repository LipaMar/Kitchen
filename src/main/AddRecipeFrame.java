package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Entities.*;

public class AddRecipeFrame extends JFrame {
	public final int FRAME_WIDTH = 800;
	public final int FRAME_HEIGHT = 500;
	private String title;
	private ArrayList<String> steps;
	private Set<Ingredient> ingredients;
	private JPanel stepsPane;
	private RecipePreview recipePreviewPane;
	private ProductsAddPane productsAddPane;
	private Repository db;
	private List<Product> notSelectedProducts;

	public Recipe makeNewRecipe() {
		init();
		buildGui();

		return new Recipe(title, steps.toArray().toString(), ingredients);
	}

	public static void main(String[] args) {
		AddRecipeFrame frame = new AddRecipeFrame();
		frame.makeNewRecipe();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void init() {
		db = new Repository();
		notSelectedProducts = db.getAllProducts();
		steps = new ArrayList<String>();
		stepsPane = new JPanel();
		recipePreviewPane = new RecipePreview();
		productsAddPane = new ProductsAddPane();
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
		JTextField titleTextField = new JTextField(35);
		buildStepsPane();
		pane.add(titleTextField, BorderLayout.NORTH);
		pane.add(recipePreviewPane, BorderLayout.EAST);
		pane.add(stepsPane, BorderLayout.WEST);
		pane.add(productsAddPane, BorderLayout.WEST);

		add(pane);
		revalidate();
		repaint();

	}

	public void update() {
		productsAddPane.updateListOfProducts();
		recipePreviewPane.update();
		revalidate();
		repaint();
	}

	private void buildStepsPane() {
		stepsPane.removeAll();
		stepsPane.setBorder(BorderFactory.createTitledBorder("Nowy krok"));
		JTextArea text = new JTextArea(5, 25);
		JScrollPane scroll = new JScrollPane(text);
		scroll.setPreferredSize(
				new Dimension((int) text.getPreferredSize().getWidth(), (int) text.getPreferredSize().getHeight() + 5));
		text.setLineWrap(true);
		text.setText("Sposób przyrz¹dzania wpisz tutaj");
		text.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
			}

			public void focusGained(FocusEvent e) {
				text.setText("");
				text.removeFocusListener(this);
			}
		});
		stepsPane.add(scroll);
		JButton button = new JButton("Dodaj");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recipePreviewPane.addStep(text.getText());
				text.setText("");
				update();
			}
		});
		stepsPane.add(button);
	}

	private class ProductsAddPane extends JPanel {
		private JPanel list = new JPanel();

		public ProductsAddPane() {
			JScrollPane scroll = new JScrollPane(list);
			scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Sk³adniki"),
					new EmptyBorder(5, 10, 10, 0)));
			scroll.setPreferredSize(new Dimension(350, 250));

			list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
			updateListOfProducts();
			add(scroll);
			repaint();
			revalidate();
		}

		public void updateListOfProducts() {
			list.removeAll();
			for (Product product : notSelectedProducts) {
				list.add(new ProductPanel(product));
			}
		}
	}

	private class ProductPanel extends JPanel {

		public ProductPanel(Product p) {
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			JTextField quantity = new JTextField(3);
			quantity.setMaximumSize(new Dimension(25, 25));
			JLabel unit = new JLabel(p.getUnit().getName(), SwingConstants.CENTER);
			JButton button = new JButton("Dodaj");
			button.addActionListener(ActionListener -> {
				recipePreviewPane.addIngredient(p, Integer.parseInt(quantity.getText()));
				notSelectedProducts.remove(p);
				AddRecipeFrame.this.update();
			});
			JLabel name = new JLabel(p.getName());

			JPanel unitPane = new JPanel();
			unitPane.setLayout(new BoxLayout(unitPane, BoxLayout.X_AXIS));
			unitPane.add(quantity);
			unitPane.add(Box.createRigidArea(new Dimension(3, 0)));
			unitPane.add(unit);
			unitPane.add(Box.createRigidArea(new Dimension(10, 0)));
			unitPane.add(button);

			add(name);
			add(Box.createHorizontalGlue());
			add(unitPane);
		}

	}
}
