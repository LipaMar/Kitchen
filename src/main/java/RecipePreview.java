import Entities.Ingredient;
import Entities.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RecipePreview extends JPanel {
    private String title;
    private ArrayList<String> steps;
    private Set<Ingredient> ingredients;
    private JPanel upper = new JPanel();
    private JPanel center = new JPanel();
    private JPanel bottom = new JPanel();

    public RecipePreview() {
        steps = new ArrayList<String>();
        ingredients = new HashSet<Ingredient>();
        setLayout(new BorderLayout());


        upper.add(new JLabel(title));

        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        update();
        add(upper, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    public void addIngredient(Product p, int q) {
        ingredients.add(new Ingredient(null, p, q));
    }

    public void addStep(String step) {
        steps.add(step);
    }

    public void update() {
        bottom.removeAll();
        for (int i = 0; i < steps.size(); i++) {
            bottom.add(new JLabel((i + 1) + ". " + steps.get(i)));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 300);
    }

}
