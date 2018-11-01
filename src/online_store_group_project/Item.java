package online_store_group_project;

public class Item {
	
	private Owner seller;
	private String name;
	private String description;
	private String category;
	private float price;
	private int quantity;
	
	
	
	public Item(Owner seller, String name, String description, String category, float price, int quantity) {
		this.seller = seller;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	public Owner getSeller() {
		return seller;
	}

	public void setSeller(Owner seller) {
		this.seller = seller;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
}
