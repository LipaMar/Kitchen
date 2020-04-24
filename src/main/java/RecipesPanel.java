import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipesPanel extends JPanel {
    private Repository db;

    public RecipesPanel() {
        db = new Repository();
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
