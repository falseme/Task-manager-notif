package lang;

import java.util.HashMap;

/**
 * Dictionaty
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
  lang.get(englishLang).put(title, "Tasks Manager");
  lang.get(englishLang).put(languaje, "Languaje");
  lang.get(englishLang).put(spanish, "Spanish");
  lang.get(englishLang).put(english, "English");

 }

 public static String get(int word) {

  return lang.get(selectedLang).get(word);

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
