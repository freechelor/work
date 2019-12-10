package stx.annotation.baeldung;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestPerson {
/*	public static void main(String args[]) throws JsonSerializationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Person person = new Person("Jason", "Choi", "27 beautiful", "Real Good Palace");
	}*/
	
	@Test
	public void givenObjectSerializedThenTrueReturned() throws JsonSerializationException {
		Person person = new Person("Jason", "Choi", "27 beautiful", "Real Good Palace");
	    ObjectToJsonConverter serializer = new ObjectToJsonConverter();
	    String jsonString = serializer.convertToJson(person);
	    System.out.println("{\"firstName\":\"Jason\",\"lastName\":\"Choi\",\"address\":\"Real Good Palace\",\"age\":\"27 beautiful\"}");
	    System.out.println(jsonString);
	    assertEquals(
	      "{\"firstName\":\"Jason\",\"lastName\":\"Choi\",\"address\":\"Real Good Palace\",\"age\":\"27 beautiful\"}",
	      jsonString);
	}
}
