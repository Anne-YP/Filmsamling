package datasource;

import domainmodel.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    private static final String TEST_FILE = "test-movies.csv";

    @AfterEach
    void deleteTestFile() {
        new File(TEST_FILE).delete();
    }

    @Test
    void savedMoviesCanBeLoadedAgain() throws FileNotFoundException {

        // Arrange
        FileHandler fileHandler = new FileHandler(TEST_FILE);

        Movie watched = new Movie("Adams æbler", "Anders Thomas Jensen", 2005,
                                true, 94, "Komedie");
        watched.markAsWatched(LocalDate.of(2025, 10, 30));

        Movie notWatched = new Movie("Jaws", "Steven Spielberg", 1975,
                                    false, 124, "Thriller");

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(watched);
        movies.add(notWatched);

        // Act
        fileHandler.saveMovies(movies);
        ArrayList<Movie> loaded = fileHandler.loadMovies();

        // Assert
        assertEquals(2, loaded.size());
        assertEquals("Adams æbler", loaded.get(0).getTitle());
        assertEquals("Jaws", loaded.get(1).getTitle());
    }

    @Test
    void savingEmptyListGivesEmptyListOnLoad() throws FileNotFoundException {
        // Arrange
        FileHandler fileHandler = new FileHandler(TEST_FILE);

        // Act
        fileHandler.saveMovies(new ArrayList<>());
        ArrayList<Movie> loaded = fileHandler.loadMovies();

        // Assert
        assertTrue(loaded.isEmpty());

    }

    @Test
    void loadNonExistingFile() {
        // Arrange
        FileHandler fileHandler = new FileHandler("does-not-exist.csv");

        // Act + Assert
        assertThrows(FileNotFoundException.class, () -> fileHandler.loadMovies());
    }

    @Test
    void getSkippedLineCount() throws FileNotFoundException {
        // Arrange
        PrintStream output = new PrintStream(new File(TEST_FILE));

        output.println("title;director;yearCreated;InColor;lengthInMinutes;genre;lastWatched");

        output.println("Jaws;Steven Spielberg;1975;false;124;Thriller;");

        output.println("ForkertData");
        output.println("Alien;Ridley Scott");
        output.close();

        FileHandler fileHandler = new FileHandler(TEST_FILE);

        // Act
        ArrayList<Movie> movies = fileHandler.loadMovies();

        // Assert
        assertEquals(1, movies.size());

        assertEquals(2, fileHandler.getSkippedLineCount());
    }
}