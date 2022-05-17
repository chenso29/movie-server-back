package andima.movie.models;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @NotNull
    private Long id;
    @NotNull
    @Min(1L)
    @Max(10L)
    private Double vote;
}
