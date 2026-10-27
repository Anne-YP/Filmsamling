import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    private MovieCollection collection;

    @BeforeEach
    void setUp() {
        collection = new MovieCollection();
        collection.addMovie("The Godfather", "Francis Ford Coppola", 1972,
                true, 175, "Crime");
        collection.addMovie("Casablanca", "Michael Curtiz", 1942,
                false, 102, "Drama");
        collection.addMovie("Godzilla", "Ishiro Honda", 1954,
                false, 96, "Sci-fi");
    }

    @Test
    void searchWithSeveralMatches() {
        ArrayList<Movie> result = collection.searchMovies("god");

        assertEquals(2, result.size());
    }

    @Test
    void searchMoviesNoMatches() {
        ArrayList<Movie> result = collection.searchMovies("Harry Potter");

        assertTrue(result.isEmpty());
    }


    @Test
    void addMovie() {
        collection.addMovie("Batman", "Tim Burton", 1989,
                true, 126, "Action");

        assertEquals(4, collection.getMovies().size());
    }

    @Test
    void getMovies() {
        collection.addMovie("Batman", "Tim Burton", 1989,
                true, 126, "Action");

        ArrayList<Movie> movies = collection.getMovies();

        assertEquals(4, movies.size());
    }




    @Test
    void editMovie() {
        Movie movie = collection.getMovies().get(0);

        boolean result = collection.editMovie(movie,"Joker", "Todd Phillips", 2019,
                true, 122, "Drama");

        assertTrue(result);

        assertEquals("Joker", movie.getTitle());
        assertEquals("Todd Phillips", movie.getDirector());
        assertEquals(2019, movie.getYearCreated());
        assertEquals(122, movie.getLengthInMinutes());
        assertEquals("Drama", movie.getGenre());
    }

    @Test
    void editMovieNotFound() {
        Movie movie = new Movie("Joker", "Todd Phillips", 2019,
                true, 122, "Drama");

        boolean result = collection.editMovie(movie, "New Title", "New Director", 2020,
                false, 100, "Comedy");

        assertFalse(result);

        assertEquals("Joker", movie.getTitle());
        assertEquals("Todd Phillips", movie.getDirector());
        assertEquals(2019, movie.getYearCreated());
        assertEquals(122, movie.getLengthInMinutes());
        assertEquals("Drama", movie.getGenre());
    }

    @Test
    void deleteMovie() {
        Movie movie = collection.getMovies().get(0);

        boolean result = collection.deleteMovie(movie);

        assertTrue(result);
        assertEquals(2, collection.getMovies().size());
    }

    @Test
    void deleteMovieNotFound() {
        Movie movie = new Movie("Batman", "Tim Burton", 1989,
                true, 126, "Action");

        boolean result = collection.deleteMovie(movie);

        assertFalse(result);
        assertEquals(3, collection.getMovies().size());
    }


    @Test
    void getMoviesNotWatchedSince() {

        Movie movie1 = collection.getMovies().get(0);
        Movie movie2 = collection.getMovies().get(1);

        movie1.markAsWatched(LocalDate.of(2026, 10, 20));
        movie2.markAsWatched(LocalDate.of(2026, 10, 25));

        ArrayList<Movie> result = collection.getMoviesNotWatchedSince(LocalDate.of(2026, 10, 22));
        assertEquals(2, result.size());
    }

    @Test
    void getMoviesNotWatchedSinceNoMatches() {

        for(Movie movie : collection.getMovies()) {
            movie.markAsWatched(LocalDate.of(2026, 10, 25));
        }

        ArrayList<Movie> result = collection.getMoviesNotWatchedSince(LocalDate.of(2026, 10, 20));

        assertTrue(result.isEmpty());
    }
}