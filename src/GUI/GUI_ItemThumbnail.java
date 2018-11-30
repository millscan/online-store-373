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
	
	public GUI_ItemThumbnail(Item item) {
		// TODO Auto-generated constructor stub
		super();
		this.setSize(150, 180);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel imagePanel = new JPanel();
		imagePanel.setSize(150, 150);
		ItemImage itemImage = new ItemImage("images/" + item.getName() + "/thumb.jpg");
		imagePanel.add(itemImage);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setSize(150, 30);
		
		JLabel nameLabel = new JLabel(item.getName());
		JLabel priceLabel = new JLabel("$" + item.getPrice());
		nameLabel.setSize(100, 30);
		priceLabel.setSize(50, 30);
		
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
}