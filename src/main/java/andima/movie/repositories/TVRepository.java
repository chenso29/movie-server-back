package andima.movie.repositories;

import andima.movie.models.entity.TV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TVRepository extends JpaRepository<TV, Long> {
    @Modifying
    @Query(value ="create table if not exists tv(" +
            " id bigint," +
            " backdrop_path varchar(255)," +
            " media_type varchar(255)," +
            " original_language varchar(255)," +
            " original_name varchar(255)," +
            " overview varchar(1000)," +
            " popularity double precision," +
            " poster_path varchar(255)," +
            " first_air_date varchar(255)," +
            " genre_ids varchar(255)," +
            " origin_country varchar(255)," +
            " name varchar(255)," +
            " vote_average double precision," +
            " vote_count BIGINT)", nativeQuery = true)
    public void createTable();

    @Modifying
    @Query(value =" DROP TABLE IF EXISTS tv", nativeQuery = true)
    public void dropTable();
}
