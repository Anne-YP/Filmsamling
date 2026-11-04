package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
        controller.addMovie("Blue Velvet", "David Lynch", 1986, true, 120, "Mystery");
        controller.addMovie("Alien", "Ridley Scott", 1979, true, 117, "Horror");
        controller.addMovie("Jaws", "Steven Spielberg", 1980, true, 125, "Thriller");
        controller.addMovie("The Shining", "Stanley Kubrick", 1983, true, 140, "Horror");
        controller.addMovie("Stingray Sam", "Cory McAbee", 2001, true, 90, "Musical");
    }

    @Test
    void moviesSortedByTitleAreAlphabetical() {
        ArrayList<Movie> sorted = controller.getMoviesSortedByTitle();

        assertEquals("Alien", sorted.get(0).getTitle());
        assertEquals("Blue Velvet", sorted.get(1).getTitle());
        assertEquals("Jaws", sorted.get(2).getTitle());
        assertEquals("Stingray Sam", sorted.get(3).getTitle());
        assertEquals("The Shining", sorted.get(4).getTitle());
    }

    @Test
    void sortingDoesNotChangeCollectionOrder() {
        controller.getMoviesSortedByTitle();
        controller.getMoviesSortedBy(SortField.YEAR);

        ArrayList<Movie> movies = controller.getMovies();

        assertEquals("Blue Velvet", movies.get(0).getTitle());
        assertEquals("Alien", movies.get(1).getTitle());
        assertEquals("Jaws", movies.get(2).getTitle());
        assertEquals("The Shining", movies.get(3).getTitle());
        assertEquals("Stingray Sam", movies.get(4).getTitle());
    }

    @Test
    void moviesSortedByYearAreOldestFirst() {
        ArrayList<Movie> sorted = controller.getMoviesSortedBy(SortField.YEAR);

        assertEquals(1979, sorted.get(0).getYearCreated());
        assertEquals(1980, sorted.get(1).getYearCreated());
        assertEquals(1983, sorted.get(2).getYearCreated());
        assertEquals(1986, sorted.get(3).getYearCreated());
        assertEquals(2001, sorted.get(4).getYearCreated());
    }

}