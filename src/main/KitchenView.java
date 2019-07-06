package main;

public class KitchenView {
	private ProductFrame productFrame;
	public KitchenView() {
		productFrame = new ProductFrame();
		
	}
	public void update(KitchenModel model) {
		productFrame.getNewProductNameTextField().setText("");
		productFrame.updateProducts(model.getProducts());
	}
	public String getNewProductName() {
		return productFrame.getNewProductNameTextField().getText();
	}
	public void setAddListener(AddButtonListener listener) {
		productFrame.setAddListener(listener);
	}
	public void setDelListener(DelButtonListener listener) {
		productFrame.setDelListener(listener);
	}
	
}
