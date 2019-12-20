package my.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FileManager {
	static String matchingPattern = null;
	static File outputFile = null;
	public static void getFileList(String rootPath, boolean recursive) throws IOException {
		File[] files = new File(rootPath).listFiles();
		FileWriter writer = new FileWriter(outputFile);
		if(!recursive) {
			System.out.println("non recursive");
			for(File f : files) {
				System.out.println(rootPath+"\\"+f.getName());
				writer.append(f.getAbsolutePath()+"\n");
			}
			writer.flush();
		} /*else {

			System.out.println("recursive");
			for(File f : files) {
				if(f.isDirectory()) {
					System.out.println(rootPath+"\\"+f.getName());
					getFileList(rootPath+"\\"+f.getName(), recursive);
				} else {
					System.out.println(rootPath+"\\"+f.getName());
					writer.append(f.getName()+"\n");
				}
			}
			writer.flush();
		}*/
		else {
			Queue<File> q = new LinkedList<File>();
			q.offer(new File(rootPath));
			while(!q.isEmpty()) {
				File f = q.poll();
				if(isMatched(f.getAbsolutePath(), matchingPattern)) writer.write("INSERT INTO dbo.Controllers (Controller) values (" + "\'" + f.getName() +"\')\n");
				if(f.isDirectory()) {
					
					File[] fs = f.listFiles();
					for(File fe : fs) {
						q.offer(fe);
					}
				}
			}
			writer.flush();
		}

		writer.close();
	}
	
/*	public static void getFileList(String rootPath) throws IOException {
		File[] files = new File(rootPath).listFiles();
		FileWriter writer = new FileWriter(outputFile);
		for(File f : files) {
			writer.write(f.getAbsolutePath()+"\n");
		}
		writer.close();
	}*/
	
	public static void setOutputFile(String fileName) {
		outputFile = new File(fileName);
		
	}
	
	public static void setMatchingPattern(String pattern) {
		matchingPattern = pattern;
	}
	
	public static boolean isMatched(String path, String pattern) {
		String[] tokens = null;
		if(pattern.indexOf("*")>=0) {
			tokens = pattern.split("*");
			if(tokens.length>1) return false;
			if(path.contains(tokens[0])) return true;
		} else {
			if(path.contains(pattern)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String args[]) {
		setOutputFile("filelist.txt");
		try {
			getFileList(".", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		setOutputFile("filelistRecursive.txt");
		try {
			getFileList(".", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		setOutputFile("filelistController.txt");
		setMatchingPattern("Controller.cs");
		try {
			getFileList("C:\\Users\\Jason\\Documents\\Visual Studio 2015\\Projects\\nuwaveERP1120\\nuwaveERP", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
