import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Chain {

	private static Map<Integer, List<String>> words;
	private static Map<Integer, Map<String, Word>> wordMap;
	private static int highestId = 0;

	static {
		words = new HashMap<>();
		wordMap = new HashMap<>();
	}

	public static String generate(int id, int numWords) {
		if(words.get(id) == null || words.get(id).isEmpty()) return null;
		
		Word word = getRandomWord(id);
		String s = toProperCase(word.word);
		
		for(int i = 0; i < numWords - 1; i++) {
			word = word.getRandomWord();
			
			if(word == null) break;
			
			if(i != numWords - 2) 
				s += " " + word.word;
			else
				s += word.word + ".";
		}
		return s;		
	}

	public static void insert(int id, String s) {
		if(words.get(id) == null) words.put(id, new ArrayList<>());
		if(wordMap.get(id) == null) wordMap.put(id, new HashMap<>());
		
		String[] words = s.split(" ");

		for (int i = 0; i < words.length - 1; i++) {
			Word w = parseWord(id, words[i]);
			Word w2 = parseWord(id, words[i + 1]);

			w.freqList.add(w2);
		}
	}
	
	public static int getId() {
		return ++highestId;
	}

	private static Word parseWord(int id, String s) {
		s = s.trim();
		s = s.toLowerCase();
		s = s.replaceAll("[^\\w\\s-]|_", "").replaceAll("\\s+", " ");
		if (wordMap.get(id).containsKey(s)) {
			return wordMap.get(id).get(s);
		} else {
			words.get(id).add(s);
			Word word = new Word(s);
			wordMap.get(id).put(s, word);
			return word;
		}
	}

	private static Word getRandomWord(int id) {
		Random r = new Random();
		int idx = r.nextInt(words.get(id).size());
		return wordMap.get(id).get(words.get(id).get(idx));
	}

	private static String toProperCase(String s) {
		String output = s.substring(0, 1).toUpperCase() + s.substring(1);
		return output;
	}

}
