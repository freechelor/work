package stx.annotation.mkyoung;

import stx.annotation.mkyoung.MyTestInfo.Priority;

@MyTestInfo(
		priority = Priority.HIGH, 
		createdBy = "mkyong.com",  
		tags = {"sales","test" }
	)
	public class TestExample {

		@MyTest
		void testA() {
		  if (true)
			throw new RuntimeException("This test always failed");
		}
		
		@MyTest(enabled = false)
		void testB() {
		  if (false)
			throw new RuntimeException("This test always passed");
		}

		@MyTest(enabled = true)
		void testC() {
		  if (10 > 1) {
			// do nothing, this test always passed.
		  }
		}
	}