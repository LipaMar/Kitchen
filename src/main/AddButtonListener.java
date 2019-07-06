package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener{
	private KitchenModel model;
	private KitchenView view;
	public AddButtonListener(KitchenModel model,KitchenView view) {
		this.model = model;
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		String str = view.getNewProductName();
		if(!str.equals(""))
			model.addProduct(str);
		view.update(model);
	}

}
