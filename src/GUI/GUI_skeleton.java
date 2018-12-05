package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import online_store_group_project.Item;
import online_store_group_project.Store;

public class GUI_skeleton extends JFrame{
	HomeButton homeButton;
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
		homeButton = new HomeButton(120, 60);
		
		cartButton = new CoolButton("Cart", 100, 60);
		CoolButton accountButton = new CoolButton("Account", 100, 60);
		
		JPanel searchBarContainer = new JPanel();
		searchBarContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		searchBarContainer.setPreferredSize(new Dimension(600, 50));
		searchBar = new JTextField("Search");
		searchBar.setPreferredSize(new Dimension(600, 30));
		
		topBar = new JPanel();
		topBar.setBorder(new EmptyBorder(5, 5, 5, 5));
		topBar.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		cartButton.addActionListener(new buttonListener());
		accountButton.addActionListener(new buttonListener());
		searchBar.addActionListener(new searchBarListener());
		
		topBar.add(homeButton);
		searchBarContainer.add(searchBar);
		topBar.add(searchBarContainer);
		topBar.add(accountButton);
		topBar.add(cartButton);
		topBar.setPreferredSize(new Dimension(1080, 70));
		
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
	
	private class HomeButton extends JPanel{
		
		private Image image;
		private int width;
		private int height;
		
		public HomeButton(int width, int height) {
			super();
			this.width = width;
			this.height = height;
			this.setPreferredSize(new Dimension(width, height));
			this.setSize(width, height);
			
			try {
				image = ImageIO.read(new File("images/amazeon.png"));
			}
			catch (IOException ex_2){
				System.out.println("Error reading default home image file.");
			}
			
			this.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					goHome();
				}
			});
		}
		
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Image scaledImage = image.getScaledInstance(this.width,this.height,Image.SCALE_SMOOTH);
	        g.drawImage(scaledImage, 0, 0, this); // see javadoc for more info on the parameters            
	    }
	}

	private class buttonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)(e.getSource());

			if(source.equals(cartButton))
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
