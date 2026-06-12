package catalog.kyrs.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CinemaRequest {

    private Long directorId;

    private Long screenwriterId;

    private String cinemaName;

    private String cinemaDesc;

    private String cinemaType;

    private Integer numberActors;

    private Double likeScore;

}
