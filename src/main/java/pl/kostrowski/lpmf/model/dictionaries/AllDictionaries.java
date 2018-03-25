package pl.kostrowski.lpmf.model.dictionaries;

import java.util.HashMap;
import java.util.Map;

public class AllDictionaries {

	private static Map<String, String> moviesDictionary = new HashMap<>();

	static {
		moviesDictionary.put("Afera Tomasza Crowna", "Afera Thomasa Crowna");
		moviesDictionary.put("Braveheart - waleczne serce", "Braveheart - Waleczne serce");
		moviesDictionary.put("Braveheart: Waleczne serce", "Braveheart - Waleczne serce");
		moviesDictionary.put("Casino Royal", "Casino Royale");
		moviesDictionary.put("Czas honoru - Czas honoru", "Czas honoru");
		moviesDictionary.put("Frozen", "Frozen - Kraina lodu");
		moviesDictionary.put("Frozen: Kraina lodu", "Frozen - Kraina lodu");
		moviesDictionary.put("Gwiezdne wojny - Atak Klonów", "Gwiezdne wojny - Atak Klonów");
		moviesDictionary.put("Gwiezdne wojny - Imperium kontratakuje", "Gwiezdne wojny - Imperium kontratakuje");
		moviesDictionary.put("Gwiezdne wojny. Przebudzenie mocy", "Gwiezdne wojny - Przebudzenie mocy");
		moviesDictionary.put("Gwiezdne wojny: Imperium kontratakuje", "Gwiezdne wojny - Imperium kontratakuje");
		moviesDictionary.put("Gwiezdne wojny: Ostatni Jedi", "Gwiezdne wojny - Ostatni Jedi");
		moviesDictionary.put("Gwiezdne wojny: Zemsta Sithów", "Gwiezdne wojny - Zemsta Sithów");
		moviesDictionary.put("Harry Potter i kamień filozoficzny", "Harry Potter i Kamień Filozoficzny");
		moviesDictionary.put("Hobbit - niezwykła podróż", "Hobbit - Niezwykła podróż");
		moviesDictionary.put("Hobbit: Bitwa Pięciu Armii", "Hobbit - Bitwa Pięciu Armii");
		moviesDictionary.put("Hobbit: Niezwykła podróż", "Hobbit - Niezwykła podróż");
		moviesDictionary.put("Hobbit: Pustkowie Smauga", "Hobbit - Pustkowie Smauga");
		moviesDictionary.put("House of Cards", "House Of Cards");
		moviesDictionary.put("Król lew", "Król Lew");
		moviesDictionary.put("Labirynt fauna", "Labirynt Fauna");
		moviesDictionary.put("Mandela", "Mandela - Long Walk to Freedom");
		moviesDictionary.put("Opowieści z Narnii", "Opowieści z Narnii - Lew, Czarownica i Stara Szafa");
		moviesDictionary.put("Opowieści z Narnii: Lew, czarownica i stara szafa",
				"Opowieści z Narnii - Lew, Czarownica i Stara Szafa");
		moviesDictionary.put("Opowieści z Narnii: lew, czarownica i stara szafa",
				"Opowieści z Narnii - Lew, Czarownica i Stara Szafa");
		moviesDictionary.put("Piraci z Karaibów", "Piraci z Karaibów - Klątwa Czarnej Perły");
		moviesDictionary.put("Piraci z Karaibów - klątwa Czarnej Perły", "Piraci z Karaibów - Klątwa Czarnej Perły");
		moviesDictionary.put("Piraci z Karaibów - na krańcu świata", "Piraci z Karaibów - Na krańcu świata");
		moviesDictionary.put("Piraci z Karaibów: Na nieznanych wodach", "Piraci z Karaibów - Na nieznanych wodach");
		moviesDictionary.put("Sherlock Holmes - gra cieni", "Sherlock Holmes - Gra cieni");
		moviesDictionary.put("Slumdog. Milioner z ulicy", "Slumdog - Milioner z ulicy");
		moviesDictionary.put("Slumdog: Milioner z ulicy", "Slumdog - Milioner z ulicy");
		moviesDictionary.put("Spirited Away", "Spirited Away - W krainie bogów");
		moviesDictionary.put("Spirited Away - w krainie bogów", "Spirited Away - W krainie bogów");
		moviesDictionary.put("Star Trek: The Motion Picture", "Star Trek");
		moviesDictionary.put("Titanic: Anniversary Edition", "Titanic");
		moviesDictionary.put("Władca Pierścieni - Drużyna Pierścienia", "Władca Pierścieni - Drużyna Pierścienia");
		moviesDictionary.put("Władca Pierścieni: Dwie Wieże", "Władca Pierścieni - Dwie Wieże");
		moviesDictionary.put("Władca Pierścieni: Powrót króla", "Władca Pierścieni - Powrót Króla");
		moviesDictionary.put("Władca pierścieni: Drużyna Pierścienia", "Władca Pierścieni - Drużyna Pierścienia");
		moviesDictionary.put("Władca pierścieni: Drużyna pierścienia", "Władca Pierścieni - Drużyna Pierścienia");
		moviesDictionary.put("Władca pierścieni: Dwie wieże", "Władca Pierścieni - Dwie Wieże");
		moviesDictionary.put("Władca pierścieni: Powrót króla", "Władca pierścieni - Powrót Króla");

	}

	public static Map<String, String> getMoviesDictionary() {
		return moviesDictionary;
	}

}
