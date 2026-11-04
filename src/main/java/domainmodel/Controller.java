package domainmodel;

import datasource.FileHandler;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
   private MovieCollection movieCollection;
   private FileHandler fileHandler;
   boolean changed;

   // TODO - US13 (kører pt. test med en .txt fil

    public Controller() {
        movieCollection = new MovieCollection();
        fileHandler = new FileHandler("movies.csv");
    }


    public void addMovie (String title, String director, int yearCreated,
                          boolean inColor, int lengthInMinutes, String genre)
    {
        changed = true;
        movieCollection.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);
    }

    public ArrayList<Movie> getMovies() {
        return movieCollection.getMovies();
    }

    public ArrayList<Movie> getMoviesSortedBy(int choice) {
        return movieCollection.getMoviesSortedBy(choice);
    }

    public ArrayList<Movie> searchMovies(String searchText) {
        return movieCollection.searchMovies(searchText);
    }

    public boolean editMovie(Movie movie, String title, String director, int yearCreated,
                             boolean inColor, int lengthInMinutes, String genre) {
        changed = true;
        return movieCollection.editMovie(
                movie, title, director, yearCreated, inColor, lengthInMinutes,genre);
    }

    public boolean deleteMovie(Movie movie) {
        changed = true;
        return movieCollection.deleteMovie(movie);
    }
    public boolean markAsWatched (Movie movie) {
        changed = true;
        return movieCollection.markAsWatched(movie);
    }

    public ArrayList<Movie> getMoviesNotWatchedForDays(int days) {
        LocalDate limit = LocalDate.now().minusDays(days);
        return movieCollection.getMoviesNotWatchedSince(limit);
    }

    public int loadMovies() throws FileNotFoundException {
        ArrayList<Movie> loaded = fileHandler.loadMovies();
        for (Movie movie : loaded) {
            movieCollection.addMovie(movie);
        }
        return loaded.size();
    }

    public boolean saveMoviesIfChanged() throws FileNotFoundException {
        if(!changed) {
            return false;
        }
        fileHandler.saveMovies(movieCollection.getMovies());
        changed = false;
        return true;
    }

    public int getSkippedLineCount() {
        return fileHandler.getSkippedLineCount();
    }
}

