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
            " adult boolean default false ," +
            " backdrop_path varchar(255) default \"\" ," +
            " media_type varchar(255) default \"\"," +
            " original_language varchar(255) default en," +
            " original_title varchar(255)," +
            " overview varchar(1000) default \"\"," +
            " popularity double precision default 0.0," +
            " poster_path varchar(255) default \"\"," +
            " release_date varchar(255) default \"\"," +
            " genre_ids varchar(255) default \"\" ," +
            " title varchar(255)," +
            " video boolean default false," +
            " vote_average double precision default 0.0," +
            " vote_count BIGINT default 0)", nativeQuery = true)
    public void createTable();

    @Modifying
    @Query(value =" DROP TABLE IF EXISTS movie", nativeQuery = true)
    public void dropTable();
}
