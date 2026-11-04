package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LengthComparatorTest {

    private LengthComparator comparator;
    private Movie reservoirDogs;
    private Movie alien;
    private Movie jaws;
    private Movie blueVelvet;

    @BeforeEach
    void setup() {
        comparator = new LengthComparator();
        reservoirDogs = new Movie("Reservoir dogs", "Quentin Tarantino", 1992, true, 99, "Crime");
        alien = new Movie("Alien", "Ridley Scott", 1979, true, 117, "Horror");
        blueVelvet = new Movie("Blue Velvet", "David Lynch", 1986, true, 120, "Mystery/Crime");
        jaws = new Movie("Jaws", "Steven Spielberg", 1980, true, 124, "Thriller");
    }

    @Test
    void shorterMovieLengthComesBefore() {
        assertTrue(comparator.compare(alien, blueVelvet) < 0);
    }

    @Test
    void longMovieLengthComesAfter() {
        assertTrue(comparator.compare(blueVelvet, alien) > 0);
    }

    @Test
    void lengthComparatorSortsLongestFirst() {
        assertTrue(comparator.compare(alien, jaws) < 0);
        assertTrue(comparator.compare(jaws, reservoirDogs) > 0);
    }
}