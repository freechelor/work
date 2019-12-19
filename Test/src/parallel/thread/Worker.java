package parallel.thread;

import java.util.ArrayList;
import java.util.List;

public class Worker {
	protected List<Long> list;
	
	public Worker() {
		list = new ArrayList<Long>();
	}
	
	public void addLong(Long l) {
		list.add(l);
	}
	
	public synchronized void removeLong() {
		if(list.size()>0) list.remove(0);
	}
	
	public synchronized void printItAll() throws InterruptedException {
		for(Long l : list) {
			System.out.println("element of list : " + l);
		}
		Thread.sleep(1000);
	}
}
