package api;

import java.util.HashMap;

public class Keys {

	public static boolean loaded = false;

	private static HashMap<String, String> keys = new HashMap<String, String>();

	public static boolean init() {

		String var;
		String[] varlist = new String[] { "TWILIO_SID", "TWILIO_AUTH", "MESSAGE_NUMBER", "NO_REPLY_USER",
				"NO_REPLY_PASS" };

		for (int i = 0; i < varlist.length; i++) {

			String key = varlist[i];
			var = System.getenv(key);
			
			if(var == null)
				return false; // error
			
			keys.put(key, var);

		}

		loaded = true;
		return true; // everything worked normally

	}

	public static String get(String apikey) {

		if (keys.containsKey(apikey)) {
			return keys.get(apikey);
		}

		return null;

	}

}
