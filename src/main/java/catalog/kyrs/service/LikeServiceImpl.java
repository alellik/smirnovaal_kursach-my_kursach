package catalog.kyrs.service;

import catalog.kyrs.controller.request.LikeRequest;
import catalog.kyrs.exceptions.LikeNotFoundException;
import catalog.kyrs.model.Cinema;
import catalog.kyrs.model.Like;
import catalog.kyrs.model.User;
import catalog.kyrs.repository.CinemaRepository;
import catalog.kyrs.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final CinemaService cinemaService;
    private final UserService userService;
    private final CinemaRepository cinemaRepository;

    @Override
    public Like createLike(LikeRequest like) throws LikeNotFoundException {
        Optional<Cinema> cinema = cinemaService.getCinemaById(like.getCinemaId());
        Optional<User> user = userService.getUserById(like.getUserId());

        if (cinema.isPresent() && user.isPresent()) {
            Like existingLike = likeRepository.findLikesByCinemaIdAndUserId(like.getCinemaId(), like.getUserId());
            if (existingLike != null) {
                existingLike.setValueOfLike(like.getValueOfLike());
                existingLike.setDateOfLike(new Date());
                likeRepository.save(existingLike);
                return existingLike;
            } else {
                int valueOfLike = like.getValueOfLike();
                if (valueOfLike >= 1 && valueOfLike <= 10) {
                    Like newLike = likeRepository.save(
                            new Like(
                                    null,
                                    cinema.get(),
                                    user.get(),
                                    valueOfLike,
                                    new Date()
                            )
                    );

                    List<Like> likes = likeRepository.findLikesByCinemaId(like.getCinemaId());
                    double sumLikes = likes.stream().mapToInt(Like::getValueOfLike).sum();
                    int countOfLike = likes.size();
                    double newLikeScore = 0;
                    if (countOfLike > 0) {
                        newLikeScore = sumLikes / countOfLike;
                    }
                    cinema.get().setLikeScore(newLikeScore);
                    cinemaRepository.save(cinema.get());

                    return newLike;
                } else {
                    throw new IllegalArgumentException("Оцените идею кинофильма от 1 до 10.");
                }
            }
        } else {
            throw new LikeNotFoundException(like.getCinemaId());
        }
    }


    @Override
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Override
    public List<Like> getLikesByCinema(long id) throws LikeNotFoundException{
        Optional<Cinema> cinema = cinemaService.getCinemaById(id);
        if (cinema.isPresent()) {
            return likeRepository.findAllByCinema(cinema.get());
        } else {
            throw new LikeNotFoundException(id);
        }
    }

    @Override
    public Optional<Like> getLikeById(Long id) {
        return likeRepository.findById(id);
    }

    @Override
    public void deleteLikeById (Long id) {
        likeRepository.deleteById(id);
    }

    @Override
    public Optional<Like> putLikeById(Long id, Like updatedLike) {
        Optional<Like> existingLike = likeRepository.findById(id);
        if (existingLike.isPresent()) {
            Like likeToUpdate = existingLike.get();
            if (updatedLike.getValueOfLike() != null) {
                likeToUpdate.setValueOfLike(updatedLike.getValueOfLike());
            }
            likeRepository.save(likeToUpdate);
        }
        return existingLike;
    }
}
