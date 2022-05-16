package andima.movie.service.impl;

import andima.movie.mappers.MovieMapper;
import andima.movie.models.dto.Movie.MovieDto;
import andima.movie.models.entity.Movie;
import andima.movie.repositories.MovieRepository;
import andima.movie.service.interfaces.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<Movie> jsonToMovie() {
        ObjectMapper mapper = new ObjectMapper();
        List<MovieDto> movieList;
        List<Movie> result = new ArrayList<>();
        try {
            for (int i = 1; i <= 20; i++) {
                movieList = Arrays.asList((mapper.readValue(new File("C:\\Users\\slexn\\IdeaProjects\\movie\\src\\main\\json\\movie\\movie" + i + ".json"), MovieDto[].class)));
                for (MovieDto movieDto : movieList) {
                    result.add(movieMapper.toModel(movieDto));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void saveAllMovies() {
        movieRepository.createTable();
        movieRepository.saveAllAndFlush(jsonToMovie());
    }

    @Override
    public void deleteAllMovies() {
        movieRepository.deleteAll();
    }

    @Override
    public void dropMovieTable() {
        movieRepository.dropTable();
    }
}
