import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private Controller controller;

    public UserInterface() {
        scanner = new Scanner(System.in);
        controller = new Controller();

        addTestMovies();
    }

    private void addTestMovies() {
        controller.addMovie("Slugs", "Juan Piquer Simón",
                1988, true, 92, "Horror");
         controller.addMovie("Disgusting Spaceworms Eat Everyone!!", "Geaorge Keller",
           1989, true, 72, "Sci-Fi/Horror/Comedy");
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

            } else if (choice.equals("4")) {
                editMovie();

            } else if (choice.equals("5")) {
                deleteMovie();

            } else if (choice.equals("0")) {
                running = false;
                System.out.println("Farvel!");
            }

            else {
                System.out.println("Ugyldigt valgt");
            }
        }
    }

    private void showMenu() {
        System.out.println("1. Opret en film\n" +
                "2. Vis alle film\n" +
                "3. Søg efter film\n" +
                "4. Redigér en film\n" +
                "5. Slet en film\n" +
                "0. Afslut");
        System.out.println("Vælg: ");
    }

    private void createMovie() {
        String title = "";
        while (title.isEmpty()) {
            System.out.println("Indtast titel: ");
            title = scanner.nextLine();

            if (title.isEmpty()) {
                System.out.println("Titel må ikke være tom.");
            }
        }

        String director = "";
        while(director.isEmpty()) {
            System.out.println("Indtast instruktør: ");
            director = scanner.nextLine();

            if (director.isEmpty()) {
                System.out.println("Instruktør må ikke være tom.");
            }
        }

        int yearCreated = 0;
        boolean validInput = false;

        while(!validInput) {
            try {
                System.out.println("Indtast årstal: ");
                yearCreated = Integer.parseInt(scanner.nextLine());

                validInput = true;
            }
            catch(NumberFormatException e) {
                System.out.println("Det er ikke et heltal. Prøv igen.");
            }
        }



        int lengthInMinutes = 0;
        boolean validInputLength = false;

        while(!validInputLength) {
            try {
                System.out.println("Indtast filmens længde i minutter: ");
                lengthInMinutes = Integer.parseInt(scanner.nextLine());

                validInputLength = true;
            }

            catch(NumberFormatException e) {
                System.out.println("Det er ikke et heltal. Prøv igen.");
            }
        }


        System.out.println("Er filmen i farver? (ja/nej); ");
        String colors = scanner.nextLine();
        boolean inColors = colors.equalsIgnoreCase("ja") || colors.equalsIgnoreCase("j");

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
        if (movies.isEmpty()) {
            System.out.println("Listen er tom.");
        }
    }

    private void searchMovies() {
        String searchText = "";
        while(searchText.isEmpty()) {
            System.out.println("Søg efter titel: ");
            searchText = scanner.nextLine();
            if (searchText.isEmpty()) {
                System.out.println("Indtast titel: ");
            }
        }
        ArrayList<Movie> movies = controller.searchMovies(searchText);
        if (movies.isEmpty()) {
            System.out.println("Film ikke fundet.");
        }
        else {
            for (Movie m : movies) {
                System.out.println(m);
            }
        }
    }

    private void editMovie() {
        System.out.println("Indtast titel: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> movies = controller.searchMovies(searchText);
        for (int i = 0 ; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }

        int choice = 0;
        boolean validInput = false;

        while(!validInput) {
            try {
                System.out.println("Vælg film nummer: ");
                choice = Integer.parseInt(scanner.nextLine());

                if(choice == 0) {
                    System.out.println("Redigering afbrudt.");
                    return;
                }

                validInput = true;
            }

            catch(NumberFormatException e) {
                System.out.println("Det er ikke et heltal. Prøv igen.");
            }
        }

        Movie selectedMovie = movies.get(choice - 1);
        System.out.println("Du har valgt: \n" + selectedMovie);

        System.out.println("Nuværende titel: " + selectedMovie.getTitle());
        System.out.println("Ny titel (tryk 'Enter' for at beholde titel): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            selectedMovie.setTitle(newTitle);
        }

        System.out.println("Nuværende instruktør: " + selectedMovie.getDirector());
        System.out.println("Ny instruktør (tryk 'Enter' for at beholde instruktør): ");
        String newDirector = scanner.nextLine();
        if (!newDirector.isEmpty()) {
            selectedMovie.setDirector(newDirector);
        }

        int choiceYear = 0;
        boolean validInputYear = false;

        System.out.println("Nuværende årstal: " + selectedMovie.getYearCreated());
        System.out.println("Nyt årstal (tryk 'Enter' for at beholde årstal): ");
        String newYearText = scanner.nextLine();
        if (!newYearText.isEmpty()) {
            selectedMovie.setYearCreated(Integer.parseInt(newYearText));
        }

        System.out.println("Nuværende filmlængde: " + selectedMovie.getLengthInMinutes());
        System.out.println("Ny filmlængde (tryk 'Enter' for at beholde længde): ");
        String newLengthText = scanner.nextLine();
        if (!newLengthText.isEmpty()) {
            selectedMovie.setLengthInMinutes(Integer.parseInt(newLengthText));
        }

        System.out.println("Nuværende genre: " + selectedMovie.getGenre());
        System.out.println("Ny genre (tryk 'Enter' for at beholde genre): ");
        String newGenre = scanner.nextLine();
        if (!newGenre.isEmpty()) {
            selectedMovie.setGenre(newGenre);
        }

        System.out.println("Nuværende format: " + (selectedMovie.isInColor() ? "Farver" : "Sort-hvid"));
        System.out.println("Farver? (ja/nej, 'Enter' = behold): ");
        String colorInput = scanner.nextLine();
        if (!colorInput.isEmpty()) {
            selectedMovie.setInColor(colorInput.equalsIgnoreCase("ja"));
        }

        System.out.println(selectedMovie);
    }

    private void deleteMovie() {
        System.out.println("Slet film valgt.\nIndtast titel: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> movies = controller.searchMovies(searchText);
        for (int i = 0 ; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }

        System.out.println("Vælg film nummer: ");
        int choice = Integer.parseInt(scanner.nextLine());
        Movie selectedMovie = movies.get(choice - 1);
        System.out.println("Vil du slette " + selectedMovie.getTitle() + " fra filmsamlingen (j/ja - n/nej)?");

        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("ja") || answer.equalsIgnoreCase("j")){
            boolean deleted = controller.deleteMovie(selectedMovie);
            if (deleted) {
                System.out.println("Filmen " + selectedMovie.getTitle() + " er slettet fra din filmsamling.");
            } else {
                System.out.println("Filmen kunne ikke slettes.");
            }
        }
        else {
                System.out.println("Sletning afbrudt.");
            }
        }
    }
