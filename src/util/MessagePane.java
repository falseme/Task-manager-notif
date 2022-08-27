package util;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import event.MovableComponentListener;
import gui.Assets;
import lang.Dictionary;
import ui.Button;
import ui.UIConfig;

public class MessagePane extends JDialog {
	private static final long serialVersionUID = 1l;
	
	public MessagePane(String _title, String _desc) {
		
		setSize(400, 120);
		setLocationRelativeTo(null);
		setUndecorated(true);

		setAlwaysOnTop(true);
		
		MovableComponentListener moveListener = new MovableComponentListener(this);
		addMouseListener(moveListener);
		addMouseMotionListener(moveListener);

		int W = getWidth();
		int w = (int) (W * 0.8);
		int x = (W - w) / 2;

		JPanel panel = new JPanel(null);
		panel.setBackground(UIConfig.getThemeColor("table-bg"));
		panel.setBorder(BorderFactory.createLineBorder(UIConfig.getThemeColor("task-border"), 2, true));
		add(panel);

		// title
		
		JLabel title = new JLabel(_title, JLabel.CENTER);
		title.setFont(Assets.oswaldFont_Underlined);
		title.setBounds(0, 10, W, 30);
		title.setForeground(UIConfig.getThemeColor("week-title"));
		panel.add(title);

		// dec

		JLabel mailLabel = new JLabel(_desc, JLabel.LEFT);
		mailLabel.setFont(Assets.notoFont_Task);
		mailLabel.setBounds(x, 40, w, 20);
		mailLabel.setForeground(UIConfig.getThemeColor("week-title"));
		panel.add(mailLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(x, 61, w, 2);
		separator.setForeground(UIConfig.getThemeColor("week-title"));
		separator.setBackground(UIConfig.getThemeColor("week-title"));
		panel.add(separator);

		// submit

		ActionListener submitListener = submitListener();
		
		Button submit = new Button(Dictionary.get(Dictionary.accept), submitListener);
		submit.setBounds(x, 70, w, 30);
		panel.add(submit);
		
		setVisible(true);
		
	}
	
	private ActionListener submitListener() {

		ActionListener listener = event -> {

			setVisible(false);

		};

		return listener;

	}
	
}
