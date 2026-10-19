import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<>();
    }

    //TODO US1
    public void addMovie (String title, String director, int yearCreated,
                          boolean inColor, int lengthInMinutes, String genre) {}

    // TODO US3
    public ArrayList<Movie> getMovies() {
        return null;
    }

    //TODO
    public int getNumberOfMovies() {
        return 0;
    }

    public ArrayList<Movie> searchMovies(String searchText) {
        ArrayList<Movie> matches = new ArrayList<>();
        String text = searchText.toLowerCase();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(text)) {
                matches.add(movie);
            }
        }
        return matches;
    }

    //TODO US5
    public boolean editMovie(Movie movie, String title, String director, int yearCreated,
                             boolean inColor, int lengthInMinutes, String genre) {
        return false;
    }

    //TODO US6
    public boolean deleteMovie(Movie movie) {
        return false;
    }
}
