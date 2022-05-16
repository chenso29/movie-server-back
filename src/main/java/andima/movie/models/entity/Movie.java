package andima.movie.models.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Builder

public class Movie {
    @Column(columnDefinition = "varchar(1000)")
    private String overview;
    @Id
    private Long id;
    @Column
    private String release_date;
    @Column
    private Boolean adult;
    @Column
    private String backdrop_path;
    @Column
    private String genre_ids;//
    @Column
    private String original_language;
    @Column
    private String original_title;
    @Column
    private String poster_path;
    @Column
    private Long vote_count;
    @Column
    private Boolean video;
    @Column
    private Double vote_average;
    @Column
    private String title;
    @Column
    private Double popularity;
    @Column
    private String media_type;
}
