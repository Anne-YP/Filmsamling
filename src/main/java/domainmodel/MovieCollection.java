package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<>();
    }

    public void addMovie(String title, String director, int yearCreated,
                         boolean inColor, int lengthInMinutes, String genre) {
        Movie movie = new Movie(
                title, director, yearCreated, inColor, lengthInMinutes, genre);
        movies.add(movie);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public ArrayList<Movie> getMovies() {
        return getMoviesSortedBy(1);
    }


    public ArrayList<Movie> searchMovies(String searchText) {
        ArrayList<Movie> matches = new ArrayList<>();
        String text = searchText.toLowerCase();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(text)) {
                matches.add(movie);
            }
        }
        return matches;
    }

    public boolean editMovie(Movie movie, String title, String director,
                             int yearCreated, boolean inColor,
                             int lengthInMinutes, String genre) {

        if (!movies.contains(movie)) {
            return false;
        }

        movie.setTitle(title);
        movie.setDirector(director);
        movie.setYearCreated(yearCreated);
        movie.setInColor(inColor);
        movie.setLengthInMinutes(lengthInMinutes);
        movie.setGenre(genre);

        return true;
    }

    public boolean deleteMovie(Movie movie) {
        return movies.remove(movie);
    }

    public boolean markAsWatched(Movie movie, LocalDate date) {
        if (!movies.contains(movie)) {
            return false;
        }

        movie.markAsWatched(date);
        return true;
    }

    public ArrayList<Movie> getMoviesNotWatchedSince(LocalDate date) {
        ArrayList<Movie> result = new ArrayList<>();

        for (Movie movie : movies) {
            if (!movie.hasBeenWatched()
                    || movie.getLastWatched().isBefore(date)) {
                result.add(movie);
            }
        }

        return result;
    }

    public ArrayList<Movie> getMoviesSortedBy(int choice) {
        if (choice < 1 || choice > 5) {
            throw new IllegalArgumentException("Ugyldigt sorteringsvalg.");
        }

        ArrayList<Movie> sortedMovies = new ArrayList<>(movies);

        for (int i = 0; i < sortedMovies.size(); i++) {
            for (int j = 0; j < sortedMovies.size() - 1; j++) {
                Movie firstMovie = sortedMovies.get(j);
                Movie secondMovie = sortedMovies.get(j + 1);
                boolean swap = false;

                if (choice == 1
                        && firstMovie.getTitle().compareToIgnoreCase(
                        secondMovie.getTitle()) > 0) {
                    swap = true;
                } else if (choice == 2
                        && firstMovie.getDirector().compareToIgnoreCase(
                        secondMovie.getDirector()) > 0) {
                    swap = true;
                } else if (choice == 3
                        && firstMovie.getYearCreated()
                        > secondMovie.getYearCreated()) {
                    swap = true;
                } else if (choice == 4
                        && firstMovie.getLengthInMinutes()
                        > secondMovie.getLengthInMinutes()) {
                    swap = true;
                } else if (choice == 5
                        && firstMovie.getGenre().compareToIgnoreCase(
                        secondMovie.getGenre()) > 0) {
                    swap = true;
                }

                if (swap) {
                    sortedMovies.set(j, secondMovie);
                    sortedMovies.set(j + 1, firstMovie);
                }
            }
        }

        return sortedMovies;
    }
}
