import javax.swing.*;

public class RecipesCard extends JPanel {
    private final Repository db;

    public RecipesCard() {
        db = new Repository();
        JButton createRecipe = createNewRecipeButton();
        add(createRecipe);
    }

    private JButton createNewRecipeButton() {
        JButton result = new JButton("Nowy przepis");

        result.addActionListener(ActionListener -> {
            AddRecipeFrame frame = new AddRecipeFrame();
            frame.makeNewRecipe();
        });

        return result;
    }
}
