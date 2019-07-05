package main;

public class KitchenView {
	private ProductFrame productFrame;
	public KitchenView() {
		productFrame = new ProductFrame();
		
	}
	public void update(KitchenModel model) {
		productFrame.updateProducts(model.getProducts());
	}
	
}
