package andima.movie.service.interfaces;

import andima.movie.models.entity.Movie;
import andima.movie.models.entity.TV;

import java.util.List;

public interface TVService {
    public void saveAllTVs();
    public void deleteAllTVs();
    public void dropTVTable();

    public TV getTV(Long id);
    public void saveTV(TV tv);
    public void updateTV(TV tv);
    public  void deleteTV(Long id);
    public void voteTV(Long id, Double vote );
    public List<TV> searchTV(String year, String media_type, String title, String lang);
    public List<TV> trendyTV();
}
