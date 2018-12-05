package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import online_store_group_project.Item;
import online_store_group_project.Store;

public class GUI_skeleton extends JFrame{
	CoolButton homeButton;
	CoolButton cartButton;
	JTextField searchBar;
	JPanel topBar;
	JPanel currentPage;
	public Store store;
	
	public GUI_skeleton(Store store){
		super("Amazeon");
		this.store = store;
		setSize(1080, 720);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		buildFrame();
		setVisible(true);
	}

	public void buildFrame() {
		homeButton = new CoolButton("Home");
		homeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				goHome();
			}
		});
		
		cartButton = new CoolButton("Cart");
		searchBar = new JTextField("Search");
		topBar = new JPanel();
		
		homeButton.addActionListener(new buttonListener());
		cartButton.addActionListener(new buttonListener());
		searchBar.addActionListener(new searchBarListener());
		
		topBar.setLayout(new BorderLayout());
		topBar.add(homeButton, BorderLayout.WEST);
		topBar.add(searchBar, BorderLayout.CENTER);
		topBar.add(cartButton, BorderLayout.EAST);
		topBar.setPreferredSize(new Dimension(1080, 30));
		
		add(topBar, BorderLayout.NORTH);
		
		this.currentPage = new GUI_HomePage(this);
		add(currentPage);
	}
	
	public void goHome() {
		switchPage(new GUI_HomePage(this));
	}
	
	public void switchPage(JPanel page) {
		System.out.println("switching page");
		remove(currentPage);
		this.currentPage = page;
		add(currentPage);
		repaint();
		revalidate();
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
