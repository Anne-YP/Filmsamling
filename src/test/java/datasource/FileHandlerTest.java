package datasource;

import domainmodel.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
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

    // Test 3: Indlæs en fil, der ikke findes
    // Test 4: Ødelagte linjer

    @Disabled
    @Test
    void loadNonExistingFile() {
        // Arrange

        // Act
        // Assert
    }

    @Disabled
    @Test
    void getSkippedLineCount() {
    }
}