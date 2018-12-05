package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import online_store_group_project.Customer;
import online_store_group_project.Item;

public class GUI_Cart extends JPanel{
	private Customer user;
	
	final int PAGE_WIDTH = 1920;
	
	public GUI_Cart(GUI_skeleton driver) {
		user = (Customer) driver.activeUser;
		Cart cart = new Cart(driver);
		CoolButton checkoutButton = new CoolButton("Checkout");
		this.setSize(PAGE_WIDTH-40, 900);
		this.setBackground(new Color(40, 40, 40));
		setBorder(new EmptyBorder(20, 20, 20, 20));
		this.add(checkoutButton);
		this.add(cart);
		this.setVisible(true);
	}
	
	private class Cart extends JPanel{
		public Cart(GUI_skeleton driver) {
			this.setLayout(new GridLayout(0,1));
			
			JPanel header = new JPanel();
			header.setSize(PAGE_WIDTH, 30);
			
			CoolLabel headerLabel = new CoolLabel("Your Cart", Color.BLACK);
			header.add(headerLabel);
			
			this.add(header);
			
			ArrayList<Item> cartItems = new ArrayList<Item>();
			for(int i = 0; i < user.getCart().size(); i = i + 1) {
				cartItems.add(user.getCart().get(i));
			}
			
			for(int i = 0; i < cartItems.size(); i++) {
				GUI_ItemThumbnail itemThumb = new GUI_ItemThumbnail(driver, cartItems.get(i));
				this.add(itemThumb);
			}
		}
	}
}
