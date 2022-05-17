package andima.movie.service.interfaces;

import andima.movie.models.entity.Movie;

import java.util.List;

public interface MovieService {
    public void saveAllMovies();
    public void deleteAllMovies();
    public void dropMovieTable();

    public Movie getMovie(Long id);
    public void saveMovie(Movie movie);
    public void updateMovie(Movie movie);
    public  void deleteMovie(Long id);
    public void voteMovie(Long id, Double vote );
    public List<Movie> searchMovie(String year, String media_type, String title, String lang);
    public List<Movie> trendyMovie();

}
