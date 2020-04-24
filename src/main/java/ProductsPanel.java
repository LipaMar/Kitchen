import Entities.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductsPanel extends JPanel {

    private JPanel upperPanel;
    private ProductsListPanel centerPanel;
    private JTextField newProductNameTextField;
    private JComboBox<Object> units;
    private Repository db;

    public ProductsPanel() {
        db = new Repository();

        upperPanel = new JPanel();
        centerPanel = new ProductsListPanel();

        initUpperPanel();

        this.add(upperPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

        updateProducts();
    }

    private void initUpperPanel() {
        upperPanel.add(new JLabel("Dodaj nowy produkt"));
        newProductNameTextField = new JTextField(20);
        units = new JComboBox<Object>(db.getAllUnits().toArray());
        JButton productAdditionButton = new JButton("Dodaj");
        productAdditionButton.addActionListener(new AddButtonListener());
        upperPanel.add(newProductNameTextField);
        upperPanel.add(units);
        upperPanel.add(productAdditionButton);
    }

    public void updateProducts() {
        centerPanel.update();
    }

    private class AddButtonListener implements ActionListener {
        public AddButtonListener() {
        }

        public void actionPerformed(ActionEvent e) {
            db.addProduct(newProductNameTextField.getText(), (Unit) units.getSelectedItem());
            updateProducts();
        }

    }
}
