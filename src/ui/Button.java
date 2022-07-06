package ui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import event.ComponentBackgroundMouseEvent;
import gui.Assets;

public class Button extends JLabel {
  private static final long serialVersionUID = 1l;

  public Button(String label, ActionListener listener) {
	  
	  super(label, JLabel.CENTER);
	  
	  setFont(Assets.notoFont);
	  setForeground(UIConfig.getThemeColor("week-title"));
	  
	  setBorder(BorderFactory.createLineBorder(getForeground(), 1, true));
	  
	  addMouseListener(new ComponentBackgroundMouseEvent(this));
	  addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			listener.actionPerformed(null);
		}
	  });
	  
	  setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	  
  }
  
  public void paintComponent(Graphics g) {

	  g.setColor(getBackground());
	  g.fillRect(0, 0, getWidth(), getHeight());

	  super.paintComponent(g);

	 }
  
}
