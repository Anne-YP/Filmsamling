import java.time.LocalDate;

public class Movie {
    private String title;
    private String director;
    private int yearCreated;
    private boolean inColor;
    private int lengthInMinutes;
    private String genre;

    private boolean watched;
    private LocalDate lastWatchedDate;

    public Movie(String title, String director, int yearCreated,
                 boolean inColor, int lengthInMinutes, String genre) {
        this.title = title;
        this.director = director;
        this.yearCreated = yearCreated;
        this.inColor = inColor;
        this.lengthInMinutes = lengthInMinutes;
        this.genre = genre;

        this.watched = false;
        this.lastWatchedDate = null;
    }

    //Getters
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

    public boolean isWatched() {
        return watched;
    }

    public LocalDate getLastWatchedDate() {
        return lastWatchedDate;
    }

    //Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public void setInColor(boolean inColor) {
        this.inColor = inColor;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIsWatched() {
        watched = true;
        lastWatchedDate = LocalDate.now();
    }

    @Override
    public String toString() {

        String colorText = inColor ? "Farver" : "Sort-hvid";

        String watchedText;
        if(watched) {
            watchedText = "Set";
        }
        else {
            watchedText = "Ikke set";
        }

        return
                "Titel: " + title +
                        "\nInstruktør: " + director +
                        "\nÅr: " + yearCreated +
                        "\nLængde: " + lengthInMinutes + " minutter" +
                        "\nGenre: " + genre +
                        "\nFormat: " + colorText +
                        "\nStatus: " + watchedText + "\n";

    }
}
