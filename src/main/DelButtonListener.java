package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DelButtonListener implements ActionListener{
	private KitchenModel model;
	private KitchenView view;
	private int productId;
	public DelButtonListener(KitchenModel model, KitchenView view) {
		this.model = model;
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		model.delProduct(productId);
		view.update(model);
	}
	public void setProductId(int id) {
		productId = id;
	}
	public DelButtonListener copy() {
		return new DelButtonListener(model,view);
	}

}
