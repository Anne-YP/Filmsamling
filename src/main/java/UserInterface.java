import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private Controller controller;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("1. Opret en film\n" +
                "2. Vis alle film\n" +
                "3. Søg efter film\n" +
                "4. Redigér en film\n" +
                "5. Slet en film\n" +
                "0. Afslut");
    }

    public void showPrompt() {
        System.out.print("Vælg: ");
    }

    public void startProgram() {
        boolean isRunning = true;

        System.out.println("Velkommen til min filmsamling!\n");
        showMenu();
        showPrompt();
        String choice = scanner.nextLine().trim(); //Lav separat klasse til at håndtere brugerinput

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

}

//    private void addMovie(Movie movie) {
//        controller.addMovie();
//    }
//    }
