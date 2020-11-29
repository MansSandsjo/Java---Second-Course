package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {

	private Set<String> stopwords;
	private Map<String, Integer> ordraknare = new HashMap<String, Integer>();

	public GeneralWordCounter(Set<String> stopwords) {
		// TODO Auto-generated constructor stub
		this.stopwords = stopwords;
	}

	@Override
	public void process(String word) {
		// TODO Auto-generated method stub
		for (String key : stopwords) {
			if (stopwords.contains(word)) {
				return;
			}
		}
		if (ordraknare.containsKey(word)) {
			for (String Key : ordraknare.keySet()) {
				if (word.equals((Key))) {
					ordraknare.replace(Key, ordraknare.get(Key), ordraknare.get(Key) + 1);
				}
			}
		} else {
			ordraknare.putIfAbsent(word, 1);
		}
	}

	@Override
	public void report() {

		// Sparar över ordräknare till en HashSet, för att kunna använda
		// metoden sort -> som finns i ArrayList
		Set<Map.Entry<String, Integer>> wordSet = ordraknare.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		// Få ovan rad förklarad

		wordList.sort(new WordCountComparator());

		for (int index = 0; index < 15; index++) {
			System.out.println(wordList.get(index));
		}
	}

	public List<Map.Entry<String, Integer>> getWordList() {

		ArrayList<Map.Entry<String, Integer>> filipslista = 
				new ArrayList<Map.Entry<String, Integer>>();
		filipslista.addAll(ordraknare.entrySet()); 
		
		return filipslista;

	}
}
