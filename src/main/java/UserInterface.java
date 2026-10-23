import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private Controller controller;

    public UserInterface() {
        scanner = new Scanner(System.in);
        controller = new Controller();
    }

    public void startProgram() {
        boolean running = true;
        System.out.println("Velkommen til min filmsamling!\n");

        while(running) {
            showMenu();

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                createMovie();
            }
            else if (choice.equals("2")) {
                showAllMovies();
            } else if (choice.equals("3")) {
                searchMovies();

            } else if (choice.equals("0")) {
                running = false;
                System.out.println("Farvel!");
            }
            else {
                System.out.println("Ugyldigt valgt");
            }
        }


//        switch (choice) {
//            case "1" -> addMovie();
//
////            case "2" -> showAllMovies();
////            case "3" -> searchMovies();
////            case "4" -> editMovie();
////            case "5" -> deleteMovie();
//
//            case "0" -> isRunning = false;
//            System.out.println("Tak for denne gang!");
//                break;

//            running = false;
//            default -> System.out.println("Ukendt valg: " + choice);


    }

    private void showMenu() {
        System.out.println("1. Opret en film\n" +
                "2. Vis alle film\n" +
                "3. Søg efter film\n" +
                // "4. Redigér en film\n" +
                // "5. Slet en film\n" +
                "0. Afslut");
        System.out.println("Vælg: ");
    }

    private void createMovie() {
        System.out.println("Indtast titel: ");
        String title = scanner.nextLine();

        System.out.println("Indtast instruktør: ");
        String director = scanner.nextLine();

        System.out.println("Indtast årstal: ");
        int yearCreated = Integer.parseInt(scanner.nextLine());

        System.out.println("Indtast filmens længde i minutter: ");
        int lengthInMinutes = Integer.parseInt(scanner.nextLine());

        System.out.println("Er filmen i farver? (ja/nej); ");
        boolean inColors = scanner.nextLine().equalsIgnoreCase("ja");

        System.out.println("Indtast filmens genre: ");
        String genre = scanner.nextLine();

        System.out.println("Filmen " + title + " er blevet tilføjet.");

       controller.addMovie(title, director, yearCreated, inColors, lengthInMinutes, genre);
    }

    private void showAllMovies() {
        System.out.println("Alle film:");

        ArrayList<Movie> movies = controller.getMovies();
        for (Movie m: movies) {
            System.out.println(m);
        }
    }

    private void searchMovies() {
        System.out.println("Søg efter titel: ");
        String searchText = scanner.nextLine();
        ArrayList<Movie> movies = controller.searchMovies(searchText);
        for (Movie m : movies) {
            System.out.println(m);
        }
    }
}
