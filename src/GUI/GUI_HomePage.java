package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import online_store_group_project.Item;
import online_store_group_project.Store;

public class GUI_HomePage extends JPanel {
	
	final int PAGE_WIDTH = 720;
	
	public GUI_HomePage(Store store) {
		// TODO Auto-generated constructor stub		
		FeaturedItems featured = new FeaturedItems(store);
		this.setSize(PAGE_WIDTH, 1000);
		this.add(featured);
		this.setVisible(true);
	}
	
	private class FeaturedItems extends JPanel {
		
		public FeaturedItems(Store store) {
			this.setSize(PAGE_WIDTH, GUI_ItemThumbnail.ITEM_THUMBNAIL_HEIGHT*2 + 30);
			this.setBackground(Color.BLACK);
			this.setLayout(new GridLayout(0, 1));
			
			JPanel header = new JPanel();
			header.setSize(PAGE_WIDTH, 30);
			JLabel headerLabel = new JLabel("Featured");
			header.add(headerLabel);
			
			JPanel topItemsPanel = new JPanel();
			topItemsPanel.setSize(PAGE_WIDTH, GUI_ItemThumbnail.ITEM_THUMBNAIL_HEIGHT*1);
			JPanel bottomItemsPanel = new JPanel();
			bottomItemsPanel.setSize(PAGE_WIDTH, GUI_ItemThumbnail.ITEM_THUMBNAIL_HEIGHT*1);
			
			int itemsPerRow = PAGE_WIDTH / GUI_ItemThumbnail.ITEM_THUMBNAIL_WIDTH;
			int totalItems = itemsPerRow * 2;
			int numStoreItems = store.getAllItems().size();
			
			for(int i = 0; i < totalItems/2; i++) {
				int randIndex = (int)Math.round(Math.random() * (double)(numStoreItems - 1));
				Item randomItem = store.getAllItems().get(randIndex);
				GUI_ItemThumbnail itemThumb = new GUI_ItemThumbnail(randomItem);
				topItemsPanel.add(itemThumb);
			}
			for(int i = totalItems/2; i < totalItems; i++) {
				int randIndex = (int)Math.round(Math.random() * (double)(numStoreItems - 1));
				Item randomItem = store.getAllItems().get(randIndex);
				GUI_ItemThumbnail itemThumb = new GUI_ItemThumbnail(randomItem);
				bottomItemsPanel.add(itemThumb);
			}
			
			this.add(header, BorderLayout.NORTH);
			this.add(topItemsPanel);
			this.add(bottomItemsPanel);
		}
		
		
	}
}
