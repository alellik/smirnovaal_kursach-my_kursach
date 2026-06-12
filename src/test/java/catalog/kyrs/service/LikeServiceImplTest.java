package catalog.kyrs.service;

import catalog.kyrs.controller.request.LikeRequest;
import catalog.kyrs.exceptions.LikeNotFoundException;
import catalog.kyrs.model.Cinema;
import catalog.kyrs.model.Like;
import catalog.kyrs.model.User;
import catalog.kyrs.repository.CinemaRepository;
import catalog.kyrs.repository.LikeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LikeServiceImplTest {

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private CinemaService cinemaService;

    @Mock
    private UserService userService;

    @Mock
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private LikeServiceImpl likeService;

    @Test
    void testCreateLike() throws LikeNotFoundException {
        LikeRequest likeRequest = new LikeRequest();
        Cinema cinema = new Cinema();
        User user = new User();
        Like newLike = new Like();

        when(cinemaService.getCinemaById(likeRequest.getCinemaId())).thenReturn(Optional.of(cinema));
        when(userService.getUserById(likeRequest.getUserId())).thenReturn(Optional.of(user));
        when(likeRepository.findLikesByCinemaIdAndUserId(likeRequest.getCinemaId(), likeRequest.getUserId())).thenReturn(null);
        when(likeRepository.save(any(Like.class))).thenReturn(newLike);

        Like createdLike = likeService.createLike(likeRequest);

        assertNotNull(createdLike);
    }

    @Test
    void testGetAllLikes() {
        List<Like> expectedLikes = new ArrayList<>();

        when(likeRepository.findAll()).thenReturn(expectedLikes);

        List<Like> actualLikes = likeService.getAllLikes();

        assertNotNull(actualLikes);
        assertEquals(expectedLikes, actualLikes);
    }

    @Test
    void testGetLikesByCinema() throws LikeNotFoundException {
        long cinemaId = 1L;
        Cinema cinema = new Cinema();
        List<Like> expectedLikes = new ArrayList<>();

        when(cinemaService.getCinemaById(cinemaId)).thenReturn(Optional.of(cinema));
        when(likeRepository.findAllByCinema(cinema)).thenReturn(expectedLikes);

        List<Like> actualLikes = likeService.getLikesByCinema(cinemaId);

        assertNotNull(actualLikes);
        assertEquals(expectedLikes, actualLikes);
    }

    @Test
    void testGetLikeById() {
        long likeId = 1L;
        Like expectedLike = new Like();

        when(likeRepository.findById(likeId)).thenReturn(Optional.of(expectedLike));

        Optional<Like> actualLike = likeService.getLikeById(likeId);

        assertTrue(actualLike.isPresent());
        assertEquals(expectedLike, actualLike.get());
    }

    @Test
    void testDeleteLikeById() {
        long likeId = 1L;

        likeService.deleteLikeById(likeId);

        verify(likeRepository, times(1)).deleteById(likeId);
    }

    @Test
    void testPutLikeById() {
        long likeId = 1L;
        Like updatedLike = new Like();
        Like existingLike = new Like();

        when(likeRepository.findById(likeId)).thenReturn(Optional.of(existingLike));
        when(likeRepository.save(any(Like.class))).thenReturn(updatedLike);

        Optional<Like> actualLike = likeService.putLikeById(likeId, updatedLike);

        assertTrue(actualLike.isPresent());
        assertEquals(updatedLike, actualLike.get());
    }
}
