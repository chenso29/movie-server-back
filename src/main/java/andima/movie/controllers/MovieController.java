package andima.movie.controllers;

import andima.movie.models.Vote;
import andima.movie.models.entity.Movie;
import andima.movie.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("movie/")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/id/{id}")
    public Movie getMovie(@PathVariable String id) {
        Movie movie = movieService.getMovie(Long.valueOf(id));
        System.out.println(movie);
        return movie;
    }

    @PostMapping("id")
    public void saveMovie(@Valid @RequestBody Movie movie) {
        movieService.saveMovie(movie);
        System.out.println("Movie has been saved");
    }

    @PatchMapping("id")
    public void updateMovie(@Valid @RequestBody Movie movie) {
        movieService.updateMovie(movie);
        System.out.println("Movie with id = " + movie.getId() + " has been updated");
    }

    @DeleteMapping("id/{id}")
    public void deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(Long.valueOf(id));
        System.out.println("Movie with id = " + id + " was deleted");
    }

    @PostMapping("vote")
    public void voteMovie(@Valid @RequestBody Vote vote) {
        movieService.voteMovie(vote.getId(), vote.getVote());
        System.out.println("Movie with id = " + vote.getId() + " was voted");
    }

    @GetMapping("search")
    public List<Movie> searchMovie(@RequestParam(required = false, name = "year") String year,
                                   @RequestParam(required = false, name = "media_type") String media_type,
                                   @RequestParam(required = false, name = "title") String title,
                                   @RequestParam(required = false, name = "lang") String lang) {
        List<Movie> list = movieService.searchMovie(year, media_type, title, lang);
        System.out.println("Movie list filtered by year=" + year
                + " media_type=" + media_type
                + " title=" + title
                + " lang=" + lang);
        return list;
    }

    @GetMapping("trendy")
    public List<Movie> trendyMovie() {
        return movieService.trendyMovie();
    }
}
