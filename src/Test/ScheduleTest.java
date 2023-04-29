package Test;

import Cinema.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class ScheduleTest {
	
	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		private Throwable exception;
		private Description description;

		protected void succeeded(org.junit.runner.Description description) {
			System.out.println("Executing " + description.getMethodName() + "... SUCCEED!");
		};

		protected void failed(Throwable exception, org.junit.runner.Description description) {
			this.exception = exception;
			this.description = description;
			System.out.println("Executing " + description.getMethodName() + "... FAILED!");
		};
	};

	Schedule schedule;
	
	@Before
	public void beforeTest() throws IllegalTimeFormatException {
		schedule = new Schedule();
	}

	@After
	public void afterTest() {
		schedule = null;
	}

	@Test
	public void registerSeanceInScheduleTest() throws IllegalTimeFormatException {
		schedule.registerSeanceInSchedule(new Seance(new Movie("Тест", new Time(2, 15)), new Time(10, 15)));
	}
	
	@Test
	public void scheduleBusyTimeCheckTest() throws IllegalTimeFormatException {
		schedule.registerSeanceInSchedule(new Seance(new Movie("Тест", new Time(2, 15)), new Time(10, 15)));
		Assert.assertFalse(schedule.isSeanceTimeFree(new Seance(new Movie("Тест1", new Time(1, 45)), new Time(11, 00))));		
	}
	
	@Test
	public void scheduleFreeTimeCheckTest() throws IllegalTimeFormatException {
		schedule.registerSeanceInSchedule(new Seance(new Movie("Тест", new Time(2, 15)), new Time(10, 15)));
		Assert.assertTrue(schedule.isSeanceTimeFree(new Seance(new Movie("Тест1", new Time(1, 45)), new Time(13, 00))));		
	}
}
