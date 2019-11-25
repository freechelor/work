package stx.abs;

public class MyConcrete extends MyAbstract {

	@Override
	public void printName() {
		System.out.println("MyConcrete");
	}
	
	public MyConcrete() {
		super();
		System.out.println("Concrete : " + this.toString());
	}
	
	public String getMyClassName() {
		return this.getClass().getName();
	}
	
	public void printConcreteName() {
		System.out.println("print Concrete Name");
	}
	
	public static void main(String args[]) {
		MyConcrete con = new MyConcrete();
		con.printName();
		System.out.println(con.getMyClassName());
		
		MyAbstract absCon = new MyConcrete();
		absCon.printConcreteName();
		//absCon.printMyClassName(); compiletime error
	}
}
