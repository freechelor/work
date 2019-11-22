package parallel;

import java.util.List;

public class PartialWork {
	private List<String> words = null;
	public PartialWork(List<String> wds) {
		words = wds;
	}
	
	public PartialResult doThings() {
		StringBuffer b = new StringBuffer();
		for(String s : words) {
			b.append(s.charAt(0));
		}
		return new PartialResult(words.size(), b.toString());
	}
}
