import Entities.Product;
import Entities.Unit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductsCard extends JPanel {

    private JPanel upperPanel;
    private ProductsListPanel centerPanel;
    private JTextField newProductNameTextField;
    private JComboBox<Object> units;
    private Repository db;

    public ProductsCard() {
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

    private class ProductsListPanel extends JPanel {
        private final Dimension panelSize;
        private Dimension listElementSize;
        private JScrollPane scroll;
        private JPanel list;
        private Repository db;

        public ProductsListPanel() {
            db = new Repository();
            panelSize = new Dimension(450, 300);
            listElementSize = new Dimension((int) panelSize.getWidth(), 40);
            list = new JPanel();
            scroll = new JScrollPane(list);

            build();
            add(scroll);
        }

        public void update() {
            list.removeAll();
            buildListPane();
        }

        private void build() {

            scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Produkty"),
                    new EmptyBorder(5, 10, 10, 0)));
            scroll.setPreferredSize(panelSize);

            buildListPane();

        }

        private void buildListPane() {
            list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
            for (Product product : db.getAllProducts()) {

                list.add(new ProductPanel(product));
            }
            repaint();
            revalidate();
        }

        private class ProductPanel extends JPanel {
            public ProductPanel(Product p) {
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

                JButton button = new JButton("Usuń");
                button.addActionListener(new DelButtonListener(p));

                add(new JLabel(p.getName()));
                add(Box.createHorizontalGlue());
                add(new JLabel(p.getUnit().getName()));
                add(Box.createHorizontalGlue());
                add(button);
            }

            @Override
            public Dimension getMaximumSize() {
                return listElementSize;
            }

            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.setSize(d.width, 40);
                return d;
            }

        }

        private class DelButtonListener implements ActionListener {
            private Product product;

            public DelButtonListener(Product p) {
                product = p;
            }

            public void actionPerformed(ActionEvent e) {
                db.delProduct(product.getId());
                update();
            }
        }
    }

}
