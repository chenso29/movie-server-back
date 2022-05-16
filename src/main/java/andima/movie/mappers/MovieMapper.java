package andima.movie.mappers;

import andima.movie.models.dto.Movie.MovieDto;
import andima.movie.models.entity.Movie;
import org.mapstruct.Mapper;

import java.util.Arrays;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    default Movie toModel(MovieDto movieDto){
        if (movieDto == null){
            return null;
        }
        return Movie.builder()
                .overview(movieDto.getOverview())
                .id(movieDto.getId())
                .release_date(movieDto.getRelease_date())
                .adult(movieDto.getAdult())
                .backdrop_path(movieDto.getBackdrop_path())
                .original_language(movieDto.getOriginal_language())
                .original_title(movieDto.getOriginal_title())
                .poster_path(movieDto.getPoster_path())
                .vote_count(movieDto.getVote_count())
                .video(movieDto.getVideo())
                .vote_average(movieDto.getVote_average())
                .title(movieDto.getTitle())
                .popularity(movieDto.getPopularity())
                .media_type(movieDto.getMedia_type())
                .genre_ids(Arrays.toString(movieDto.getGenre_ids()))
                .build();
    }


}
