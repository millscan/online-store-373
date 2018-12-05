package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import online_store_group_project.Item;
import online_store_group_project.Store;

public class ItemsListPage extends JFrame{
	GUI_skeleton pageSkeleton;
	ArrayList<GUI_ItemThumbnail> items;
	JPanel mainPanel;
	
	public ItemsListPage(ArrayList<Item> items, Store store) {
		pageSkeleton = new GUI_skeleton(store);
		for(int i = 0; i < items.size(); i = i + 1) {
			this.items.add(new GUI_ItemThumbnail(new GUI_skeleton(store), items.get(i)));
		}
		
		buildFrame();
		setVisible(true);	
	}
	
	public void buildFrame() {
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(1040, 250));
		mainPanel.setBackground(new Color(40, 40, 40));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainPanel.setLayout(new GridLayout(0, 1));
		
		JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		for(int i = 0; i < items.size(); i = i + 1) {
			Item item = items.get(i).getItem();
			JPanel photoPanel = new JPanel();
			photoPanel.setPreferredSize(new Dimension(200, 250));
			photoPanel.setOpaque(false);
			
			JPanel titlePanel = new JPanel();
			titlePanel.setPreferredSize(new Dimension(250, 30));
			titlePanel.setOpaque(false);
			JLabel titleLabel = new JLabel(item.getName().toUpperCase());
			titleLabel.setForeground(Color.WHITE);
			titleLabel.setFont(new Font("Lucida Sans", Font.BOLD, 18));
			titlePanel.add(titleLabel);
			
			JButton viewButton = new JButton("View Item " + String.valueOf(i));
			
			photoPanel.add(titlePanel);
			photoPanel.add(new ItemImage(item, 225));
			
			viewButton.addActionListener(new itemListener());
			
			scroll.add(photoPanel);
			scroll.add(viewButton);
		}
		mainPanel.add(scroll);
		pageSkeleton.add(mainPanel);
	}
	
	private class itemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton tempItem = (JButton)e.getSource();
			String temp2 = tempItem.getText().substring(10, tempItem.getText().length());
			int i = Integer.parseInt(temp2);
			Item item = items.get(i).getItem();
			pageSkeleton.switchPage(new ItemGUI(item, pageSkeleton.activeUser));
		}
		
	}
	
}
