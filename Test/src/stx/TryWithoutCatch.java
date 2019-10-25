package stx;

import java.util.Scanner;

public class TryWithoutCatch {
	public static void main(String args[]) {
		int count;
		try(Scanner scan = new Scanner(System.in)) {
			count = scan.nextInt();
		}
		System.out.println("test number : " + count);
	}
}
