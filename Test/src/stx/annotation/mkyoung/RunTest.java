package stx.annotation.mkyoung;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;



public class RunTest {

  public static void main(String[] args) throws Exception {

	System.out.println("Testing...");

	int passed = 0, failed = 0, count = 0, ignore = 0;

	Class<TestExample> obj = TestExample.class;

	// Process @MyTestInfo
	if (obj.isAnnotationPresent(MyTestInfo.class)) {

		Annotation annotation = obj.getAnnotation(MyTestInfo.class);
		MyTestInfo MyTestInfo = (MyTestInfo) annotation;

		System.out.printf("%nPriority :%s", MyTestInfo.priority());
		System.out.printf("\n");
		System.out.printf("%nCreatedBy :%s", MyTestInfo.createdBy());
		System.out.printf("%nTags :");

		int tagLength = MyTestInfo.tags().length;
		for (String tag : MyTestInfo.tags()) {
			if (tagLength > 1) {
				System.out.print(tag + ", ");
			} else {
				System.out.print(tag);
			}
			tagLength--;
		}

		System.out.printf("%nLastModified :%s%n%n", MyTestInfo.lastModified());

	}

	// Process @Test
	for (Method method : obj.getDeclaredMethods()) {

		// if method is annotated with @Test
		if (method.isAnnotationPresent(MyTest.class)) {

			Annotation annotation = method.getAnnotation(MyTest.class);
			MyTest test = (MyTest) annotation;

			// if enabled = true (default)
			if (test.enabled()) {

			  try {
				method.invoke(obj.newInstance());
				System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
				passed++;
			  } catch (Throwable ex) {
				System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
				failed++;
			  }

			} else {
				System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
				ignore++;
			}

		}

	}
	System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);

	}
}
