package lang;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Dictionary
 */
public class Dictionary {

 public static HashMap<Integer, HashMap<Integer, String>> lang;

 public static final int spanishLang = 0;
 public static final int englishLang = 1;

 private static int selectedLang = 0;

 public static final int day1 = 0;
 public static final int day2 = 1;
 public static final int day3 = 2;
 public static final int day4 = 3;
 public static final int day5 = 4;
 public static final int day6 = 5;
 public static final int day7 = 6;
 public static final int options = 7;
 public static final int theme = 8;
 public static final int darkTheme = 9;
 public static final int whiteTheme = 10;
 public static final int title = 11;
 public static final int languaje = 12;
 public static final int spanish = 13;
 public static final int english = 14;
 public static final int user = 15;
 public static final int mailsettings = 16;
 public static final int wspsettings = 17;
 public static final int notiftitle = 18;

 public static final int mail_settings_title = 19;
 public static final int mail_settings_desc = 20;
 public static final int wsp_settings_title = 23;
 public static final int wsp_settings_desc = 24;
 public static final int keys_load_title= 25;
 public static final int keys_load_desc = 26;

 public static final int mail_changed_title = 27;
 public static final int mail_changed_desc = 28;
 public static final int wsp_changed_title = 29;
 public static final int wsp_changed_desc = 30;
 public static final int wsp_changed_desc_2 = 37;

 public static final int cancel = 21;
 public static final int accept = 22;
 public static final int create = 31;
 public static final int frequency = 38;
 public static final int repeatWeek = 32;
 public static final int repeatDays = 39;
 public static final int notRepeat = 40;

 public static final int creator_select = 33;
 public static final int creator_title = 34;
 public static final int creator_time = 35;
 public static final int creator_notif = 36;

 //last used num = 40

 public static void init() {

  lang = new HashMap<Integer, HashMap<Integer, String>>();

  lang.put(spanishLang, new HashMap<Integer, String>());
  lang.put(englishLang, new HashMap<Integer, String>());

  // SPANISH

  lang.get(spanishLang).put(day1, "Domingo");
  lang.get(spanishLang).put(day2, "Lunes");
  lang.get(spanishLang).put(day3, "Martes");
  lang.get(spanishLang).put(day4, "Miércoles");
  lang.get(spanishLang).put(day5, "Jueves");
  lang.get(spanishLang).put(day6, "Viernes");
  lang.get(spanishLang).put(day7, "Sábado");
  lang.get(spanishLang).put(options, "Opciones");
  lang.get(spanishLang).put(theme, "Tema");
  lang.get(spanishLang).put(whiteTheme, "Tema Claro");
  lang.get(spanishLang).put(darkTheme, "Tema Oscuro");
  lang.get(spanishLang).put(title, "Organizador de Tareas");
  lang.get(spanishLang).put(languaje, "Lenguaje");
  lang.get(spanishLang).put(spanish, "Español");
  lang.get(spanishLang).put(english, "Inglés");
  lang.get(spanishLang).put(user, "Usuario");
  lang.get(spanishLang).put(mailsettings, "Establecer dirección de correo");
  lang.get(spanishLang).put(wspsettings, "Establecer número de WhatsApp");
  lang.get(spanishLang).put(notiftitle, "Notificación Tarea Semanal: ");
  lang.get(spanishLang).put(mail_settings_title, "Registrar Correo");
  lang.get(spanishLang).put(mail_settings_desc, "Correo electrónico: (ej: nombre@ejemplo.com)");
  lang.get(spanishLang).put(wsp_settings_title, "Registrar número");
  lang.get(spanishLang).put(wsp_settings_desc, "Número de WhatsApp: (ej: +123 1234 1234567)");
  lang.get(spanishLang).put(cancel, "Cancelar");
  lang.get(spanishLang).put(accept, "Aceptar");
  lang.get(spanishLang).put(create, "Crear tarea");
  lang.get(spanishLang).put(frequency, "Frecuencia");
  lang.get(spanishLang).put(repeatWeek, "Repetir semanalmente");
  lang.get(spanishLang).put(repeatDays, "Repetir cada X días");
  lang.get(spanishLang).put(notRepeat, "No repetir");
  lang.get(spanishLang).put(keys_load_title, "VAR Error");
  lang.get(spanishLang).put(keys_load_desc, "Error al cargar variables para mails y whatsapp");
  lang.get(spanishLang).put(mail_changed_title, "Mail actualizado");
  lang.get(spanishLang).put(mail_changed_desc, "Su dirección de correo fue cambiada a:");
  lang.get(spanishLang).put(wsp_changed_title, "Whatsapp actualizado");
  lang.get(spanishLang).put(wsp_changed_desc, "Su número de whatsapp fue cambiado a:");
  lang.get(spanishLang).put(wsp_changed_desc_2, "Envía \"join nearly-buffalo\" a +1 415 523 8886");
  lang.get(spanishLang).put(creator_select, "Día seleccionado");
  lang.get(spanishLang).put(creator_title, "Título:");
  lang.get(spanishLang).put(creator_time, "Hora: (HH:MM)");
  lang.get(spanishLang).put(creator_notif, "Notificaciones");

  // English

  lang.get(englishLang).put(day1, "Sunday");
  lang.get(englishLang).put(day2, "Monday");
  lang.get(englishLang).put(day3, "Tuesday");
  lang.get(englishLang).put(day4, "Wednesday");
  lang.get(englishLang).put(day5, "Thursday");
  lang.get(englishLang).put(day6, "Friday");
  lang.get(englishLang).put(day7, "Saturday");
  lang.get(englishLang).put(options, "Options");
  lang.get(englishLang).put(theme, "Theme");
  lang.get(englishLang).put(darkTheme, "Dark Theme");
  lang.get(englishLang).put(whiteTheme, "White Theme");
  lang.get(englishLang).put(title, "Task Manager");
  lang.get(englishLang).put(languaje, "Languaje");
  lang.get(englishLang).put(spanish, "Spanish");
  lang.get(englishLang).put(english, "English");
  lang.get(englishLang).put(user, "User");
  lang.get(englishLang).put(mailsettings, "Set email address");
  lang.get(englishLang).put(wspsettings, "Set WhatsApp number");
  lang.get(englishLang).put(notiftitle, "Weekly Task Notification: ");
  lang.get(englishLang).put(mail_settings_title, "Mail Registration");
  lang.get(englishLang).put(mail_settings_desc, "Email address: (e.g., name@example.com)");
  lang.get(englishLang).put(wsp_settings_title, "Number registration");
  lang.get(englishLang).put(wsp_settings_desc, "WhatsApp number: (e.g., +123 1234 1234567)");
  lang.get(englishLang).put(cancel, "Cancel");
  lang.get(englishLang).put(accept, "Accept");
  lang.get(englishLang).put(create, "New task");
  lang.get(englishLang).put(frequency, "Frequency");
  lang.get(englishLang).put(repeatWeek, "Repeat weekly");
  lang.get(englishLang).put(repeatDays, "Repeat every X days");
  lang.get(englishLang).put(notRepeat, "Do not repeat");
  lang.get(englishLang).put(keys_load_title, "VAR Error");
  lang.get(englishLang).put(keys_load_desc, "Could not load mail & whatsapp variables");
  lang.get(englishLang).put(mail_changed_title, "Email updated");
  lang.get(englishLang).put(mail_changed_desc, "Your email was changed to:");
  lang.get(englishLang).put(wsp_changed_title, "Whatsapp updated");
  lang.get(englishLang).put(wsp_changed_desc, "Your whatsapp number was changed to:");
  lang.get(englishLang).put(wsp_changed_desc_2, "Send \"join nearly-buffalo\" to +1 415 523 8886");
  lang.get(englishLang).put(creator_select, "Day select");
  lang.get(englishLang).put(creator_title, "Title:");
  lang.get(englishLang).put(creator_time, "Time: (HH:MM)");
  lang.get(englishLang).put(creator_notif, "Notifications");

 }

 public static String get(int word) {

  return lang.get(selectedLang).get(word);

 }

 public static int getKey(String word) {

  if(lang.get(selectedLang).containsValue(word)) {
  	for(Entry<Integer, String> entry: lang.get(selectedLang).entrySet()) {

  	  if(entry.getValue() == word)
  		return entry.getKey();

  	}
  }

  return -1;

 }

 public static void changeLang(int newlang) {

  if (newlang < 0 && newlang > 1)
   return;

  System.out.println("lang changed");
  selectedLang = newlang;

 }

 public static int getLangSelected(){
  return selectedLang;
 }

}
