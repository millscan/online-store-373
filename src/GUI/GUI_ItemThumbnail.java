package GUI;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import online_store_group_project.Item;

public class GUI_ItemThumbnail extends JPanel{
	
	public static final int ITEM_THUMBNAIL_WIDTH = 150;
	public static final int ITEM_THUMBNAIL_HEIGHT = 180;
	
	private Item item;
	
	public GUI_ItemThumbnail(Item item) {
		// TODO Auto-generated constructor stub
		super();
		this.setSize(ITEM_THUMBNAIL_WIDTH, ITEM_THUMBNAIL_HEIGHT);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		this.item = item;
		
		JPanel imagePanel = new JPanel();
		imagePanel.setSize(ITEM_THUMBNAIL_WIDTH, ITEM_THUMBNAIL_WIDTH);
		ItemImage itemImage = new ItemImage("images/" + item.getName() + "/thumb.jpg");
		imagePanel.add(itemImage);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setSize(ITEM_THUMBNAIL_WIDTH, ITEM_THUMBNAIL_HEIGHT - ITEM_THUMBNAIL_WIDTH);
		
		JLabel nameLabel = new JLabel(item.getName());
		JLabel priceLabel = new JLabel("$" + item.getPrice());
		nameLabel.setSize(ITEM_THUMBNAIL_WIDTH*(2/3), ITEM_THUMBNAIL_HEIGHT - ITEM_THUMBNAIL_WIDTH);
		priceLabel.setSize(ITEM_THUMBNAIL_WIDTH*(1/3), ITEM_THUMBNAIL_HEIGHT - ITEM_THUMBNAIL_WIDTH);
		
		infoPanel.add(nameLabel);
		infoPanel.add(priceLabel);
		
		this.add(imagePanel);
		this.add(infoPanel);
		this.setVisible(true);
	}
	
	private class ItemImage extends Canvas{  
	      
	    public void paint(Graphics g) {  
	        Toolkit t=Toolkit.getDefaultToolkit();  
	        Image i=t.getImage(this.path);  
	        g.drawImage(i, 0,0,this);  
	    }  
	    
	    private String path;
	    
	    public ItemImage(String path) {
			this.path = path;
		}
	  
	} 
	
	public Item getItem() {
		return item;
	}
}