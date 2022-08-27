package util;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import event.MovableComponentListener;
import gui.Assets;
import lang.Dictionary;
import main.App;
import ui.Button;
import ui.UIConfig;

public class WspLoginPane extends JDialog {
	private static final long serialVersionUID = 1l;
	
	public WspLoginPane() {
		
		setSize(400, 200);
		setLocationRelativeTo(null);
		setUndecorated(true);

		setAlwaysOnTop(true);

		MovableComponentListener moveListener = new MovableComponentListener(this);
		addMouseListener(moveListener);
		addMouseMotionListener(moveListener);
		
		//UI
		
		int W = getWidth();
		int w = (int) (W * 0.8);
		int x = (W - w) / 2;
		
		JPanel panel = new JPanel(null);
		panel.setBackground(UIConfig.getThemeColor("table-bg"));
		panel.setBorder(BorderFactory.createLineBorder(UIConfig.getThemeColor("task-border"), 2, true));
		add(panel);

		JLabel title = new JLabel(Dictionary.get(Dictionary.wsp_settings_title), JLabel.CENTER);
		title.setFont(Assets.oswaldFont_Underlined);
		title.setBounds(0, 10, W, 30);
		title.setForeground(UIConfig.getThemeColor("week-title"));
		panel.add(title);

		// number

		JLabel wspLabel = new JLabel(Dictionary.get(Dictionary.wsp_settings_desc), JLabel.LEFT);
		wspLabel.setFont(Assets.notoFont_Task);
		wspLabel.setBounds(x, 40, w, 20);
		wspLabel.setForeground(UIConfig.getThemeColor("week-title"));
		panel.add(wspLabel);

		JTextField wsp = new JTextField();
		wsp.setBorder(null);
		wsp.setBounds(x, 70, w, 20);
		wsp.setFont(Assets.notoFont);
		wsp.setForeground(UIConfig.getThemeColor("week-title"));
		wsp.setBackground(panel.getBackground());
		
		if(App.getConfig().getUserwspnumber() != null && !App.getConfig().getUserwspnumber().isEmpty())
			wsp.setText(App.getConfig().getUserwspnumber());
		panel.add(wsp);

		JSeparator separator = new JSeparator();
		separator.setBounds(x, 91, w, 2);
		separator.setForeground(UIConfig.getThemeColor("week-title"));
		separator.setBackground(UIConfig.getThemeColor("week-title"));
		panel.add(separator);
		
		// submit
		
		ActionListener submitListener = submitListener(wsp, wspLabel);
		
		Button submit = new Button(Dictionary.get(Dictionary.accept), submitListener);
		submit.setBounds(x, 110, w, 30);
		panel.add(submit);

		Button cancel = new Button(Dictionary.get(Dictionary.cancel), submitListener);
		cancel.setBounds(x, 150, w, 30);
		panel.add(cancel);
		
		setVisible(true);
		
	}
	
	private ActionListener submitListener(JTextField input, JLabel label) {
		
		ActionListener listener = (event) -> {
			
			if(event.getActionCommand().equals(Dictionary.get(Dictionary.accept))) {
				
				String number = input.getText();

				if (number == null || number.isEmpty() || !number.contains("+")) {
					label.setForeground(UIConfig.getThemeColor("fg-error"));
					if (!label.getText().endsWith("*"))
						label.setText(label.getText() + " *");
					input.grabFocus();
				} else {
					App.getConfig().setUserwspnumber("whatsapp:"+number);
					setVisible(false);
					new MessagePane(Dictionary.get(Dictionary.wsp_changed_title), Dictionary.get(Dictionary.wsp_changed_desc) + number);
				}
				
			}else {
				setVisible(false);
			}
			
		};
		
		return listener;
		
	}
	
}
