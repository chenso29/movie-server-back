package andima.movie.models.dto.Movie;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {
    private String overview;
    private Long id;
    private String release_date;
    private Boolean adult;
    private String backdrop_path;
    private Long[] genre_ids;
    private String original_language;
    private String original_title;
    private String poster_path;
    private Long vote_count;
    private Boolean video;
    private Double vote_average;
    private String title;
    private Double popularity;
    private String media_type;
}
