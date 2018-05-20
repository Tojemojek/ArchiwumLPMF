package pl.kostrowski.lpmf.model.dictionaries;

import java.util.HashMap;
import java.util.Map;

public class AllDictionaries {

	private static Map<String, String> moviesDictionary = new HashMap<>();
	private static Map<String, String> artistDictionary = new HashMap<>();
	private static Map<String, String> songDictionary = new HashMap<>();

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
		moviesDictionary.put("Władca pierścieni: Powrót króla", "Władca Pierścieni - Powrót Króla");

		artistDictionary.put("A.R. Rahman","Allah Rakha Rahman");
		artistDictionary.put("B.J. Thomas","Billy Joe Thomas");
		artistDictionary.put("Giulia y los Tellarini","Giulia y Los Tellarini");
		artistDictionary.put("Rodrigo & Gabriela","Rodrigo y Gabriela");
		artistDictionary.put("Kelly / Reynolds / O'Connor","Gene Kelly / Donald O'Connor / Debbie Reynolds");
		artistDictionary.put("Komeda/ Osiecka/ Fetting","Agnieszka Osiecka / Krzysztof Komeda / Edmund Fetting");
		artistDictionary.put("Tykwer / Heil / Klimek","Reinhold Heil / Johnny Klimek / Tom Tykwer");
		artistDictionary.put("G.Ragni / J.Rado / G.Mc.Dermont","Gerome Ragni / James Rado / Galt MacDermot");
		artistDictionary.put("Osiecka / Komeda / Edmund Fetting","Agnieszka Osiecka / Krzysztof Komeda / Edmund Fetting");
		artistDictionary.put("Alan Menken / Celine Dion&Peabo Bryson","Alan Menken / Celine Dion / Peabo Bryson");
		artistDictionary.put("Hans Zimmer / Rodrigo & Gabriela","Hans Zimmer / Rodrigo y Gabriela");
		artistDictionary.put("Burt Bacharach / B.J. Thomas","Burt Bacharach / Billy Joe Thomas");
		artistDictionary.put("Matos Rodriguez / Maroni / Contursi","Matos Rodriguez / Pascual Contursi / Enrique Maroni");
		artistDictionary.put("Jan A.P. Kaczmarek / Leszek Możdżer","Jan A. P. Kaczmarek / Leszek Możdżer");
		artistDictionary.put("Django Reinhardt & Stephane Grappelli","Django Reinhardt / Stephane Grappelli");
		artistDictionary.put("Jamie Cullum, Clint Eastwood","Jamie Cullum / Clint Eastwood");
		artistDictionary.put("Simon & Garfunkel","Paul Simon / Art Garfunkel");
		artistDictionary.put("Simon / Garfunkel","Paul Simon / Art Garfunkel");
		artistDictionary.put("Jan A.P. Kaczmarek","Jan A. P. Kaczmarek");
		artistDictionary.put("Heil Reinhold, Klimek Johnny & Tykwer Tom","Reinhold Heil / Johnny Klimek / Tom Tykwer");
		artistDictionary.put("Reinhold / Klimek / Tykwer","Reinhold Heil / Johnny Klimek / Tom Tykwer");
		artistDictionary.put("Herve Roy","Hervé Roy");
		artistDictionary.put("Joe Hisaiashi","Joe Hisaishi");
		artistDictionary.put("Joe Hisaishi","Joe Hisaishi");
		artistDictionary.put("Randy Newman & Lyle Lovett","Randy Newman / Lyle Lovett");
		artistDictionary.put("Ryuichi Sakamoto","Ryūichi Sakamoto");

		songDictionary.put("Beauty and the Beast","Beauty and The Beast");
		songDictionary.put("Cops Or Criminals","Cops or Criminals");
		songDictionary.put("Lillies of the valley","Lillies of the Valley");
		songDictionary.put("Over The Rainbow","Over the Rainbow");
		songDictionary.put("Sleep Safe And Warm","Sleep Safe and Warm");
		songDictionary.put("Song of The Lonely Mountain","Song of the Lonely Mountain");
		songDictionary.put("The Sound of Silence","The Sound Of Silence");
		songDictionary.put("Will And Elizabeth","Will and Elizabeth");
		songDictionary.put("With A Little Help From My Friends","With a Little Help From My Friends");
		songDictionary.put("Young And Beautiful","Young and Beautiful");
		songDictionary.put("temat","Temat");
		songDictionary.put("czołówka","Czołówka");
		songDictionary.put("Sound of Silence","The Sound of Silence");

	}


	public static Map<String, String> getMoviesDictionary() {
		return moviesDictionary;
	}
	public static Map<String, String> getArtistDictionary() {return artistDictionary;}
	public static Map<String, String> getSongDictionary() {return songDictionary;}

}
