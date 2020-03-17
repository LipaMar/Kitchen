package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;

import org.hibernate.query.criteria.internal.path.AbstractFromImpl.JoinScope;

import Entities.*;

public class NewRecipeFrame extends JFrame {
	public final int FRAME_WIDTH = 800;
	public final int FRAME_HEIGHT = 500;
	private String title;
	private ArrayList<String> steps;
	private Set<Ingredient> ingredients;
	private JPanel stepsPane;
	private JPanel recipePreviewPane;

	public Recipe makeNewRecipe() {
		init();
		buildGui();

		return new Recipe(title, steps.toArray().toString(), ingredients);
	}

	public static void main(String[] args) {
		NewRecipeFrame frame = new NewRecipeFrame();
		frame.makeNewRecipe();

	}

	private void init() {
		steps = new ArrayList<String>();
		stepsPane = new JPanel();
		recipePreviewPane = new JPanel();
		setTitle("Nowy przepis");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationByPlatform(true);
//		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

	}

	private void buildGui() {
		JPanel pane = new JPanel();

		pane.add(new JLabel("Tytu³"));
		JTextField titleTextField = new JTextField(35);
		pane.add(titleTextField, BorderLayout.NORTH);
		buildStepsPane();
		pane.add(stepsPane, BorderLayout.CENTER);
		buildRecipePreviewPane();
		pane.add(recipePreviewPane, BorderLayout.EAST);

		add(pane);
		revalidate();
		repaint();

	}

	private void update() {

		buildRecipePreviewPane();
		revalidate();
		repaint();
	}

	private void buildStepsPane() {
		stepsPane.removeAll();
		stepsPane.setBorder(BorderFactory.createTitledBorder("Nowy krok"));
		JTextArea text = new JTextArea(5, 35);
		JScrollPane scroll = new JScrollPane(text);
		scroll.setPreferredSize(new Dimension((int)text.getPreferredSize().getWidth(),(int)text.getPreferredSize().getHeight()+5));
		// text.setOpaque(false);
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
				steps.add(text.getText());
				text.setText("");
				update();
			}
		});
		stepsPane.add(button);
	}

	private void buildRecipePreviewPane() {
		recipePreviewPane.removeAll();
		JPanel upper = new JPanel();
		JPanel center = new JPanel();
		JPanel bottom = new JPanel();

		upper.add(new JLabel(title));

		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		for (int i = 0; i < steps.size(); i++) {
			bottom.add(new JLabel((i + 1) + ". " + steps.get(i)));
		}
		recipePreviewPane.add(upper, BorderLayout.NORTH);
		recipePreviewPane.add(center, BorderLayout.CENTER);
		recipePreviewPane.add(bottom, BorderLayout.SOUTH);
	}
}
