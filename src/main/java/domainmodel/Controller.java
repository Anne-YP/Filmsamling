package domainmodel;

import datasource.FileHandler;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Controller {
   private MovieCollection movieCollection;
   private FileHandler fileHandler;
   boolean changed;


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

    public ArrayList<Movie> getMoviesSortedBy(SortField field) {
        return movieCollection.getMoviesSorted(comparatorFor(field));
    }

    public ArrayList<Movie> getMoviesSortedByTitle() {
        return movieCollection.getMoviesSortedByTitle();
    }

    private Comparator<Movie> comparatorFor(SortField field) {
        return switch (field) {
            case TITLE -> new TitleComparator();
            case DIRECTOR -> new DirectorComparator();
            case YEAR -> new YearComparator();
            case LENGTH -> new LengthComparator();
            case GENRE -> new GenreComparator();
        };
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
    public boolean markAsWatched (Movie movie, LocalDate date) {
        changed = true;
        return movieCollection.markAsWatched(movie, date);
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

