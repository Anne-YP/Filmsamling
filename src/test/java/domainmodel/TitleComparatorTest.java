package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleComparatorTest {

    private TitleComparator comparator;
    private Movie reservoirDogs;
    private Movie alien;
    private Movie jaws;
    private Movie blueVelvet;

    @BeforeEach
    void setup() {
        comparator = new TitleComparator();
        reservoirDogs = new Movie("Reservoir dogs", "Quentin Tarantino", 1992, true, 99, "Crime");
        alien = new Movie("Alien", "Ridley Scott", 1979, true, 117, "Horror");
        blueVelvet = new Movie("Blue Velvet", "David Lynch", 1986, true, 120, "Mystery/Crime");
        jaws = new Movie("Jaws", "Steven Spielberg", 1980, true, 125, "Thriller");
    }

    @Test
    void earlierTitleComesBefore() {
        assertTrue(comparator.compare(alien, blueVelvet) < 0);
    }

    @Test
    void laterTitleComesAfter() {
        assertTrue(comparator.compare(blueVelvet, alien) > 0);
    }

    @Test
    void identicalTitlesAreEqual() {
        Movie sameTitleMovie = new Movie("Reservoir dogs", "Quentin Tarantino", 1992, true, 99, "Crime");

        assertEquals(0, comparator.compare(reservoirDogs, sameTitleMovie));
    }

    @Test
    void titleComparatorIgnoresCase() {

        // Arrange
        Movie lowercaseMovie = new Movie("reservoir dogs", "Quentin Tarantino", 1992, true, 99, "Crime");

        // Assert
        assertEquals(0, comparator.compare(reservoirDogs, lowercaseMovie));
    }

    @Test
    void titleComparatorSortsAlphabetically() {
        assertTrue(comparator.compare(alien, blueVelvet) < 0);
        assertTrue(comparator.compare(jaws, alien) > 0);
    }
}