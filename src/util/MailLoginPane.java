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

public class MailLoginPane extends JDialog {
	private static final long serialVersionUID = 1l;

	public MailLoginPane() {

		setSize(400, 200);
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

		JLabel title = new JLabel(Dictionary.get(Dictionary.mail_settings_title), JLabel.CENTER);
		title.setFont(Assets.oswaldFont_Underlined);
		title.setBounds(0, 10, W, 30);
		title.setForeground(UIConfig.getThemeColor("week-title"));
		panel.add(title);

		// address

		JLabel mailLabel = new JLabel(Dictionary.get(Dictionary.mail_settings_desc), JLabel.LEFT);
		mailLabel.setFont(Assets.notoFont_Task);
		mailLabel.setBounds(x, 40, w, 20);
		mailLabel.setForeground(UIConfig.getThemeColor("week-title"));
		panel.add(mailLabel);

		JTextField mail = new JTextField();
		mail.setBorder(null);
		mail.setBounds(x, 70, w, 20);
		mail.setFont(Assets.notoFont);
		mail.setForeground(UIConfig.getThemeColor("week-title"));
		mail.setBackground(panel.getBackground());
		
		if(App.getConfig().getUserMail() != null && !App.getConfig().getUserMail().isEmpty())
			mail.setText(App.getConfig().getUserMail());
		panel.add(mail);

		JSeparator separator = new JSeparator();
		separator.setBounds(x, 91, w, 2);
		separator.setForeground(UIConfig.getThemeColor("week-title"));
		separator.setBackground(UIConfig.getThemeColor("week-title"));
		panel.add(separator);

		// submit

		ActionListener submitListener = submitListener(mail, mailLabel);
		
		Button submit = new Button(Dictionary.get(Dictionary.accept), submitListener);
		submit.setBounds(x, 110, w, 30);
		panel.add(submit);

		Button cancel = new Button(Dictionary.get(Dictionary.cancel), submitListener);
		cancel.setBounds(x, 150, w, 30);
		panel.add(cancel);
		
		setVisible(true);

	}

	private ActionListener submitListener(JTextField mail, JLabel mailL) {

		ActionListener listener = event -> {

			if (event.getActionCommand().equals(Dictionary.get(Dictionary.accept))) {

				String user = mail.getText();
				user = user.replace(" ","");

				if (user == null || user.isEmpty() || !user.contains("@")) {
					mailL.setForeground(UIConfig.getThemeColor("fg-error"));
					if (!mailL.getText().endsWith("*"))
						mailL.setText(mailL.getText() + " *");
					mail.grabFocus();
				} else {
					App.getConfig().setUserMail(user);
					setVisible(false);
					new MessagePane(Dictionary.get(Dictionary.mail_changed_title), Dictionary.get(Dictionary.mail_changed_desc), "> " + user);
				}

			} else {
				
				setVisible(false);
				
			}

		};

		return listener;

	}

}
