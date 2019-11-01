package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class ProductsFrame extends JFrame{
	public final int FRAME_WIDTH=600;
	public final int FRAME_HEIGHT=500;
	private JPanel upperPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JButton productAdditionButton;
	private JTextField newProductNameTextField;
	private DelButtonListener delListener;
	public ProductsFrame() {
		upperPanel = new JPanel();
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		upperPanel.add(new JLabel("Dodaj nowy produkt"));
		newProductNameTextField = new JTextField(20);
		productAdditionButton = new JButton("Dodaj");
		upperPanel.add(newProductNameTextField);
		upperPanel.add(productAdditionButton);
		
		
		
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		upperPanel.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT/5));
		centerPanel.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT*3/5));
		bottomPanel.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT/5));
		this.add(upperPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.add(bottomPanel,BorderLayout.SOUTH);
		this.setTitle("Products");
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void updateProducts(List<Product> products) {
		centerPanel.removeAll();
		JPanel listPanel = buildProductsListPanel(products);
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
			JButton button = new JButton("Usuń");
			DelButtonListener listener = delListener.copy();
			listener.setProductId(p.getId());
			button.addActionListener(listener);
			panel.setLayout(new BorderLayout());
			panel.add(label,BorderLayout.WEST);
			panel.add(button,BorderLayout.EAST);
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

	public void setDelListener(DelButtonListener listener) {
		delListener = listener;
	}
	public void setAddListener(AddButtonListener listener) {
		productAdditionButton.addActionListener(listener);
	}
}
