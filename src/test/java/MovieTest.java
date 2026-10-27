import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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

    @Test
    void hasBeenWatched() {
            Movie movie = new Movie(
                    "Batman",
                    "Tim Burton",
                    1989,
                    true,
                    126,
                    "Action");

            assertFalse(movie.hasBeenWatched());
        }

    @Test
    void getLastWatched() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        LocalDate date = LocalDate.of(2026, 10, 26);
        movie.markAsWatched(date);

        assertEquals(date, movie.getLastWatched());
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

    @Test
    void markAsWatched() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        LocalDate date = LocalDate.of(2026, 10, 26);

        movie.markAsWatched(date);

        assertEquals(date, movie.getLastWatched());
    }

    @Test
    void getDaysSinceWatched() {
        Movie movie = new Movie(
                "Batman",
                "Tim Burton",
                1989,
                true,
                126,
                "Action");

        movie.markAsWatched(LocalDate.of(2026, 10, 20));

        assertEquals(6L, movie.getDaysSinceWatched(LocalDate.of(2026, 10, 26)));
    }

    @Disabled
    @Test
    void testToString() {
    }
}