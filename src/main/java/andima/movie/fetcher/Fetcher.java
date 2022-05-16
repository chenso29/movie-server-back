package andima.movie.fetcher;

import andima.movie.models.dto.Movie.MovieDto;
import andima.movie.models.dto.Movie.MovieResponse;
import andima.movie.models.dto.TV.TVDto;
import andima.movie.models.entity.TV;
import andima.movie.models.dto.TV.TVResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class Fetcher {

    private final RestTemplate restTemplate;
    private final String movieURL = "https://api.themoviedb.org/3/trending/movie/week?api_key=0507452b9f99ae93a4c0aa57415dfe69&page=";
    private final String tvURL = "https://api.themoviedb.org/3/trending/tv/week?api_key=0507452b9f99ae93a4c0aa57415dfe69&page=";

    @Autowired
    public Fetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MovieDto> getAllMovies(int page){
        return Objects.requireNonNull(restTemplate.getForObject(movieURL + page, MovieResponse.class)).getResults();
    }

    public List<TVDto> getAllTVs(int page){
        return Objects.requireNonNull(restTemplate.getForObject(tvURL + page, TVResponse.class)).getResults();
    }
}
