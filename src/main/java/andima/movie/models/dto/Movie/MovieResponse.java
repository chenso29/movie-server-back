package andima.movie.models.dto.Movie;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    List<MovieDto> results;
}
