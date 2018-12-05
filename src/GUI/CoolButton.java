package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class CoolButton extends JButton {
	public CoolButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
		this.setSize(new Dimension(50, 20));
		this.setBackground(Color.WHITE);
	}
}
