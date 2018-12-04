package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import online_store_group_project.Item;

public class ItemGUI extends JFrame{
	JButton homeButton;
	JButton cartButton;
	JTextField searchBar;
	JPanel topBar;
	GUI_ItemThumbnail item;
	JButton addToCartButton;
	JComboBox quantityBar;
	JPanel rightBar;
	JTextArea description;
	JPanel centerPanel;
	
	public ItemGUI(GUI_ItemThumbnail item){
		super("Amazeon");
		
		this.item = item;
		
		setSize(1080, 720);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		buildFrame();
		setVisible(true);
	}

	public void buildFrame() {
		homeButton = new JButton("Home");
		cartButton = new JButton("Cart");
		addToCartButton = new JButton("Add to Cart");
		searchBar = new JTextField("Search");
		topBar = new JPanel();
		rightBar = new JPanel();
		description = new JTextArea();
		centerPanel = new JPanel();
		
		description.setEditable(false);
		description.setText(item.getItem().getDescription());
		
		ArrayList<String> options = new ArrayList<String>();
		int i;
		
		for(i = 0; i < item.getItem().getQuantity(); i = i + 1) {
			options.add(String.valueOf(i + 1));
		}
		
		String[] CBInput = new String[i];
		CBInput = options.toArray(CBInput);
		
		
		//TODO: need to test this
		quantityBar = new JComboBox(CBInput);
		
		homeButton.addActionListener(new buttonListener());
		cartButton.addActionListener(new buttonListener());
		addToCartButton.addActionListener(new buttonListener());
		searchBar.addActionListener(new searchBarListener());
		//This part may be unnecessary// quantityBar.addActionListener(new comboBoxListener());
		
		//topBar.setSize(1080, 1080);
		
		//topBar.setOpaque(true);
		//topBar.setBackground(Color.BLUE);
		topBar.add(cartButton, FlowLayout.LEFT);
		topBar.add(searchBar, FlowLayout.LEFT);
		topBar.add(homeButton, FlowLayout.LEFT);
		rightBar.add(addToCartButton);
		rightBar.add(quantityBar);
		centerPanel.add(item);
		centerPanel.add(description);
		
		add(topBar, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(rightBar, BorderLayout.EAST);
	}

	private class buttonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(homeButton))
			{
				handleHome();	
			}
			else if(source.equals(cartButton))
			{
				handleCart();
			}
			else if(source.equals(addToCartButton)) 
			{
				handleAddToCart();
			}
		
		}
		
		public void handleHome(){
			//new GUI_HomePage(store);
		}
		
		public void handleCart() {
			//new CartGUI();
		}
		
		public void handleAddToCart() {
			Item tempItem = item.getItem();
			tempItem.setQuantity(Integer.parseInt(quantityBar.getSelectedItem().toString()));
			//TODO: needs global user var// user.addToCart(tempItem)
			item.getItem().setQuantity(item.getItem().getQuantity() - Integer.parseInt(quantityBar.getSelectedItem().toString()));
		}
	}
	
	private class searchBarListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			ArrayList<Item> itemMatches = new ArrayList<Item>();
			String query = searchBar.getText();
			//TODO: this line will use a global store var// itemMatches = store.searchItems(query);
			
			if(itemMatches.size() == 0) {
		        JOptionPane.showMessageDialog(null, "No items matching that search.");	
			}
			else {
				//new ItemsInSearchGUI(store, itemMatches);
				//TODO: this part needs to use the Item Thumbnails, or perhaps the items-in-search page will do this
			}
		}
	}
	//This part may be unnecessary
	/*private class comboBoxListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}*/
}
