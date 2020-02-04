package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class MenuFrame extends JFrame{
	public final int FRAME_WIDTH=600;
	public final int FRAME_HEIGHT=500;
	private JPanel cards;
	public MenuFrame() {
		setTitle("Kuchnia");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JPanel fridge = new ProductsPanel();
		JPanel menu = new JPanel();
		
		fridge.setPreferredSize(new Dimension(FRAME_HEIGHT,FRAME_WIDTH));
		
		JButton fridgeButton = new JButton("Lodówka");
		fridgeButton.addActionListener(new MenuActionListener("lodówka"));
		menu.add(fridgeButton);
		
		cards = new JPanel(new CardLayout());
		cards.add(menu,"menu");
		cards.add(fridge,"lodówka");
		
		this.add(cards, BorderLayout.CENTER);
		this.setVisible(true);

//		JPanel comboBoxPane = new ComboBoxPanel(); //use FlowLayout
//		String comboBoxItems[] = { "menu", "lodówka" };
//		JComboBox cb = new JComboBox(comboBoxItems);
//		cb.setEditable(false);
//		cb.addItemListener(((ComboBoxPanel)comboBoxPane) );
//		comboBoxPane.add(cb);

//		this.add(comboBoxPane, BorderLayout.PAGE_START);
		
		//pack();
	}
	private class MenuActionListener implements ActionListener{

		private String name;
		public MenuActionListener(String name) {
			this.name = name;
		}
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout)cards.getLayout();
			
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
