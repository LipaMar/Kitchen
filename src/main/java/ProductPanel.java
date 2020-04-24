import Entities.Product;

import javax.swing.*;
import java.awt.*;

public class ProductPanel extends JPanel {

    public ProductPanel(Product p) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JTextField quantity = new JTextField(3);
        quantity.setMaximumSize(new Dimension(30, 30));
        JLabel unit = new JLabel(p.getUnit().getName(), SwingConstants.CENTER);
        JButton button = new JButton("Dodaj");
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