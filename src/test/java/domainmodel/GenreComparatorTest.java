package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreComparatorTest {

    private GenreComparator comparator;
    private Movie reservoirDogs;
    private Movie alien;
    private Movie jaws;
    private Movie blueVelvet;

    @BeforeEach
    void setup() {
        comparator = new GenreComparator();
        reservoirDogs = new Movie("Reservoir dogs", "Quentin Tarantino", 1992, true, 99, "Crime");
        alien = new Movie("Alien", "Ridley Scott", 1979, true, 117, "Horror");
        blueVelvet = new Movie("Blue Velvet", "David Lynch", 1986, true, 120, "Crime");
        jaws = new Movie("Jaws", "Steven Spielberg", 1980, true, 125, "Thriller");
    }

    @Test
    void earlierGenreComesBefore() {
        assertTrue(comparator.compare(reservoirDogs, alien) < 0);
    }

    @Test
    void laterGenreComesBefore() {
        assertTrue(comparator.compare(jaws, alien) > 0);
    }

    @Test
    void sameGenreIsEqual() {
        assertEquals(0, comparator.compare(reservoirDogs, blueVelvet));
    }

    @Test
    void genresDifferingInCaseAreEqual() {
        Movie lowercaseMovie = new Movie("Heat", "Michael Mann", 1995, true, 170, "crime");

        assertEquals(0, comparator.compare(reservoirDogs, lowercaseMovie));
    }

    @Test
    void lowercaseGenreIsSortedByLetterNotByCase() {
        Movie lowercaseGenre = new Movie("The Thing", "John Carpenter", 1982, true, 109, "horror");

        assertTrue(comparator.compare(lowercaseGenre, jaws) < 0);
    }
}