package notif;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import event.MovableComponentListener;
import gui.Assets;
import main.App;
import ui.Button;
import ui.UIConfig;

public class MailLoginPane extends JDialog {
	private static final long serialVersionUID = 1l;

	public MailLoginPane() {

		setSize(400, 250);
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

		JLabel title = new JLabel("Login", JLabel.CENTER);
		title.setFont(Assets.oswaldFont_Underlined);
		title.setBounds(0, 10, W, 30);
		title.setForeground(UIConfig.getThemeColor("week-title"));
		panel.add(title);

		// address

		JLabel mailLabel = new JLabel("Mail:", JLabel.LEFT);
		mailLabel.setFont(Assets.notoFont_Task);
		mailLabel.setBounds(x, 40, w, 20);
		mailLabel.setForeground(UIConfig.getThemeColor("week-title"));
		panel.add(mailLabel);

		JTextField mail = new JTextField();
		mail.setBorder(null);
		mail.setBounds(x, 60, w, 20);
		mail.setFont(Assets.notoFont);
		mail.setForeground(UIConfig.getThemeColor("week-title"));
		mail.setBackground(panel.getBackground());
		panel.add(mail);

		JSeparator separator = new JSeparator();
		separator.setBounds(x, 80, w, 2);
		separator.setForeground(UIConfig.getThemeColor("week-title"));
		separator.setBackground(UIConfig.getThemeColor("week-title"));
		panel.add(separator);

		// password

		JLabel passLabel = new JLabel("Password:", JLabel.LEFT);
		passLabel.setFont(Assets.notoFont_Task);
		passLabel.setBounds(x, 90, w, 20);
		passLabel.setForeground(UIConfig.getThemeColor("week-title"));
		panel.add(passLabel);

		JPasswordField pass = new JPasswordField();
		pass.setBorder(null);
		pass.setBounds(x, 110, w, 20);
		pass.setFont(Assets.notoFont);
		pass.setForeground(UIConfig.getThemeColor("week-title"));
		pass.setBackground(panel.getBackground());
		panel.add(pass);

		separator = new JSeparator();
		separator.setBounds(x, 130, w, 2);
		separator.setForeground(UIConfig.getThemeColor("week-title"));
		separator.setBackground(UIConfig.getThemeColor("week-title"));
		panel.add(separator);

		// submit

		ActionListener submitListener = submitListener(mail, pass, mailLabel, passLabel);
		
		Button submit = new Button("Login", submitListener);
		submit.setBounds(x, 150, w, 30);
		panel.add(submit);

		Button cancel = new Button("Cancel", submitListener);
		cancel.setBounds(x, 190, w, 30);
		panel.add(cancel);
		
		setVisible(true);

	}

	private ActionListener submitListener(JTextField mail, JPasswordField pass, JLabel mailL, JLabel passL) {

		ActionListener listener = event -> {

			if (event.getActionCommand().equals("Login")) {

				String user = mail.getText();
				String passw = String.valueOf(pass.getPassword());

				if (user == null || user.isEmpty() || !user.contains("@")) {
					mailL.setForeground(UIConfig.getThemeColor("fg-error"));
					if (!mailL.getText().endsWith("*"))
						mailL.setText(mailL.getText() + " *");
					mail.grabFocus();
				} else if (passw == null || passw.isEmpty()) {
					passL.setForeground(UIConfig.getThemeColor("fg-error"));
					if (!passL.getText().endsWith("*"))
						passL.setText(passL.getText() + " *");
					pass.grabFocus();
				} else {
					App.getConfig().setUserMail(user);
				}

			} else {
				
				setVisible(false);
				
			}

		};

		return listener;

	}

}
