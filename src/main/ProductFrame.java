package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ProductFrame extends JFrame{
	private JPanel upperPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JButton productAdditionButton;
	private JTextField newProductNameTextField;
	//private AddButtonListener addListener;
	private DelButtonListener delListener;
	private List<Product> products;
	public ProductFrame() {
		upperPanel = new JPanel();
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		upperPanel.add(new JLabel("Dodaj nowy produkt"));
		newProductNameTextField = new JTextField(15);
		productAdditionButton = new JButton("Dodaj");
		upperPanel.add(newProductNameTextField);
		upperPanel.add(productAdditionButton);
		
		this.add(upperPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.add(bottomPanel,BorderLayout.SOUTH);
		this.setTitle("Products");
		this.setSize(700, 500);
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void updateProducts(List<Product> products) {
		centerPanel.removeAll();
		centerPanel.setLayout(new GridLayout(1,3));
		JPanel a = new JPanel();
		JPanel b = new JPanel();
		JPanel c = new JPanel();
		b.setLayout(new GridLayout(products.size(),2,0,10));
		for(Product p : products) {
			JLabel label = new JLabel(p.getName());
			JButton button = new JButton("Usuń");
			DelButtonListener listener = delListener.copy();
			listener.setProductId(p.getID());
			button.addActionListener(listener);
			b.add(label);
			b.add(button);
		}
		centerPanel.add(a);
		centerPanel.add(b);
		centerPanel.add(c);

		this.revalidate();
		this.repaint();
	}

	public JTextField getNewProductNameTextField() {
		return newProductNameTextField;
	}

	public void setDelListener(DelButtonListener listener) {
		delListener = listener;
	}
	public void setAddListener(AddButtonListener listener) {
		productAdditionButton.addActionListener(listener);
	}
}
