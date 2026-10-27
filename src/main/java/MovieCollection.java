import java.time.LocalDate;
import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<>();
    }

    //TODO US1
    public void addMovie (String title, String director, int yearCreated,
                          boolean inColor, int lengthInMinutes, String genre) {
        Movie movie = new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre);
        movies.add(movie);
    }

    // TODO US3
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    //TODO
//    public int getNumberOfMovies() {
//        return 0;
//    }

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

        if(!movies.contains(movie)) {
            return false;
        }

        movie.setTitle(title);
        movie.setDirector(director);
        movie.setYearCreated(yearCreated);
        movie.setInColor(inColor);
        movie.setLengthInMinutes(lengthInMinutes);
        movie.setGenre(genre);

        return true;
    }

    //TODO US6
    public boolean deleteMovie(Movie movie) {
        return movies.remove(movie);
    }

    public ArrayList<Movie> getMoviesNotWatchedSince(LocalDate date) {
        ArrayList<Movie> result = new ArrayList<>();

        for(Movie movie : movies) {
            if(!movie.hasBeenWatched() || movie.getLastWatched().isBefore(date)) {

                result.add(movie);
            }
        }

        return result;
    }



//   Movie movie1 = new Movie("Tourist Trap", "David Schmoeller",
//            1979, true, 90, "Horror/Thriller");
//    Movie movie2 = new Movie("Tammy and the T-Rex", "Stewart Raffill",
//            1994, true, 82, "Horror/Comedy");
//    Movie movie3 = new Movie("Dogs", "Burt Brinckerhoff",
//            1977, true, 90, "Horror");
//    Movie movie4 = new Movie("Five Easy Pieces", "Bob Rafelson",
//            1970, true, 98, "Thriller/Drama");
//    Movie movie5 = new Movie("Slugs", "Juan Piquer Simón",
//            1988, true, 92, "Horror");
//    Movie movie6 = new Movie("Disgusting Spaceworms Eat Everyone!!", "Geaorge Keller",
//            1989, true, 72, "Sci-Fi/Horror/Comedy");

}
