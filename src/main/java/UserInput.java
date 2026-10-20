import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);

    public void addMovie() {
        System.out.println("For at tilføje en film skriv: titel, instruktør, årstal, farve/uden farve, " +
                "længde i minutter, genre.");

        String text = scanner.nextLine();
    }
}
