package event;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovableComponentListener extends MouseAdapter{
	
	Component owner;
	
	int X, Y;
	
	public MovableComponentListener(Component component) {
		
		owner = component;
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		
		owner.setLocation(owner.getX() + (x-X), owner.getY() + (y-Y));
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		X = e.getX();
		Y = e.getY();
		
		owner.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		owner.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
	}

}
