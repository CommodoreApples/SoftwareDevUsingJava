import java.util.GregorianCalendar;
import java.util.Scanner;

public class PA1_2 {
    private int year;
    private int month;
    private int day;

    public PA1_2() {
        long currentTimeMillis = System.currentTimeMillis();
        setDate(currentTimeMillis);
    }

    public PA1_2(long elapsedTime) {
        setDate(elapsedTime);
    }

    public PA1_2(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setDate(long elapsedTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);

        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public static void main(String[] args) {
        PA1_2 currentDate = new PA1_2();
        PA1_2 customDate2 = new PA1_2(34355555133101L);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of years from the current date for the second date (1-10): ");
        int yearsFromCurrent = scanner.nextInt();

        long elapsedTime = System.currentTimeMillis() + yearsFromCurrent * 365L * 24L * 60L * 60L * 1000L;

        PA1_2 customDate1 = new PA1_2(elapsedTime);

        System.out.println("Current Date: " + currentDate.getYear() + "-"
                           + (currentDate.getMonth() + 1) + "-" + currentDate.getDay());
        System.out.println("Custom Date 1: " + customDate2.getYear() + "-"
                           + (customDate2.getMonth() + 1) + "-" + customDate2.getDay());
        System.out.println("Custom Date 2 (" + yearsFromCurrent + " years from current date): "
                           + customDate1.getYear() + "-"
                           + (customDate1.getMonth() + 1) + "-" + customDate1.getDay());       
    }
}