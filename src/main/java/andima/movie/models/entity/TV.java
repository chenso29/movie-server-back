package andima.movie.models.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tv")
@Builder

public class TV {
    @Id
    @NotNull
    private Long id;
    @Column
    private Double vote_average;
    @Column(columnDefinition = "varchar(1000)")
    private String overview;
    @Column
    private Long vote_count;
    @Column
    private String first_air_date;
    @Column
    private String backdrop_path;
    @Column
    private String original_name;
    @Column
    private String genre_ids;//
    @Column
    @NotNull
    private String origin_country;//
    @Column
    private String original_language;
    @Column
    @NotNull
    private String name;
    @Column
    private String poster_path;
    @Column
    private Double popularity;
    @Column
    private String media_type;
}
