package PA3;

import java.time.LocalDate;
import java.util.Scanner;

public class PA1_3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        System.out.print("Enter month (1-12): ");
        int month = scanner.nextInt();

        if (month < 1 || month > 12) {
            System.out.println("Invalid month input. Please enter a value between 1 and 12.");
            return;
        }

        printCalendar(year, month);
    }

    public static void printCalendar(int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);

        System.out.println(firstDayOfMonth.getMonth() + " " + year);

        System.out.println("Mo Tu We Th Fr Sa Su");

        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i < dayOfWeek; i++) {
            System.out.print("   ");
        }

        int lastDayOfMonth = firstDayOfMonth.lengthOfMonth();
        for (int day = 1; day <= lastDayOfMonth; day++) {
            System.out.printf("%2d ", day);

            if ((day + dayOfWeek - 1) % 7 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }
}
