package stx.abs;

public abstract class MyAbstract {
	public MyAbstract() {
		System.out.println("MyAbstract Constructor : " + this.toString());
	}
	public abstract void printName();
	public void printConcreteName() {
		System.out.println("MyAbstract printName");
	}
}
