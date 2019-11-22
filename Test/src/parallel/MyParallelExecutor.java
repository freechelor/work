package parallel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyParallelExecutor {
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
		System.out.println("works size : " + works.size());
        List<Callable<PartialResult>> tasks = new ArrayList<Callable<PartialResult>>();
		// assign each work to each worker
		for(PartialWork w : works) {
			Callable<PartialResult> c = new Callable<PartialResult>() {

				@Override
				public PartialResult call() throws Exception {
					return w.doThings();
				}
			};
			tasks.add(c);
		}
		
		// execution
		ExecutorService exec = Executors.newCachedThreadPool();
        try {
            long start = System.currentTimeMillis();
            List<Future<PartialResult>> results = exec.invokeAll(tasks);
            int sum = 0;
            List<String> setOfFirst = new ArrayList<>();
            for (Future<PartialResult> fr : results) {
                sum += fr.get().getCnt();
                setOfFirst.add(fr.get().getFirst()+":");
                //System.out.println("count for task : " + fr.get().getCnt());
            }
            long elapsed = System.currentTimeMillis() - start;
            System.out.println(String.format("Elapsed time: %d ms", elapsed));
            System.out.println("total count : " + sum + ", first characters set : " + setOfFirst);
        } finally {
            exec.shutdown();
        }
        
		// collect result
		
		// get final result
		
        // some other exectuors you could try to see the different behaviours
        // ExecutorService exec = Executors.newFixedThreadPool(3);
        // ExecutorService exec = Executors.newSingleThreadExecutor();

	}
}
