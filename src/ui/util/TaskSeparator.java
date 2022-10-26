package ui.util;

import java.awt.Graphics;

import javax.swing.JComponent;

import ui.UIConfig;

public class TaskSeparator extends JComponent {

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		
		g.setColor(UIConfig.getThemeColor("separator"));
		g.fillRect(0, 0, getWidth(), getHeight());
		
	}
	
}
