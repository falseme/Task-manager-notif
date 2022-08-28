package notif;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import main.App;

public class WspNotification implements Runnable {

	private String msg;
	private String phoneNumber = null;
	
	public WspNotification(String msg) {
		
		this.msg = msg;
		
		phoneNumber = "whatsapp:"+App.getConfig().getUserwspnumber();
		
	}
	
	public void send() {
		
		if(phoneNumber == null || phoneNumber.isEmpty())
			return;
		
		Thread thr = new Thread(this);
		thr.start();
		
	}
	
	@Override
	public void run() {
		
		Twilio.init(api.Keys.keys.get("TWILIO_ACCOUNT_SID"), api.Keys.keys.get("TWILIO_AUTH_TOKEN"));
		
		Message message = Message.creator(new PhoneNumber(phoneNumber),
				new PhoneNumber(api.Keys.keys.get("MESSAGE_NUMBER")), msg).create();
		
		System.out.println(message.getStatus());
		
	}

}
