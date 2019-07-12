package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class AddButtonListener implements ActionListener{
	private KitchenModel model;
	private KitchenView view;
	public AddButtonListener(KitchenModel model,KitchenView view) {
		this.model = model;
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		String str = view.getNewProductName();
		if(str.equals(""))
			JOptionPane.showMessageDialog(null, "Wprowadź nazwę produktu", "Brak nazwy produktu", JOptionPane.INFORMATION_MESSAGE);
		else if(str.length()>30)
			JOptionPane.showMessageDialog(null, "Wprowadzona nazwa jest zbyt długa", "Zbyt długa nazwa produktu", JOptionPane.ERROR_MESSAGE);
		else
			model.addProduct(str);
		view.update(model);
	}

}
