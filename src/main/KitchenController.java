package main;

public class KitchenController {
	private KitchenModel model;
	private KitchenView view;
	public KitchenController(KitchenModel model, KitchenView view) {
		this.view = view;
		this.model = model;
		AddButtonListener addListener = new AddButtonListener(model, view);
		DelButtonListener delListener = new DelButtonListener(model, view);
		view.setAddListener(addListener);
		view.setDelListener(delListener);
		view.update(model);
	}
	
}
