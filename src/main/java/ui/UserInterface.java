package ui;

import domainmodel.Controller;
import domainmodel.Movie;
import domainmodel.SortField;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        loadMovies();

        while(running) {
            showMenu();

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> createMovie();
                case "2" -> showAllMovies();
                case "3" -> searchMovies();
                case "4" -> editMovie();
                case "5" -> deleteMovie();
                case "6" -> markMovieAsWatched();
                case "7" -> System.out.println("indsæt FilmJegIkkeHarSetLænge her.");
                case "8" -> showMoviesSortedByChoice();
                case "0" -> {
                    try {
                        controller.saveMoviesIfChanged();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    running = false;
                    System.out.println("Farvel!");
                }
                default -> System.out.println("Ugyldigt valg.");
            }
        }
    }

    private void showMenu() {
        System.out.println("1. Opret en film\n" +
                "2. Vis alle film\n" +
                "3. Søg efter film\n" +
                "4. Redigér en film\n" +
                "5. Slet en film\n" +
                "6. Markér en film som set\n" +
                "7. Find film, du ikke har set længe\n" +
                "8. Vis film sorteret efter\n" +
                "0. Afslut");
        System.out.print("Vælg: ");
    }

    private void createMovie() {
        String title = "";
        while (title.isEmpty()) {
            System.out.print("Indtast titel: ");
            title = scanner.nextLine();

            if (title.isEmpty()) {
                System.out.println("Titel må ikke være tom.");
            }
        }

        String director = "";
        while(director.isEmpty() || director.matches("\\d+")) {
            System.out.print("Indtast instruktør: ");
            director = scanner.nextLine();

            if (director.isEmpty()) {
                System.out.println("Instruktør må ikke være tom.");
            }
            else if (director.matches("\\d+")) {
                System.out.println("Instruktør må ikke kun bestå af tal.");
            }
        }

        int yearCreated = 0;
        boolean validInput = false;
        while(!validInput) {
            try {
                System.out.print("Indtast årstal: ");
                yearCreated = Integer.parseInt(scanner.nextLine());

                if (yearCreated < 1888 || yearCreated > LocalDate.now().getYear() + 1) {
                    System.out.println("Årstallet skal være mellem 1888 og " + (LocalDate.now().getYear() + 1));
                }
                else {
                    validInput = true;
                }
            }
            catch(NumberFormatException e) {
                System.out.println("Det er ikke et heltal. Prøv igen.");
            }
        }

        int lengthInMinutes = 0;
        boolean validInputLength = false;
        while(!validInputLength) {
            try {
                System.out.print("Indtast filmens længde i minutter: ");
                lengthInMinutes = Integer.parseInt(scanner.nextLine());

                if (lengthInMinutes < 1) {
                    System.out.println("Filmlængden skal være mindst 1 minut.");
                }
                else {
                    validInputLength = true;
                }
            }
            catch(NumberFormatException e) {
                System.out.println("Det er ikke et heltal. Prøv igen.");
            }
        }

        boolean inColors = false;
        boolean validColorInput = false;
        while(!validColorInput) {
            System.out.print("Er filmen i farver? (ja/nej); ");
            String colors = scanner.nextLine();
            if (colors.equalsIgnoreCase("ja") || colors.equalsIgnoreCase("j")) {
                inColors = true;
                validColorInput = true;
            }
            else if (colors.equalsIgnoreCase("nej") || colors.equalsIgnoreCase("n")) {
                inColors = false;
                validColorInput = true;
            }
            else {
                System.out.println("Ugyldigt svar. Skriv ja/j eller nej/n.");
            }
        }

        String genre = "";
        while (genre.isEmpty() || genre.matches("\\d+")) {
            System.out.print("Indtast filmens genre: ");
            genre = scanner.nextLine();

            if (genre.isEmpty()) {
                System.out.println("Genre må ikke være tom.");
            }
            else if (genre.matches("\\d+")) {
                System.out.println("Genre må ikke kun bestå af tal.");
            }
        }

        try {
            controller.addMovie(title, director, yearCreated, inColors, lengthInMinutes, genre);
            System.out.println("Filmen " + title + " er blevet tilføjet.");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAllMovies() {
        System.out.println("Alle film:");

        ArrayList<Movie> movies = controller.getMoviesSortedByTitle();
        for (Movie m: movies) {
            System.out.println(m);
        }
        if (movies.isEmpty()) {
            System.out.println("Listen er tom.");
        }
    }

    private void loadMovies() {
        try {
            int count = controller.loadMovies();
            System.out.println(count + " film er indlæst.");
            int skipped = controller.getSkippedLineCount();
            if (skipped > 0) {
                System.out.println("Advarsel: " + skipped + " linje(r) i filen kunne ikke læses og er sprunget over.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Der er ingen gemt filmsamling endnu - du starter med en tom samling.");
        } catch (NullPointerException e) {
            System.out.println("Der er ingen gemt filmsamling endnu - du starter med en tom samling.");
        }
    }

    // Ift. ansvarsfordeling er det så ok, at UI har adgang til domainmodel.Movie Arrayliste?
    private void searchMovies() {
        String searchText = "";
        while(searchText.isEmpty()) {
            System.out.print("Søg efter titel: ");
            searchText = scanner.nextLine();
            if (searchText.isEmpty()) {
                System.out.print("Indtast titel: ");
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
        System.out.print("Indtast titel: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> movies = controller.searchMovies(searchText);
        if (movies.isEmpty()) {
            System.out.println("Ingen film fundet.");
            return;
        }
        System.out.println("Antal film fundet: " + movies.size());
        for (int i = 0 ; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }

        int choice = 0;
        boolean validInput = false;
        while(!validInput) {
            try {
                System.out.print("Vælg film nummer: ");
                choice = Integer.parseInt(scanner.nextLine());

                if(choice == 0) {
                    System.out.println("Redigering afbrudt.");
                    return;
                }

                if(choice < 1 || choice > movies.size()) {
                    System.out.println("Ugyldigt nummer. Prøv igen.");
                }

                else {
                    validInput = true;
                }
            }
            catch(NumberFormatException e) {
                System.out.println("Det er ikke et heltal. Prøv igen.");
            }
        }

        Movie selectedMovie = movies.get(choice - 1);
        System.out.println("Du har valgt: \n" + selectedMovie);

        String updatedTitle = selectedMovie.getTitle();
        String updatedDirector = selectedMovie.getDirector();
        int updatedYear = selectedMovie.getLengthInMinutes();
        int updatedLength = selectedMovie.getLengthInMinutes();
        String updatedGenre = selectedMovie.getGenre();
        boolean updatedInColor = selectedMovie.isInColor();

        System.out.println("Nuværende titel: " + selectedMovie.getTitle());
        System.out.print("Ny titel (tryk 'Enter' for at beholde titel): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            updatedTitle = newTitle;
        }

        System.out.println("Nuværende instruktør: " + selectedMovie.getDirector());
        boolean validDirectorInput = false;
        while (!validDirectorInput) {
            System.out.print("Ny instruktør (tryk 'Enter' for at beholde instruktør): ");
            String newDirector = scanner.nextLine();

            if (newDirector.isEmpty()) {
                validDirectorInput = true;
            }
            else if (newDirector.matches("\\d+")) {
                System.out.println("Instruktør må ikke kun bestå af tal.");
            }
            else {
                updatedDirector = newDirector;
                validDirectorInput = true;
            }
        }

        System.out.println("Nuværende årstal: " + selectedMovie.getYearCreated());
        boolean newYearInput = false;
        while (!newYearInput) {
            System.out.print("Nyt årstal (tryk 'Enter' for at beholde årstal): ");
            String newYearText = scanner.nextLine();

            if (newYearText.isEmpty()) {
                newYearInput = true;
            }
            else {
                try {
                    updatedYear = Integer.parseInt(newYearText);
                    newYearInput = true;
                }
                catch (NumberFormatException e) {
                    System.out.println("Det er ikke et heltal. Prøv igen.");
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("Nuværende filmlængde: " + selectedMovie.getLengthInMinutes());
        boolean newLengthInput = false;
        while(!newLengthInput) {
            System.out.print("Ny filmlængde (tryk 'Enter' for at beholde længde): ");
            String newLengthText = scanner.nextLine();

            if (newLengthText.isEmpty()) {
                newLengthInput = true;
            }
            else {
                try {
                    updatedLength = Integer.parseInt(newLengthText);
                    newLengthInput = true;
                }
                catch (NumberFormatException e) {
                    System.out.println("Det er ikke et heltal. Prøv igen.");
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("Nuværende genre: " + selectedMovie.getGenre());
        boolean validGenreInput = false;
        while (!validGenreInput) {
            System.out.print("Ny genre (tryk 'Enter' for at beholde genre): ");
            String newGenre = scanner.nextLine();

            if (newGenre.isEmpty()) {
                validGenreInput = true;
            }
            else if (newGenre.matches("\\d+")) {
                System.out.println("Genre må ikke kun bestå af tal.");
            }
            else {
            updatedGenre = newGenre;
            validGenreInput = true;
        }
        }

        System.out.println("Nuværende format: " + (selectedMovie.isInColor() ? "Farver" : "Sort-hvid"));
        boolean validFormatInput = false;
        while (!validFormatInput){
            System.out.print("Farver? (ja/nej, 'Enter' = behold): ");
            String colorInput = scanner.nextLine();

            if (colorInput.isEmpty()) {
                validFormatInput = true;
            }
            else if (colorInput.equalsIgnoreCase("ja") || colorInput.equalsIgnoreCase("j")) {
                updatedInColor = true;
                validFormatInput = true;
            }
            else if (colorInput.equalsIgnoreCase("nej") || colorInput.equalsIgnoreCase("n")) {
                updatedInColor = false;
                validFormatInput = true;
            }
            else {
                System.out.println("Ugyldigt svar. Skriv ja/j eller nej/n.");
            }
        }
        controller.editMovie(selectedMovie, updatedTitle, updatedDirector, updatedYear, updatedInColor, updatedLength, updatedGenre);
        System.out.println(selectedMovie);
    }

    private void deleteMovie() {
        System.out.print("Slet film valgt.\nIndtast titel: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> movies = controller.searchMovies(searchText);
        if (movies.isEmpty()) {
            System.out.println("Ingen film fundet.");
            return;
        }
        for (int i = 0 ; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }

        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Vælg film nummer: ");
                choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0){
                    System.out.println("Sletning afbrudt.");
                    return;
                }
                if (choice < 1 || choice > movies.size()) {
                    System.out.println("Ugyldigt nummer. Prøv igen.");
                }
                else {
                    validInput = true;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Det er ikke et heltal. Prøv igen.");
            }
        }

        Movie selectedMovie = movies.get(choice - 1);

        boolean validDeleteInput = false;
        while (!validDeleteInput) {
            System.out.print("Vil du slette " + selectedMovie.getTitle() + " fra filmsamlingen (j/ja - n/nej)?: ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("ja") || answer.equalsIgnoreCase("j")) {
                System.out.println("Filmen " + selectedMovie.getTitle() + " er slettet fra din filmsamling.");
                controller.deleteMovie(selectedMovie);
                validDeleteInput = true;
            }
            else if (answer.equalsIgnoreCase("nej") || answer.equalsIgnoreCase("n")) {
                System.out.println("Sletning afbrudt.");
                validDeleteInput = true;
            }
            else {
                System.out.println("Ugyldigt svar. Skriv ja/j eller nej/n.");
            }
        }
        }

        private void markMovieAsWatched() {
            System.out.print("Indtast titel: ");
            String searchText = scanner.nextLine();

            ArrayList<Movie> movies = controller.searchMovies(searchText);
            if (movies.isEmpty()) {
                System.out.println("Ingen film fundet.");
                return;
            }
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i));
            }

            int choice = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Vælg film nummer: ");
                    choice = Integer.parseInt(scanner.nextLine());

                    if (choice == 0) {
                        System.out.println("Markering afbrudt.");
                        return;
                    }
                    else if (choice < 1 || choice > movies.size()) {
                        System.out.println("Ugyldigt nummer. Prøv igen.");
                    }
                    else {
                        validInput = true;
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Det er ikke et heltal. Prøv igen.");
                }
            }

            Movie selectedMovie = movies.get(choice - 1);

            LocalDate watchDate = null;
            boolean validDate = false;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            while (!validDate) {
                System.out.print("Indtast dato (dd-mm-åååå). Tryk 'Enter' for dags dato: ");
                String dateText = scanner.nextLine();

                if (dateText.isEmpty()) {
                    watchDate = LocalDate.now();
                    validDate = true;
                }
                else {
                    try {
                        watchDate = LocalDate.parse(dateText, formatter);
                        if (watchDate.isAfter(LocalDate.now())) {
                            System.out.println("Datoen må ikke ligge i fremtiden.");
                        }
                        else {
                            validDate = true;
                        }
                    }
                    catch (DateTimeParseException e) {
                        System.out.println("Ugyldig dato. Prøv igen.");
                    }
                }
            }

           controller.markAsWatched(selectedMovie, watchDate);
            System.out.println(selectedMovie.getTitle() + " er markeret som set.");
        }

    private void showMoviesSortedByChoice() {
        System.out.println("Sortér efter:");
        System.out.println("1. Titel");
        System.out.println("2. Instruktør");
        System.out.println("3. Årstal");
        System.out.println("4. Længde");
        System.out.println("5. Genre");

        int sortChoice = 0;

        while (sortChoice < 1 || sortChoice > 5) {
            System.out.print("Vælg et tal fra 1 til 5: ");
            String input = scanner.nextLine();

            try {
                sortChoice = Integer.parseInt(input);

                if (sortChoice < 1 || sortChoice > 5) {
                    System.out.println("Vælg et tal fra 1 til 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Skriv et tal fra 1 til 5.");
            }
        }

        SortField field = switch (sortChoice) {
            case 1 -> SortField.TITLE;
            case 2 -> SortField.DIRECTOR;
            case 3 -> SortField.YEAR;
            case 4 -> SortField.LENGTH;
            default -> SortField.GENRE;
        };

        ArrayList<Movie> movies = controller.getMoviesSortedBy(field);

        if (movies.isEmpty()) {
            System.out.println("Listen er tom.");
            return;
        }

        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
    }

