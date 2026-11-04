package domainmodel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Movie implements Comparable<Movie> {
    private static final int FIRST_MOVIE_YEAR = 1888;

    private String title;
    private String director;
    private int yearCreated;
    private boolean inColor;
    private int lengthInMinutes;
    private String genre;

    private LocalDate lastWatched;

    public Movie(String title, String director, int yearCreated,
                 boolean inColor, int lengthInMinutes, String genre) {

        setTitle(title);
        setDirector(director);
        setYearCreated(yearCreated);
        setInColor(inColor);
        setLengthInMinutes(lengthInMinutes);
        setGenre(genre);

        this.lastWatched = null;
    }

    // Getters
    public boolean isInColor() {
        return inColor;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    public boolean hasBeenWatched() {
        return lastWatched != null;
    }

    public LocalDate getLastWatched() {
        return lastWatched;
    }

    public long getDaysSinceWatched(LocalDate today) {
        return ChronoUnit.DAYS.between(lastWatched, today);
    }

    // Setters
    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Titlen må ikke være tom.");
        }
        this.title = title;
    }

    public void setDirector(String director) {
        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException("Instruktøren må ikke være tom.");
        }
        this.director = director;
    }

    public void setYearCreated(int yearCreated) {
        int latestYear = LocalDate.now().getYear() + 1;

        if (yearCreated < FIRST_MOVIE_YEAR || yearCreated > latestYear) {
            throw new IllegalArgumentException(
                    "Årstallet skal være mellem "
                            + FIRST_MOVIE_YEAR + " og " + latestYear + ".");
        }
        this.yearCreated = yearCreated;
    }

    public void setInColor(boolean inColor) {
        this.inColor = inColor;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        if (lengthInMinutes < 1) {
            throw new IllegalArgumentException(
                    "Filmlængden skal være mindst 1 minut.");
        }
        this.lengthInMinutes = lengthInMinutes;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Genre må ikke være tom.");
        }
        this.genre = genre;
    }

    public void markAsWatched(LocalDate date) {
        if (date == null || date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Datoen må ikke være tom eller ligge i fremtiden.");
        }
        lastWatched = date;
    }

    @Override
    public int compareTo(Movie other) {
        return title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        String colorText = inColor ? "Farver" : "Sort-hvid";

        String watchedText;

        if(!hasBeenWatched()) {
            watchedText = "Ikke set";
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            long daysSince = getDaysSinceWatched(LocalDate.now());

            watchedText = "Sidst set: "
                    + lastWatched.format(formatter)
                    + " (for "
                    + daysSince
                    + " dage siden)";
        }

        return "Titel: " + title
                + "\nInstruktør: " + director
                + "\nÅr: " + yearCreated
                + "\nLængde: " + lengthInMinutes + " minutter"
                + "\nGenre: " + genre
                + "\nFormat: " + colorText
                + "\nStatus: " + watchedText + "\n";
    }

}