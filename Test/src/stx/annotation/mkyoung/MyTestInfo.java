package stx.annotation.mkyoung;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyTestInfo {
	public enum Priority {
		LOW, MEDIUM, HIGH
	}
	
	Priority priority() default Priority.MEDIUM;
	String[] tags() default "";
	String createdBy() default "Jason";
	String lastModified() default "12/10/2019";
}
