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

 public static void init() {

  lang = new HashMap<Integer, HashMap<Integer, String>>();

  lang.put(spanishLang, new HashMap<Integer, String>());
  lang.put(englishLang, new HashMap<Integer, String>());

  // SPANISH

  lang.get(spanishLang).put(day1, "Domingo");
  lang.get(spanishLang).put(day2, "Lunes");
  lang.get(spanishLang).put(day3, "Martes");
  lang.get(spanishLang).put(day4, "Miercoles");
  lang.get(spanishLang).put(day5, "Jueves");
  lang.get(spanishLang).put(day6, "Viernes");
  lang.get(spanishLang).put(day7, "Sabado");

  // English

  lang.get(englishLang).put(day1, "Sunday");
  lang.get(englishLang).put(day2, "Monday");
  lang.get(englishLang).put(day3, "Tuesday");
  lang.get(englishLang).put(day4, "Wednesday");
  lang.get(englishLang).put(day5, "Thursday");
  lang.get(englishLang).put(day6, "Friday");
  lang.get(englishLang).put(day7, "Saturday");

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

}
