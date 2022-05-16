package andima.movie.models.dto.TV;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TVDto {
    private Long id;
    private Double vote_average;
    private String overview;
    private Long vote_count;
    private String first_air_date;
    private String backdrop_path;
    private String original_name;
    private Long[] genre_ids;
    private String[] origin_country;
    private String original_language;
    private String name;
    private String poster_path;
    private Double popularity;
    private String media_type;
}
