package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import online_store_group_project.Item;

public class GUI_skeleton extends JFrame{
	JButton homeButton;
	JButton cartButton;
	JTextField searchBar;
	JPanel topBar;
	
	GUI_skeleton(){
		super("Amazeon");
		
		setSize(1080, 720);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		buildFrame();
		setVisible(true);
	}

	public void buildFrame() {
		homeButton = new JButton("Home");
		cartButton = new JButton("Cart");
		searchBar = new JTextField("Search");
		topBar = new JPanel();
		
		homeButton.addActionListener(new buttonListener());
		cartButton.addActionListener(new buttonListener());
		searchBar.addActionListener(new searchBarListener());
		
		topBar.add(homeButton, FlowLayout.LEFT);
		topBar.add(searchBar, FlowLayout.CENTER);
		topBar.add(cartButton, FlowLayout.RIGHT);
		
		add(topBar, BorderLayout.NORTH);
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
		
		}
		
		public void handleHome(){
			//new HomePageGUI();
		}
		
		public void handleCart() {
			//new CartGUI();
		}
	}
	
	private class searchBarListener implements ActionListener{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			ArrayList<Item> itemMatches = new ArrayList<Item>();
			String query = searchBar.getText();
			//TODO: this line will use a global sore var// itemMatches = store.searchItems(query);
			
			if(itemMatches.size() == 0) {
		        JOptionPane.showMessageDialog(null, "No items matching that search.");	
			}
			else {
				//new ItemsInSearchGUI(store, itemMatches);
				//TODO: this part needs to use the Item Thumbnails.
			}
		}
	}

}
