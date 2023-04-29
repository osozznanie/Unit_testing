package Cinema;

import java.io.Serializable;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Map.Entry;

public class Cinema implements Serializable {

	private static final long serialVersionUID = -2991843421434092287L;
	private String name;
	private TimeTable cinemaTimeTable;
	private TreeSet<Hall> cinemaHalls;

	public Cinema(String name) {
		this.name = name;
		this.cinemaTimeTable = new TimeTable();
		this.cinemaHalls = new TreeSet<Hall>();
	}

	public String getName() {
		return name;
	}

	public static Cinema inputCinema() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.print("Введите название кинотеатра: ");
		String name = scanner.nextLine();
		if (name.equals("")) {
			System.err.println("Название кинотеатра введено некорректно!");
			name = "-= Какой-то безымянный кинотеатр =-";
		}
		System.out.println("Кинотеатр \"" + name.toString() + "\" успешно создан!\n");
		return new Cinema(name);
	}

	public void addHallToCinema() {
		Hall cinemaHall = Hall.inputHall();
		cinemaHalls.add(cinemaHall);
		System.out.println("Кинозал \"" + cinemaHall.getName() + "\" успешно добавлен в кинотеатр \"" + name + "\"!\n");
	}

	public Optional<Hall> getHallFromSet(Hall cinemaHall) {
		Optional<Hall> hallFound = cinemaHalls.stream().filter(entry -> entry.getName().equals(cinemaHall.getName()))
				.findFirst();

		return hallFound;
	}

	public boolean removeHallFromCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			cinemaHalls.remove(hallFound.get());
			System.out.println(
					"Кинозал \"" + hallFound.get().getName() + "\" успешно удалён из кинотеатра \"" + name + "\"!\n");
			return true;
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addTimeTableToCinema() throws IllegalTimeFormatException {
		boolean isDone = cinemaTimeTable.addTimeTableEntry();
		if (isDone) {
			System.out.println("График работы кинотеатра \"" + name + "\" успешно изменён!\n");
			return true;
		} else {
			System.err.println("Внести изменения в график работы кинотеатра \"" + name + "\" не удалось!\n");
			return false;
		}
	}

	public boolean removeTimeTableFromCinema() {
		boolean isDone = cinemaTimeTable.removeTimeTableEntry();
		if (isDone) {
			System.out.println("График работы кинотеатра \"" + name + "\" успешно изменён!\n");
			return true;
		} else {
			System.err.println("Внести изменения в график работы кинотеатра \"" + name + "\" не удалось!\n");
			return false;
		}
	}

	public boolean addTimeTableToHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().addTimeTableToHall();

			if (isDone) {
				System.out.println("График работы для \"" + hallFound.get() + "\" успешно добавлен в кинотеатр \""
						+ name + "\"!\n");
				return true;
			} else {
				System.err.println("Внести изменения в график работы кинотеатра \"" + name + "\" не удалось!\n");
				return false;
			}
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeTimeTableFromHallInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().removeTimeTableFromHall();

			if (isDone) {
				System.out.println("График работы для \"" + hallFound.get() + "\" успешно удалён из кинотеатра \""
						+ name + "\"!\n");
				return true;
			} else {
				System.err.println("Внести изменения в график работы кинотеатра \"" + name + "\" не удалось!\n");
				return false;
			}
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addScheduleToHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().addScheduleToHall();

			if (isDone) {
				System.out.println("Расписание сеансов для \"" + hallFound.get() + "\" успешно добавлен в кинотеатр \""
						+ name + "\"!\n");
				return true;
			} else {
				System.err.println("Внести изменения в расписание сеансов для \"" + hallFound.get() + "\" кинотеатра \""
						+ name + "\" не удалось!\n");
				return false;
			}
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeScheduleFromHallInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().removeScheduleFromHall();

			if (isDone) {
				System.out.println("Расписание сеансов для \"" + hallFound.get() + "\" успешно удалён из кинотеатра \""
						+ name + "\"!\n");
				return true;
			} else {
				System.err.println("Внести изменения в расписание сеансов для \"" + hallFound.get() + "\" кинотеатра \""
						+ name + "\" не удалось!\n");
				return false;
			}
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addSeanceToScheduleInHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			Days day = Days.inputDay();
			if (day == null)
				return false;

			Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
					.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

			if (hallScheduleEntryFound.isPresent()) {
				Movie movie = Movie.inputMovie();
				boolean isDone = hallScheduleEntryFound.get().getValue().addSeance(movie);

				if (isDone)
					return true;
				else
					return false;
			} else {
				System.err.println(day.toLiteral(true) + " отсутствует в расписании сеансов для \"" + hallFound.get()
						+ "\" кинотеатра \"" + name + "\"!\n");
				return false;
			}
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeSeanceFromScheduleInHallInCinema() throws IllegalTimeFormatException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			Days day = Days.inputDay();
			if (day == null)
				return false;

			Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
					.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

			if (hallScheduleEntryFound.isPresent()) {
				Movie movie = Movie.inputMovie();
				Seance removingSeance = Seance.inputSeance(movie);
				boolean isDone = hallScheduleEntryFound.get().getValue().removeSeance(removingSeance);

				if (isDone)
					return true;
				else
					return false;
			} else {
				System.err.println(day.toLiteral(true) + " отсутствует в расписании сеансов для \"" + hallFound.get()
						+ "\" кинотеатра \"" + name + "\"!\n");
				return false;
			}
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
			return false;
		}
	}

	public void addMovieToSeanceInScheduleInHallInCinema() throws IllegalTimeFormatException {
		Movie movie = Movie.inputMovie();

		boolean addOneMoreSeance;

		do {
			Hall cinemaHall = Hall.inputHall();

			Optional<Hall> hallFound = getHallFromSet(cinemaHall);

			if (hallFound.isPresent()) {
				Days day = Days.inputDay();
				if (day == null)
					return;

				Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
						.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

				if (hallScheduleEntryFound.isPresent()) {
					hallScheduleEntryFound.get().getValue().addSeance(movie);
				} else {
					System.err.println(day.toLiteral(true) + " отсутствует в расписании сеансов для \""
							+ hallFound.get() + "\" кинотеатра \"" + name + "\"!\n");
				}
			} else {
				System.err
						.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
			}

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);

			System.out.print("Добавить еще один сеанс? (true/false) ");
			addOneMoreSeance = scanner.nextBoolean();

			if (!addOneMoreSeance) {
				System.out.println("Вы отказались от дальнейшего добавления сеансов для данного фильма!\n");
			}

		} while (addOneMoreSeance);
	}

	public void removeMovieFromSeanceInScheduleInAllHallsInCinema() throws IllegalTimeFormatException {
		Movie movie = Movie.inputMovie();
		boolean isDone = false;

		for (Hall hall : cinemaHalls) {
			for (Days day : Days.values()) {
				Optional<Seance> seance = hall.getHallSchedule().entrySet().stream()
						.filter(entry -> entry.getKey().equals(day)).findFirst().get().getValue()
						.getMovieSeanceFromSet(movie);

				if (seance.isPresent()) {
					hall.getHallSchedule().entrySet().stream().filter(entry -> entry.getKey().equals(day)).findFirst()
							.get().getValue().removeSeance(seance.get());
					isDone = true;
				} else
					break;
			}
		}

		if (isDone) {
			System.out.println(movie.toString() + " успешно удалён из расписания сеансов всех залов кинотеатра!\n");
		} else {
			System.err.println("Удалить " + movie.toString()
					+ " из расписания сеансов всех залов кинотеатра не удалось, т.к. такого фильма нет в расписании кинтоеатра!\n");
		}
	}

	public void viewCinemaTimeTable() {
		System.out.print("Кинотеатр \"" + name + "\"\n");
		cinemaTimeTable.viewTimeTable();
		System.out.println();
	}

	public void viewCinemaHalls() {
		System.out.println("Перечень кинозалов кинотеатра \"" + name + "\":");
		cinemaHalls.forEach(System.out::println);
		System.out.println();
	}

	public boolean viewHallTimeTableInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().viewHallTimeTable();
			return true;
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean viewHallScheduleInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			hallFound.get().viewHallSchedule();
			return true;
		} else {
			System.err.println("Кинозал \"" + cinemaHall.getName() + "\" отсутствует кинотеатре \"" + name + "\"!\n");
			return false;
		}
	}

	@Override
	public String toString() {
		if (name == "-= Какой-то безымянный кинотеатр =-") {
			return (String) name;
		} else
			return "Кинотеатр \"" + name + "\"";
	}
}
