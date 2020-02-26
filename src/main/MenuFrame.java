package main;

import java.util.LinkedList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class MenuFrame extends JFrame {
	public final int FRAME_WIDTH = 600;
	public final int FRAME_HEIGHT = 500;
	private JPanel cards;
	private KitchenModel repo;

	public MenuFrame() {
		setTitle("Kuchnia");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		repo = new KitchenModel();

		JPanel fridge = new ProductsPanel();
		JPanel menu = new JPanel();

		fridge.setPreferredSize(new Dimension(FRAME_HEIGHT, FRAME_WIDTH));

		JButton fridgeButton = new JButton("Lod�wka");
		fridgeButton.addActionListener(new MenuActionListener("lod�wka"));
		menu.add(fridgeButton);

		cards = new JPanel(new CardLayout());
		cards.add(menu, "menu");
		cards.add(fridge, "lod�wka");

		this.add(cards, BorderLayout.CENTER);
		this.setVisible(true);
	}

	private class MenuActionListener implements ActionListener {

		private String name;

		public MenuActionListener(String name) {
			this.name = name;
		}

		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout) cards.getLayout();

			cl.show(cards, name);
		}

	}
//	private class ComboBoxPanel extends JPanel implements ItemListener{
//		public void itemStateChanged(ItemEvent evt) {
//			CardLayout cl = (CardLayout)(cards.getLayout());
//			cl.show(cards, (String)evt.getItem());
//		}
//	}

}
