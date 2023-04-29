package Cinema;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;

public class Hall implements Comparable<Hall>, Serializable {
	
	private static final long serialVersionUID = -901752129341087075L;
	private String name;
	private TimeTable hallTimeTable;
	private Map<Days, Schedule> hallSchedule;

	public Hall(String name) {
		this.name = name;
		this.hallTimeTable = new TimeTable();
		this.hallSchedule = new TreeMap<Days, Schedule>();
	}

	public String getName() {
		return name;
	}

	public Map<Days, Schedule> getHallSchedule() {
		return hallSchedule;
	}
	
	public static Hall inputHall() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.print("Введите название кинозала: ");
		String name = scanner.nextLine();
		if (name.equals("")) {
			System.err.println("Название кинозала введено некорректно!\n");
			name = "-= Какой-то безымянный кинозал =-";
		}

		return new Hall(name);
	}

	public boolean addTimeTableToHall() throws IllegalTimeFormatException {
		boolean isDone = hallTimeTable.addTimeTableEntry();
		if (isDone) {
			System.out.println("График работы успешно добавлен в кинозал \"" + name + "\"!\n");
			return true;
		} else
			return false;
	}

	public boolean removeTimeTableFromHall() {
		boolean isDone = hallTimeTable.removeTimeTableEntry();
		if (isDone) {
			System.out.println("График работы успешно удалён из кинозала \"" + name + "\"!\n");
			return true;
		} else
			return false;
	}
	
	public static boolean isScheduleTimeNonWorkable(Days day, TimeTable timeTable, Schedule schedule)
			throws IllegalTimeFormatException {
		Time minTime = Time.getMinValue();
		Time openingTime = timeTable.getOpeningTime(day);
		Time closingTime = timeTable.getClosingTime(day);
		Time maxTime = Time.getMaxValue();
		Map<Time, Boolean> isTimeFree = schedule.getIsTimeFree();

		boolean isScheduleTimeNonWorkable = false;
		boolean isScheduleTimeBeforeOpeningNonWorkable = true;
		boolean isScheduleTimeAfterClosingNonWorkable = true;

		for (int t = Time.timeToMinutes(minTime); t < Time.timeToMinutes(openingTime); t++) {
			if (!isTimeFree.get(Time.minutesToTime(t))) {
				isScheduleTimeBeforeOpeningNonWorkable = false;
				break;
			}
		}

		for (int t = Time.timeToMinutes(Time.sumTime(closingTime, new Time(0, 1)));
				 t <= Time.timeToMinutes(maxTime);
				 t++) {
			if (!isTimeFree.get(Time.minutesToTime(t))) {
				isScheduleTimeAfterClosingNonWorkable = false;
				break;
			}
		}

		if (isScheduleTimeBeforeOpeningNonWorkable && isScheduleTimeAfterClosingNonWorkable) {
			isScheduleTimeNonWorkable = true;
		}

		return isScheduleTimeNonWorkable;
	}

	public boolean addScheduleToHall() throws IllegalTimeFormatException {
		Days day = Days.inputDay();
		if (day == null)
			return false;

		if (hallTimeTable.findDayInTimeTable(day).isPresent()) {
			
			Movie movie = Movie.inputMovie();
			Schedule schedule = new Schedule();
			schedule.addSeance(movie);

			if (isScheduleTimeNonWorkable(day, hallTimeTable, schedule)) {
				hallSchedule.put(day, schedule);
				System.out.println("Расписание сеансов на " + day.toLiteral(true) + " успешно добавлено в кинозал \"" + name + "\"!\n");
				return true;
			} else {
				System.err.println("Добавление расписания сеансов на " + day.toLiteral(true) + " в кинозал \"" + name
						+ "\" невозможно, т.к. существуют сеансы в нерабочее время кинозала!\n");
				return false;
			}
		} else {
			System.err.println(day.toLiteral(true) + " отсутствует в графике работы!\n");
			return false;
		}
	}
	
	public boolean removeScheduleFromHall() {
		Days day = Days.inputDay();
		if (day == null)
			return false;

		Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallSchedule.entrySet().stream()
				.filter(entry -> entry.getKey().equals(day)).findFirst();

		if (hallScheduleEntryFound.isPresent()) {
			hallSchedule.remove(hallScheduleEntryFound.get().getKey());
			System.out.println("Расписание сеансов на " + day.toLiteral(true) + " успешно удалено из кинозала \"" + name + "\"!\n");
			return true;
		} else {
			System.err.println(day.toLiteral(true) + " отсутствует в графике работы!\n");
			return false;
		}
	}

	public void viewHallTimeTable() {
		System.out.print("Кинозал \"" + name + "\"\n");
		hallTimeTable.viewTimeTable();
		System.out.println();
	}
	
	public Function<Entry<Days, Schedule>, String> hallScheduleToString() {
		return entry -> "Расписание сеансов в " + entry.getKey().toLiteral(true) + ":\n" + entry.getValue().toString();
	}
	
	public void viewHallSchedule() {
		System.out.print("Расписание сеансов кинозала \"" + name + "\":\n");
		hallSchedule.entrySet().stream().map(hallScheduleToString()).collect(toList()).forEach(System.out::println);
		System.out.println();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hallSchedule == null) ? 0 : hallSchedule.hashCode());
		result = prime * result + ((hallTimeTable == null) ? 0 : hallTimeTable.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hall other = (Hall) obj;
		if (hallSchedule == null) {
			if (other.hallSchedule != null)
				return false;
		} else if (!hallSchedule.equals(other.hallSchedule))
			return false;
		if (hallTimeTable == null) {
			if (other.hallTimeTable != null)
				return false;
		} else if (!hallTimeTable.equals(other.hallTimeTable))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		if (name == "-= Какой-то безымянный кинозал =-") {
			return (String) name;
		} else
			return "Кинозал \"" + name + "\"";
	}
	
	@Override
	public int compareTo(Hall anotherHall) {
		return this.name.compareTo(anotherHall.getName());		
	}
}
