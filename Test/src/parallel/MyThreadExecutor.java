package parallel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyThreadExecutor {
	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String args[]) throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
		List<PartialWork> works = new ArrayList<>();
		// split work into PartialWork
		try(BufferedReader scanner = new BufferedReader(new FileReader(new File("./data.txt")))) {
			String word = null;
			List<String> work = new ArrayList<>();
			int i = 0;
			String line;
			while((line=scanner.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line);
				while(st.hasMoreTokens()) {
					word = st.nextToken();
					//System.out.println("word : " + word);
					i++;
					work.add(word);
					
					if(i>=100) {
						i=0;
						PartialWork pWork = new PartialWork(work);
						works.add(pWork);
						work = new ArrayList<>();
					}
				}
			}
			PartialWork pWork = new PartialWork(work);
			works.add(pWork);
		}
		List<PartialResult> results = new ArrayList<>();
		System.out.println("work size : " + works.size());
		for(int i=0; i<works.size(); i++) {
			results.add(null);
		}
		final CyclicBarrier gate = new CyclicBarrier(9);
		final int[] ids = new int[] {0,1,2,3,4,5,6,7};
		Thread[] threads = new Thread[8];
		for(int thNum=0; thNum<threads.length; thNum++) {
			if(threads[thNum%8] == null) {
				threads[thNum%8] = new MyThread(thNum) {
					@Override
					public void run() {
						try {
							gate.await();
							// do stuff
							int id=ids[this.id];
							System.out.println("id : " + id);
							while(id<works.size() && works.get(id)!=null) { 
//								System.out.println("id in while loop : " + id);
								results.set(id, works.get(id).doThings());
								//System.out.println(results.get(id).getFirst());
								id += 8;
							}
						} catch (InterruptedException | BrokenBarrierException e) {
							e.printStackTrace();
						}
					}
				};
			} else {
				
			}
		}
        long start = System.currentTimeMillis();
		for(Thread thread : threads) {
			thread.start();
		}
		
		try {
			gate.await();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		for(Thread thread : threads) {
			thread.join();
		}
		long l = System.currentTimeMillis()-start;
		System.out.println("result size : " + results.size());
		for(int i=0; i<results.size(); i++) {
			PartialResult r = results.get(i);
			System.out.println("result : " + r.getCnt() + " , data : " + r.getFirst());
		}
		System.out.println("Elapsed time : " + l);
	}
}

class MyThread extends Thread {
	public int id;
	public MyThread(int id) {
		this.id = id;
	}
}
