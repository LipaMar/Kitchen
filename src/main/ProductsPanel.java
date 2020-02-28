package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ProductsPanel extends JPanel {

	private JPanel upperPanel;
	private ProductsListPanel centerPanel;
	private JButton productAdditionButton;
	private JTextField newProductNameTextField;
	private Repository db;

	public ProductsPanel() {
		db = new Repository();

		upperPanel = new JPanel();
		centerPanel = new ProductsListPanel();

		initUpperPanel();

		this.add(upperPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);

		updateProducts();

		this.setVisible(true);
	}

	private void initUpperPanel() {
		upperPanel.add(new JLabel("Dodaj nowy produkt"));
		newProductNameTextField = new JTextField(20);
		productAdditionButton = new JButton("Dodaj");
		productAdditionButton.addActionListener(new AddButtonListener());
		upperPanel.add(newProductNameTextField);
		upperPanel.add(productAdditionButton);
	}

	public void updateProducts() {
		centerPanel.update();
	}

	private class AddButtonListener implements ActionListener {
		public AddButtonListener() {
		}

		public void actionPerformed(ActionEvent e) {
			String str = firstLetterToUpper(newProductNameTextField.getText());
			if (str.equals(""))
				JOptionPane.showMessageDialog(null, "Wprowadź nazwę produktu", "Brak nazwy produktu",
						JOptionPane.INFORMATION_MESSAGE);
			else if (str.length() > 30)
				JOptionPane.showMessageDialog(null, "Wprowadzona nazwa jest zbyt długa", "Zbyt długa nazwa produktu",
						JOptionPane.ERROR_MESSAGE);
			else {

				try {
					db.addProduct(str);
				} catch (SQLIntegrityConstraintViolationException e1) {

					JOptionPane.showMessageDialog(null, "Wprowadzony produkt już znajduje się w bazie!", "Błąd",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			updateProducts();
		}

		private String firstLetterToUpper(String str) {

			return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
		}

	}
}
