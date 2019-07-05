package main;

import java.awt.BorderLayout;
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
	private JPanel bottomPanel;
	private JButton productAdditionButton;
	private List<Product> products;
	public ProductFrame() {
		upperPanel = new JPanel();
		bottomPanel = new JPanel();
		upperPanel.add(new JLabel("Dodaj nowy produkt"));
		JTextField NewProductNameTextField = new JTextField(15);
		productAdditionButton= new JButton("Dodaj");
		upperPanel.add(NewProductNameTextField);
		upperPanel.add(productAdditionButton);
		

		
		this.add(upperPanel,BorderLayout.NORTH);
		this.setTitle("Products");
		this.setSize(700, 700);
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void updateProducts(List<Product> products) {
		for(Product p : products) {
			System.out.println(p.getName());
			JLabel label = new JLabel(p.getName());
			JButton button = new JButton("Usuń");
			bottomPanel.add(label);
			bottomPanel.add(button);
		}

		this.add(bottomPanel,BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	
}
