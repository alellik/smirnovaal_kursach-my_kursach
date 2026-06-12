package catalog.kyrs.service;

import catalog.kyrs.controller.request.LikeRequest;
import catalog.kyrs.model.Like;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LikeServiceTest {

    @Mock
    private LikeService likeService;

    @Test
    void testCreateLike() {
        LikeRequest likeRequest = new LikeRequest();
        Like createdLike = new Like();

        when(likeService.createLike(likeRequest)).thenReturn(createdLike);

        Like actualLike = likeService.createLike(likeRequest);

        assertNotNull(actualLike);
        assertEquals(createdLike, actualLike);
    }

    @Test
    void testGetAllLikes() {
        List<Like> expectedLikes = new ArrayList<>();

        when(likeService.getAllLikes()).thenReturn(expectedLikes);

        List<Like> actualLikes = likeService.getAllLikes();

        assertNotNull(actualLikes);
        assertEquals(expectedLikes, actualLikes);
    }

    @Test
    void testGetLikesByCinema() {
        Long cinemaId = 1L;
        List<Like> expectedLikes = new ArrayList<>();

        when(likeService.getLikesByCinema(cinemaId)).thenReturn(expectedLikes);

        List<Like> actualLikes = likeService.getLikesByCinema(cinemaId);

        assertNotNull(actualLikes);
        assertEquals(expectedLikes, actualLikes);
    }

    @Test
    void testGetLikeById() {
        Long id = 1L;
        Like like = new Like();
        Optional<Like> expectedLike = Optional.of(like);

        when(likeService.getLikeById(id)).thenReturn(expectedLike);

        Optional<Like> actualLike = likeService.getLikeById(id);

        assertTrue(actualLike.isPresent());
        assertEquals(expectedLike.get(), actualLike.get());
    }

    @Test
    void testDeleteLikeById() {
        Long id = 1L;

        likeService.deleteLikeById(id);

        verify(likeService, times(1)).deleteLikeById(id);
    }

    @Test
    void testPutLikeById() {
        Long id = 1L;
        Like updatedLike = new Like();
        Optional<Like> expectedLike = Optional.of(updatedLike);

        when(likeService.putLikeById(id, updatedLike)).thenReturn(expectedLike);

        Optional<Like> actualLike = likeService.putLikeById(id, updatedLike);

        assertTrue(actualLike.isPresent());
        assertEquals(expectedLike.get(), actualLike.get());
    }
}
