package catalog.kyrs.service;

import catalog.kyrs.controller.request.LikeRequest;
import catalog.kyrs.exceptions.LikeNotFoundException;
import catalog.kyrs.model.Like;
import java.util.List;
import java.util.Optional;

public interface LikeService {

    Like createLike(LikeRequest like) throws LikeNotFoundException;
    List<Like> getAllLikes();
    List<Like> getLikesByCinema(long id) throws LikeNotFoundException;
    Optional<Like> getLikeById(Long id);
    void deleteLikeById(Long id);
    Optional<Like> putLikeById(Long id, Like updatedLike);
}
