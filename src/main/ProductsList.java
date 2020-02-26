package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class ProductsList extends JPanel {
	private final Dimension panelSize;
	private Dimension listElementSize;
	private JScrollPane scroll;
	private JPanel list;
	private KitchenModel db = new KitchenModel();

	public ProductsList() {
		panelSize = new Dimension(450, 300);
		listElementSize = new Dimension((int)panelSize.getWidth(), 40);
		list = new JPanel();
		scroll = new JScrollPane(list);

		build();
		add(scroll);
	}

	public void update() {
		clear();
		buildListPane();
		repaint();
		revalidate();
	}

	private void clear() {
		list.removeAll();
	}

	private void build() {

		scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Produkty"),new EmptyBorder(5,10,10,0)));
		scroll.setPreferredSize(panelSize);

		buildListPane();

	}

	private void buildListPane() {
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		for (Product product : db.getProducts()) {

			list.add(new ProductPanel(product));
		}
	}

	private class ProductPanel extends JPanel {
		public ProductPanel(Product p) {
			setLayout(new BorderLayout());

			JButton button = new JButton("Usuñ");
			button.addActionListener(new DelButtonListener(p));
			add(button, BorderLayout.EAST);
			add(new JLabel(p.getName()), BorderLayout.WEST);
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
