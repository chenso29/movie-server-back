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
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Movie getMovie(Long id) {
        return movieRepository.findById(id).isPresent()
                ? movieRepository.findById(id).get()
                : null;
    }

    @Override
    public void saveMovie(Movie movie) {
        if (movie.getAdult() == null) {
            movie.setAdult(false);
        }
        if (movie.getBackdrop_path() == null) {
            movie.setBackdrop_path("");
        }
        if (movie.getGenre_ids() == null) {
            movie.setGenre_ids("");
        }
        if (movie.getVote_count() == null) {
            movie.setVote_count(0L);
        }
        if (movie.getOriginal_language() == null) {
            movie.setOriginal_language("en");
        }
        if (movie.getOriginal_title() == null) {
            movie.setOriginal_title(movie.getTitle());
        }
        if (movie.getVideo() == null) {
            movie.setVideo(false);
        }
        if (movie.getVote_average() == null) {
            movie.setVote_average(0.0);
        }
        if (movie.getPoster_path() == null) {
            movie.setPoster_path("");
        }
        if (movie.getOverview() == null) {
            movie.setOverview("");
        }
        if (movie.getRelease_date() == null) {
            movie.setRelease_date("");
        }
        if (movie.getMedia_type() == null) {
            movie.setMedia_type("");
        }
        if (movie.getPopularity() == null) {
            movie.setPopularity(0.0);
        }
        movieRepository.save(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        Movie movieToUpdate = movieRepository.findById(movie.getId()).get();
        if (movieToUpdate != null) {
            if (movie.getAdult() != null) {
                movieToUpdate.setAdult(movie.getAdult());
            }
            if (movie.getBackdrop_path() != null) {
                movieToUpdate.setBackdrop_path(movie.getBackdrop_path());
            }
            if (movie.getGenre_ids() != null) {
                movieToUpdate.setGenre_ids(movie.getGenre_ids());
            }
            if (movie.getVote_count() != null) {
                movieToUpdate.setVote_count(movie.getVote_count());
            }
            if (movie.getOriginal_language() != null) {
                movieToUpdate.setOriginal_language(movie.getOriginal_language());
            }
            if (movie.getOriginal_title() != null) {
                movieToUpdate.setOriginal_title(movie.getOriginal_title());
            }
            if (movie.getVideo() != null) {
                movieToUpdate.setVideo(movie.getVideo());
            }
            if (movie.getVote_average() != null) {
                movieToUpdate.setVote_average(movie.getVote_average());
            }
            if (movie.getPoster_path() != null) {
                movieToUpdate.setPoster_path(movie.getPoster_path());
            }
            if (movie.getOverview() != null) {
                movieToUpdate.setOverview(movie.getOverview());
            }
            if (movie.getRelease_date() != null) {
                movieToUpdate.setRelease_date(movie.getRelease_date());
            }
            if (movie.getMedia_type() != null) {
                movieToUpdate.setMedia_type(movie.getMedia_type());
            }
            if (movie.getPopularity() != null) {
                movieToUpdate.setPopularity(movie.getPopularity());
            }
            if (movie.getTitle() != null) {
                movieToUpdate.setTitle(movie.getTitle());
            }
        }
        movieRepository.save(movieToUpdate);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void voteMovie(Long id, Double vote) {
        Movie movie = movieRepository.findById(id).isPresent()
                ? movieRepository.findById(id).get()
                : null;
        if (movie != null) {
            double newVote = movie.getVote_average() * movie.getVote_count() + vote;
            movie.setVote_count(movie.getVote_count() + 1);
            newVote = newVote / movie.getVote_count();
            DecimalFormat df = new DecimalFormat("#.#");
            movie.setVote_average(Double.parseDouble(df.format(newVote)));
            movieRepository.save(movie);
        } else {
            System.out.println("Movie with id " + id + " is not exist");
        }
    }

    @Override
    public List<Movie> searchMovie(String year, String media_type, String title, String lang) {
        return movieRepository.findAll()
                .stream()
                .filter((e) -> {
                    boolean a = true;
                    if (year != null && !Objects.equals(e.getRelease_date().length(), 0)) {
                        a = Integer.parseInt(year) <= Integer.parseInt(e.getRelease_date().substring(0, 4))
                                && (Integer.parseInt(year) + 9) >= Integer.parseInt(e.getRelease_date().substring(0, 4));
                    }
                    return a;
                }).filter(e -> {
                    boolean a = true;
                    if (media_type != null && !Objects.equals(e.getMedia_type(), "")) {
                        a = Objects.equals(e.getMedia_type(), media_type);
                    }
                    return a;
                })
                .filter(e -> {
                    boolean a = true;
                    if (lang != null && !Objects.equals(e.getOriginal_language(), "")) {
                        a = Objects.equals(e.getOriginal_language(), lang);
                    }
                    return a;
                }).filter(e -> {
                    boolean a = true;
                    if (title!=null && !Objects.equals(e.getTitle(), "")){
                        a =  e.getTitle().toLowerCase().contains(title.toLowerCase());
                    }
                    return a;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<Movie> trendyMovie() {
        return movieRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Movie::getPopularity).reversed())
                .limit(20)
                .collect(Collectors.toList());
    }


}
