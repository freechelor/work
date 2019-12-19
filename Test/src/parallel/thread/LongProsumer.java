package parallel.thread;

import java.util.Random;

public class LongProsumer implements Runnable{
	Worker worker;
	int id;
	public LongProsumer(Worker w, int id) {
		worker = w;
		id = id;
	}
	
	public void run() {
		Random r = new Random();
		int cnt = 0;
		while(true) {
			if(r.nextInt()%3!=0)
				worker.addLong(r.nextLong());
			else
				worker.removeLong();
			cnt++;
			if(cnt>100 && cnt%2==1)
				try {
					worker.printItAll();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public static void main(String args[]) {
		Worker w = new Worker();
		LongProsumer lp = new LongProsumer(w, 0);
		LongProsumer lp2 = new LongProsumer(w, 9);
		
		Thread thread1 = new Thread(lp);
		Thread thread2 = new Thread(lp2);
		
		thread1.start();
		thread2.start();
	}
}

