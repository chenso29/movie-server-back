package andima.movie.mappers;

import andima.movie.models.dto.TV.TVDto;
import andima.movie.models.entity.TV;
import org.mapstruct.Mapper;

import java.util.Arrays;


@Mapper(componentModel = "spring")
public interface TVMapper {
    default TV toModel(TVDto tvDto) {
        if (tvDto == null) {
            return null;
        }

        return TV.builder()
                .id(tvDto.getId())
                .vote_average(tvDto.getVote_average())
                .overview(tvDto.getOverview())
                .vote_count(tvDto.getVote_count())
                .first_air_date(tvDto.getFirst_air_date())
                .backdrop_path(tvDto.getBackdrop_path())
                .original_name(tvDto.getOriginal_name())
                .genre_ids(Arrays.toString(tvDto.getGenre_ids()))
                .origin_country(Arrays.toString(tvDto.getOrigin_country()))
                .original_language(tvDto.getOriginal_language())
                .name(tvDto.getName())
                .poster_path(tvDto.getPoster_path())
                .popularity(tvDto.getPopularity())
                .media_type(tvDto.getMedia_type())
                .build();
    }
}
