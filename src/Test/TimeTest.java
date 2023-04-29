package Test;

import Cinema.IllegalTimeFormatException;
import Cinema.Time;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

public class TimeTest {

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		protected void succeeded(org.junit.runner.Description description) {
			System.out.println("Executing " + description.getMethodName() + "... SUCCEED!");
		};

		protected void failed(Throwable exception, org.junit.runner.Description description) {
			System.out.println("Executing " + description.getMethodName() + "... FAILED!");
		};
	};

	@Test
	public void timeTest1() throws IllegalTimeFormatException {
		new Time(10, 15);
	}

	@Test(expected = IllegalTimeFormatException.class)
	public void timeTest2() throws IllegalTimeFormatException {
		new Time(10, 75);
	}

	@Test(expected = IllegalTimeFormatException.class)
	public void timeTest3() throws IllegalTimeFormatException {
		new Time(25, 15);
	}

	@Test
	public void toLiteralTest1() throws IllegalTimeFormatException {
		Time time = new Time(2, 15);
		String actualString = time.toLiteral();
		String expectedString = "2 час. 15 мин.";
		Assert.assertEquals(expectedString, actualString);
	}

	@Test
	public void toLiteralTest2() throws IllegalTimeFormatException {
		Time time = new Time(0, 15);
		String actualString = time.toLiteral();
		String expectedString = "15 мин.";
		Assert.assertEquals(expectedString, actualString);
	}

	@Test
	public void toLiteralTest3() throws IllegalTimeFormatException {
		Time time = new Time(2, 0);
		String actualString = time.toLiteral();
		String expectedString = "2 час. ";
		Assert.assertEquals(expectedString, actualString);
	}

	@Test
	public void sumTimeTest1() throws IllegalTimeFormatException {
		Time actualSum = Time.sumTime(new Time(10, 15), new Time(2, 3));
		Time expectedSum = new Time(12, 18);
		Assert.assertEquals(expectedSum, actualSum);
	}

	@Test
	public void sumTimeTest2() throws IllegalTimeFormatException {
		Time actualSum = Time.sumTime(new Time(12, 45), new Time(2, 25));
		Time expectedSum = new Time(15, 10);
		Assert.assertEquals(expectedSum, actualSum);
	}

	@Test
	public void sumTimeTest3() throws IllegalTimeFormatException {
		Time actualSum = Time.sumTime(new Time(22, 10), new Time(2, 15));
		Time expectedSum = new Time(0, 25);
		Assert.assertEquals(expectedSum, actualSum);
	}

	@Test
	public void timeToMinutesTest() throws IllegalTimeFormatException {
		Integer actual = Time.timeToMinutes(new Time(10, 10));
		Assert.assertTrue(actual == 610);
	}

	@Test
	public void minutesToTimeTest() throws IllegalTimeFormatException {
		Time actual = Time.minutesToTime(610);
		Assert.assertEquals(actual, new Time(10, 10));
	}
}
