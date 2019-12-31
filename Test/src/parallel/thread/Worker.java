package parallel.thread;

import java.util.ArrayList;
import java.util.List;

public class Worker {
	// if you use synchronized keyword for method, which methods should you put synchronized? all of them or part of them?
	protected List<Long> list;
	
	public Worker() {
		list = new ArrayList<Long>();
	}
	
	public void addLong(Long l) {
		System.out.println("add " + l);
		list.add(l);
	}
	
	public synchronized void removeLong() {
		System.out.println("remove ");
		if(list.size()>0) list.remove(0);
	}
	
	public synchronized void printItAll() throws InterruptedException {
		for(Long l : list) {
			System.out.println("element of list ===================================================> " + l);
			fibonaci(50);
		}
	}
	
	private int fibonaci(int r) {
		if(r<=1) return r;
		return fibonaci(r-1) + fibonaci(r-2);
	}
	
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		//System.out.println(fibonaci(50));
		System.out.println(System.currentTimeMillis()-start);
	}
}
