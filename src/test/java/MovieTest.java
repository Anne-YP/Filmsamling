import jdk.jfr.DataAmount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void isInColor() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        assertTrue(movie.isInColor());
    }

    @Test
    void getTitle() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        assertEquals("Batman", movie.getTitle());
    }

    @Test
    void getDirector() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        assertEquals("Tim Burton", movie.getDirector());
    }

    @Test
    void getYearCreated() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        assertEquals(1989, movie.getYearCreated());
    }

    @Test
    void getLengthInMinutes() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        assertEquals(126, movie.getLengthInMinutes());
    }

    @Test
    void getGenre() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        assertEquals("Action", movie.getGenre());
    }

    @Disabled
    @Test
    void isWatched() {
    }

    @Disabled
    @Test
    void getLastWatchedDate() {
    }

    @Test
    void setTitle() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        movie.setTitle("Joker");

        assertEquals("Joker", movie.getTitle());
    }

    @Test
    void setDirector() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        movie.setDirector("Christopher Nolan");

        assertEquals("Christopher Nolan", movie.getDirector());
    }

    @Test
    void setYearCreated() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        movie.setYearCreated(2000);

        assertEquals(2000, movie.getYearCreated());
    }
    
    @Test
    void setInColors(){
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                false,
                126,
                "Action");

        movie.setInColor(true);

        assertTrue(movie.isInColor());
    }

    @Test
    void setLengthInMinutes() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        movie.setLengthInMinutes(90);

        assertEquals(90, movie.getLengthInMinutes());
    }

    @Test
    void setGenre() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        movie.setGenre("Drama");

        assertEquals("Drama", movie.getGenre());
    }

    @Disabled
    @Test
    void setIsWatched() {
    }

    @Disabled
    @Test
    void testToString() {
    }
}