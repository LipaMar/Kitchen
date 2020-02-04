package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.hibernate.exception.ConstraintViolationException;


public class ProductsPanel extends JPanel{
	
	private JPanel upperPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JButton productAdditionButton;
	private JTextField newProductNameTextField;
	private KitchenModel db;
	public final int FRAME_WIDTH=600;
	public final int FRAME_HEIGHT=500;
	public ProductsPanel() {
		super();
	//	this.setTitle("Products");
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		db = new KitchenModel();
		
		upperPanel = new JPanel();
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		upperPanel.add(new JLabel("Dodaj nowy produkt"));
		newProductNameTextField = new JTextField(20);
		productAdditionButton = new JButton("Dodaj");
		productAdditionButton.addActionListener(new AddButtonListener());
		upperPanel.add(newProductNameTextField);
		upperPanel.add(productAdditionButton);
		this.setVisible(true);
		
	//	upperPanel.setPreferredSize(new Dimension(get,getPreferredSize().height/5));
		upperPanel.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT/5));
		centerPanel.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT*3/5));
		bottomPanel.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT/5));
		this.add(upperPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.add(bottomPanel,BorderLayout.SOUTH);
		
		updateProducts();
		
	}
	
	public void updateProducts() {
		centerPanel.removeAll();
		JPanel listPanel = buildProductsListPanel(db.getProducts());
		JScrollPane scroll = new JScrollPane(listPanel);
		scroll.setPreferredSize(new Dimension( (int)listPanel.getPreferredSize().getWidth()+30, (int)centerPanel.getPreferredSize().getHeight()-50));
		scroll.setBorder(BorderFactory.createTitledBorder("Produkty"));
		centerPanel.add(scroll);
		
		this.revalidate();
		this.repaint();
	}
	private JPanel buildProductsListPanel(List<Product> products)
	{
		int elementsHight = 40;
		JPanel result = new JPanel();
		for(Product p : products) {
			JPanel panel = new JPanel();
			JLabel label = new JLabel(firstLetterToUpper(p.getName()));
			JButton delButton = new JButton("Usuń");
			delButton.addActionListener(new DelButtonListener(p));
			panel.setLayout(new BorderLayout());
			panel.add(label,BorderLayout.WEST);
			panel.add(delButton,BorderLayout.EAST);
			result.add(panel);
		}
		result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));
		result.setPreferredSize(new Dimension((int)centerPanel.getPreferredSize().getWidth()/2,elementsHight*products.size()));
		return result;
	}
	private String firstLetterToUpper(String str) {

		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}

	public JTextField getNewProductNameTextField() {
		return newProductNameTextField;
	}

	private class DelButtonListener implements ActionListener{
		private Product product;
		public DelButtonListener(Product p) {
			product = p;
		}
		public void actionPerformed(ActionEvent e) {
			db.delProduct(product.getId());
			updateProducts();
		}
	}
	
	private class AddButtonListener implements ActionListener{
		public AddButtonListener() {
		}
		public void actionPerformed(ActionEvent e) {
			String str = newProductNameTextField.getText();
			if(str.equals(""))
				JOptionPane.showMessageDialog(null, "Wprowadź nazwę produktu", "Brak nazwy produktu", JOptionPane.INFORMATION_MESSAGE);
			else if(str.length()>30)
				JOptionPane.showMessageDialog(null, "Wprowadzona nazwa jest zbyt długa", "Zbyt długa nazwa produktu", JOptionPane.ERROR_MESSAGE);
			else {
				
				try {
					db.addProduct(str);
				} catch (SQLIntegrityConstraintViolationException e1) {
					
					JOptionPane.showMessageDialog(null, "Wprowadzony produkt już znajduje się w bazie!", "Błąd", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			updateProducts();
		}

	}
}
