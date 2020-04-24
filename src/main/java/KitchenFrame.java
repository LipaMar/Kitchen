import java.awt.CardLayout;
import javax.swing.*;

public class KitchenFrame extends JFrame {
    public final int FRAME_WIDTH = 600;
    public final int FRAME_HEIGHT = 500;
    private JPanel cards;

    public KitchenFrame() {
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
        JPanel fridgeCard = new ProductsPanel();
        JPanel recipeCard = new RecipesPanel();
        cards.add(recipeCard, "recipe");
        cards.add(fridgeCard, "fridge");

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Lod�wka", fridgeCard);
        tabs.addTab("Przepisy", recipeCard);
        this.getContentPane().add(tabs);
    }
}
