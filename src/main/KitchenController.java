package main;

public class KitchenController {
	private KitchenModel model;
	private KitchenView view;
	public KitchenController(KitchenModel model, KitchenView view) {
		this.view = view;
		this.model = model;
		view.update(model);
	}
	
}
