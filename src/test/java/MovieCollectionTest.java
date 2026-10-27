import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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


    @Disabled
    @Test
    void searchMovies() {
    }

    @Disabled
    @Test
    void editMovie() {
    }

    @Disabled
    @Test
    void deleteMovie() {
    }

    @Disabled
    @Test
    void getMoviesNotWatchedSince() {
    }
}