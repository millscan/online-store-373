package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import online_store_group_project.Item;
import online_store_group_project.Store;

public class GUI_HomePage extends JPanel {
	
	final int PAGE_WIDTH = 1080;
	
	public GUI_HomePage(Store store) {
		// TODO Auto-generated constructor stub		
		FeaturedItems featured = new FeaturedItems(store);
		this.setSize(PAGE_WIDTH, 690);
		this.add(featured);
		this.setVisible(true);
	}
	
	private class FeaturedItems extends JPanel {
		
		public FeaturedItems(Store store) {
			this.setSize(PAGE_WIDTH, GUI_ItemThumbnail.ITEM_THUMBNAIL_HEIGHT*2 + 30);
			this.setBackground(Color.BLACK);
			this.setLayout(new BorderLayout());
			
			JPanel header = new JPanel();
			header.setSize(PAGE_WIDTH, 30);
			header.setBackground(Color.WHITE);
			JLabel headerLabel = new JLabel("FEATURED ITEMS");
			header.add(headerLabel);
			
			JPanel topItemsPanel = new JPanel();
			topItemsPanel.setSize(PAGE_WIDTH, GUI_ItemThumbnail.ITEM_THUMBNAIL_HEIGHT);
			JPanel bottomItemsPanel = new JPanel();
			bottomItemsPanel.setSize(PAGE_WIDTH, GUI_ItemThumbnail.ITEM_THUMBNAIL_HEIGHT);
			
			int itemsPerRow = PAGE_WIDTH / GUI_ItemThumbnail.ITEM_THUMBNAIL_WIDTH;
			int totalItems = itemsPerRow * 2;
			int numStoreItems = store.getAllItems().size();
			
			ArrayList<Item> featuredItems = new ArrayList<Item>();
			while(featuredItems.size() < totalItems) {
				int randIndex = (int)Math.round(Math.random() * (double)(numStoreItems - 1));
				Item randomItem = store.getAllItems().get(randIndex);
				if(!featuredItems.contains(randomItem)) {
					featuredItems.add(randomItem);
				};
			}
			
			for(int i = 0; i < featuredItems.size()/2; i++) {
				GUI_ItemThumbnail itemThumb = new GUI_ItemThumbnail(featuredItems.get(i));
				topItemsPanel.add(itemThumb);
			}
			for(int i = featuredItems.size()/2; i < featuredItems.size(); i++) {
				GUI_ItemThumbnail itemThumb = new GUI_ItemThumbnail(featuredItems.get(i));
				bottomItemsPanel.add(itemThumb);
			}
			
			this.add(header, BorderLayout.NORTH);
			this.add(topItemsPanel);
			this.add(bottomItemsPanel, BorderLayout.SOUTH);
		}
		
		
	}
}
