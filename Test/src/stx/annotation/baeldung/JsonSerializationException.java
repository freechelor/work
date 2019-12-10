package stx.annotation.baeldung;

public class JsonSerializationException extends Exception {
	private static final long serialVersionUID = -7742887815946648644L;
	private String exMessage;
	public JsonSerializationException(String ex) {
		exMessage = ex;
	}
	
	@Override
	public String toString() {
		return exMessage;
	}
}
