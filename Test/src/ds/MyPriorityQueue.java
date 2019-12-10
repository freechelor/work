package ds;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class MyPriorityQueue {
	public static void main(String argsp[]) {
		Queue<Integer> natural = new PriorityQueue<Integer>(7);
		Random r = new Random();
		System.out.println("generated");
		for(int i=0; i<7; i++) {
			int gen = r.nextInt(100);
			System.out.println(gen);
			natural.add(gen);
		}
		System.out.println("natural print");
		System.out.println(natural);
		
		System.out.println("print by for each");
		for(int a: natural) {
			System.out.println(a);
		}		
		System.out.println("priority queue poll");
		for(int i=0; i<7; i++) {
			System.out.println(natural.poll());
		}
		
		Comparator<Customer> idComparator = new Comparator<Customer>() {
			@Override
			public int compare(Customer c1, Customer c2) {
				return c1.id-c2.id;
			}
		};
		Queue<Customer> comp = new PriorityQueue<>(7, idComparator);
		System.out.println("customer id generated");
		for(int i=0; i<7; i++) {
			int gen = r.nextInt(100);
			System.out.println(gen);
			comp.add(new Customer(gen, "Name"+gen));
		}
		System.out.println("customer print");
		System.out.println(comp);
		
		System.out.println("print by for each");
		for(Customer a: comp) {
			System.out.println(a.toString());
		}		
		System.out.println("priority queue poll");
		for(int i=0; i<7; i++) {
			System.out.println(comp.poll());
		}
	}
}

class Customer {
	public Customer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int id;
	public String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}
}
