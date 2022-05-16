package andima.movie.repositories;

import andima.movie.models.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Modifying
    @Query(value ="create table if not exists movie(" +
            " id bigint," +
            " adult boolean," +
            " backdrop_path varchar(255)," +
            " media_type varchar(255)," +
            " original_language varchar(255)," +
            " original_title varchar(255)," +
            " overview varchar(1000)," +
            " popularity double precision," +
            " poster_path varchar(255)," +
            " release_date varchar(255)," +
            " genre_ids varchar(255)," +
            " title varchar(255)," +
            " video boolean," +
            " vote_average double precision," +
            " vote_count BIGINT)", nativeQuery = true)
    public void createTable();

    @Modifying
    @Query(value =" DROP TABLE IF EXISTS movie", nativeQuery = true)
    public void dropTable();
}
