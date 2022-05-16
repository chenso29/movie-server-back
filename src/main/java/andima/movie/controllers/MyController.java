package andima.movie.controllers;

import andima.movie.fetcher.Fetcher;
import andima.movie.models.dto.Movie.MovieDto;
import andima.movie.models.dto.TV.TVDto;
import andima.movie.service.interfaces.MovieService;
import andima.movie.service.interfaces.TVService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/")
public class MyController {

    private final Fetcher fetcher;

    private final MovieService movieService;

    private final TVService tvService;

    @Autowired
    public MyController(Fetcher fetcher, MovieService movieService, TVService tvService) {
        this.fetcher = fetcher;
        this.movieService = movieService;
        this.tvService = tvService;
    }

    @GetMapping("movie/")
    public void fetchAllMovies() throws IOException {
        System.out.println("Fetch Movies");
        for (int i = 1; i <= 20; i++) {
            List<MovieDto> movies = fetcher.getAllMovies(i);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("C:\\Users\\slexn\\IdeaProjects\\movie\\src\\main\\json\\movie\\movie" + i + ".json"), movies);
        }
        System.out.println("Movie JSON files created");
    }

    @GetMapping("tv/")
    public void fetchAllTVs() throws IOException {
        System.out.println("Fetch TVs");
        for (int i = 1; i <= 20; i++) {
            List<TVDto> tv = fetcher.getAllTVs(i);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("C:\\Users\\slexn\\IdeaProjects\\movie\\src\\main\\json\\tv\\tv" + i + ".json"), tv);
        }
        System.out.println("TV JSON files created");
    }

    @GetMapping("add")
    public void addMovie() {
        try {
            movieService.saveAllMovies();
            tvService.saveAllTVs();
            System.out.println("Data added to DB");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @GetMapping("delete")
    public void deleteMovie() {
        try {
            movieService.deleteAllMovies();
            tvService.deleteAllTVs();
            System.out.println("Data deleted from table");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @GetMapping("drop")
    public void dropMovieTable() {
        try {
            movieService.dropMovieTable();
            tvService.dropTVTable();
            System.out.println("Tables has been dropped");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


