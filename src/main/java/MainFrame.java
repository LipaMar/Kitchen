import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public final int FRAME_WIDTH = 600;
    public final int FRAME_HEIGHT = 500;
    private JPanel cards;

    public MainFrame() {
        init();
        buildGui();

    }

    private void init() {
        setTitle("Kuchnia");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void buildGui() {
        cards = new JPanel(new CardLayout());
        JPanel fridgeCard = new ProductsCard();
        JPanel recipeCard = new RecipesCard();
        cards.add(recipeCard, "recipe");
        cards.add(fridgeCard, "fridge");

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Lodówka", fridgeCard);
        tabs.addTab("Przepisy", recipeCard);
        this.getContentPane().add(tabs);
    }
}
