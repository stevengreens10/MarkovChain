import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Word {

	public String word;
	public List<Word> freqList;
	
	public Word(String word) {
		this.word = word;
		this.freqList = new ArrayList<>();
	}
	
	public Word getRandomWord() {
		if(freqList.isEmpty()) return null;
		Random r = new Random();
		int idx = r.nextInt(freqList.size());
		return freqList.get(idx);
	}
	
}
