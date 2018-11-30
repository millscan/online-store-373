package GUI;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import online_store_group_project.Item;
import online_store_group_project.Store;

public class GUI_HomePage extends JPanel {
	
	final int WIDTH = 720;
	
	public GUI_HomePage(Store store) {
		// TODO Auto-generated constructor stub		
		FeaturedItems featured = new FeaturedItems(store);
		this.setSize(WIDTH, 1000);
		this.add(featured);
		this.setVisible(true);
	}
	
	private class FeaturedItems extends JPanel {
		
		public FeaturedItems(Store store) {
			this.setSize(WIDTH, GUI_ItemThumbnail.ITEM_THUMBNAIL_HEIGHT*2 + 30);
			this.setLayout(new BorderLayout());
			
			JPanel header = new JPanel();
			header.setSize(WIDTH, 30);
			JLabel headerLabel = new JLabel("Featured");
			header.add(headerLabel);
			
			JPanel itemsPanel = new JPanel();
			itemsPanel.setSize(WIDTH, GUI_ItemThumbnail.ITEM_THUMBNAIL_HEIGHT*2);
			
			int itemsPerRow = Math.floorDiv(WIDTH, GUI_ItemThumbnail.ITEM_THUMBNAIL_WIDTH);
			int totalItems = itemsPerRow * 2;
			int numStoreItems = store.getAllItems().size();
			
			for(int i = 0; i < totalItems; i++) {
				int randIndex = (int)Math.round(Math.random() * (double)(numStoreItems - 1));
				Item randomItem = store.getAllItems().get(randIndex);
				GUI_ItemThumbnail itemThumb = new GUI_ItemThumbnail(randomItem);
				itemsPanel.add(itemThumb);
			}
			
			this.add(header, BorderLayout.NORTH);
			this.add(itemsPanel);
			this.setVisible(true);
		}
		
		
	}
}
