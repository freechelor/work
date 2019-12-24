package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TestTestInterface {
	String value = "AA";
	@FunctionalInterface
	public interface Foo {
	    String method(String m);
	    default void defaultMethod() {}
	}
	
	public static int getLambdaAsArg(int n, TestInterface inf) {
		return inf.op(n, 4);
	}
	
	public String scopeExperiment() {
		value = "default value of scopeExperiment";
	    Foo fooIC = new Foo() {
	        String value = "Inner class value";
	 
	        @Override
	        public String method(String string) {
	            return this.value;
	        }
	    };
	    String resultIC = fooIC.method("");
	 
	    Foo fooLambda = parameter -> {
	        String value = "Lambda value";
	        return this.value;
	    };
	    String resultLambda = fooLambda.method("");
	 
	    return "Results: resultIC = " + resultIC + 
	      ", resultLambda = " + resultLambda;
	}
	
	public static void main(String args[]) {
		TestInterface plus = (x,y) -> x+y;
		TestInterface minus = (x,y) -> x-y;
		TestInterface divide = (x,y) -> x/y;
		TestInterface multiply = (x,y) -> x*y;
		
		System.out.println(plus.op(10,2));
		System.out.println(minus.op(10,2));
		System.out.println(divide.op(10,2));
		System.out.println(multiply.op(10,2));
		
		System.out.println(getLambdaAsArg(10, plus));
		System.out.println(getLambdaAsArg(10,minus));
		System.out.println(getLambdaAsArg(10,divide));
		System.out.println(getLambdaAsArg(10,multiply));
		
		System.out.println("=====================================================");
        // Consumer to multiply 2 to every integer of a list 
        Consumer<List<Integer> > modify = list -> 
        { 
            for (int i = 0; i < list.size(); i++) 
                list.set(i, 2 * list.get(i)); 
        }; 
  
        // Consumer to display a list of integers 
        Consumer<List<Integer> > 
            dispList = list -> list.stream().forEach(a -> System.out.print(a + " ")); 
  
        List<Integer> list = new ArrayList<Integer>(); 
        list.add(2); 
        list.add(1); 
        list.add(3); 
  
        // using addThen() 
        modify.accept(list);
        modify.andThen(dispList);
        modify.andThen(dispList).accept(list);
        //andThen(dispList);
        //.accept(list); 
        ; 
        
        TestTestInterface obj = new TestTestInterface();
        System.out.println(obj.scopeExperiment());
	}
}
