package Cinema;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static int menu() {
		System.out.println("-= КИНОТЕАТР =-");
		System.out.println("11 - создать новый кинотеатр");
		System.out.println("12 - сохранить кинотеатр в файл");
		System.out.println("13 - загрузить кинотеатр из файла");
		System.out.println("-= ГРАФИК РАБОТЫ КИНОТЕАТРА =-");
		System.out.println("21 - создать/изменить график работы кинотеатра на день");
		System.out.println("22 - удалить график работы кинотеатра на день");
		System.out.println("23 - вывести график работы кинотеатра на неделю");
		System.out.println("-= ЗАЛЫ КИНОТЕАТРА =-");
		System.out.println("31 - создать зал в кинотеатре");
		System.out.println("32 - удалить зал из кинотеатра");
		System.out.println("33 - вывести перечень залов кинотеатра");
		System.out.println("-= ГРАФИК РАБОТЫ ЗАЛА КИНОТЕАТРА =-");
		System.out.println("41 - создать/изменить график работы зала кинотеатра на день");
		System.out.println("42 - удалить график работы зала кинотеатра на день");
		System.out.println("43 - вывести график работы зала кинотеатра на неделю");
		System.out.println("-= РАСПИСАНИЕ СЕАНСОВ ЗАЛА КИНОТЕАТРА =-");
		System.out.println("51 - создать/изменить расписание сеансов зала кинотеатра на день");
		System.out.println("52 - удалить расписание сеансов зала кинотеатра на день");
		System.out.println("53 - вывести расписание сеансов зала кинотеатра на неделю");
		System.out.println("-= СЕАНСЫ ЗАЛА КИНОТЕАТРА НА ДЕНЬ =-");
		System.out.println("61 - добавить сеанс в расписание сеансов зала кинотеатра на день");
		System.out.println("62 - удалить сеанс из расписания сеансов зала кинотеатра на день");
		System.out.println("-= ФИЛЬМЫ =-");
		System.out.println("71 - добавить фильм и набор сеансов в расписание зала кинотеатра");
		System.out.println("72 - удалить фильм из расписания сеансов всех залов кинотеатра");
		System.out.println("-= ВЫХОД =-");
		System.out.println("0 - выйти из программы");
		System.out.println();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Сделайте Ваш выбор: ");
		int menuChoise = scanner.nextInt();

		return menuChoise;
	}

	public static void main(String[] args)
			throws NumberFormatException, IllegalTimeFormatException, IOException, ClassNotFoundException {
		Cinema cinema = null;

		while (true) {
			switch (menu()) {

			case 11: {
				cinema = Cinema.inputCinema();
				break;
			}

			case 12: {
				SerializeMethods.serialize(cinema, new File("Cinema.obj"));
				System.out.println("Кинотеатр \"" + cinema.getName() + "\" успешно сохранён в файл!\n");
				break;
			}

			case 13: {
				cinema = (Cinema) SerializeMethods.deserialize(new File("Cinema.obj"));
				System.out.println("Кинотеатр \"" + cinema.getName() + "\" успешно загружен из файла!\n");
				break;
			}

			case 21: {
				cinema.addTimeTableToCinema();
				break;
			}

			case 22: {
				cinema.removeTimeTableFromCinema();
				break;
			}

			case 23: {
				cinema.viewCinemaTimeTable();
				break;
			}

			case 31: {
				cinema.addHallToCinema();
				break;
			}

			case 32: {
				cinema.removeHallFromCinema();
				break;
			}

			case 33: {
				cinema.viewCinemaHalls();
				break;
			}

			case 41: {
				cinema.addTimeTableToHallInCinema();
				break;
			}

			case 42: {
				cinema.removeTimeTableFromHallInCinema();
				break;
			}

			case 43: {
				cinema.viewHallTimeTableInCinema();
				break;
			}

			case 51: {
				cinema.addScheduleToHallInCinema();
				break;
			}

			case 52: {
				cinema.removeScheduleFromHallInCinema();
				break;
			}

			case 53: {
				cinema.viewHallScheduleInCinema();
				break;
			}

			case 61: {
				cinema.addSeanceToScheduleInHallInCinema();
				break;
			}

			case 62: {
				cinema.removeSeanceFromScheduleInHallInCinema();
				break;
			}
			
			case 71: {
				cinema.addMovieToSeanceInScheduleInHallInCinema();
				break;
			}

			case 72: {
				cinema.removeMovieFromSeanceInScheduleInAllHallsInCinema();
				break;
			}
			
			case 0: {
				System.out.println("Надеюсь, Вам удалось по достоинству оценить весь функционал нашего Кинотеатра! Всего наилучшего!\n");
				System.exit(0);
				break;
			}

			default: {
				System.err.println("Такого пункта меню не существует!\n");
				break;
			}
			}
		}
	}
}
