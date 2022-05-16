package andima.movie.models.dto.TV;

import andima.movie.models.entity.TV;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TVResponse {
    private List<TVDto> results;
}
