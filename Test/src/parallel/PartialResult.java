package parallel;

public class PartialResult {
	private int cnt;
	private String first;
	
	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}
	
	public PartialResult(int c, String f) {
		cnt = c;
		first = f;
	}
	
}
