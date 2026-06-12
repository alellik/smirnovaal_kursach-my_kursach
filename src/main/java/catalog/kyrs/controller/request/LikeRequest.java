package catalog.kyrs.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeRequest {
    private Long cinemaId;
    private Long userId;
    private Integer valueOfLike;
}
