package catalog.kyrs.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import catalog.kyrs.service.UserService;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

public class RegistrationControllerTest {

    @Mock
    private UserService userService;

    private RegistrationController registrationController;

    @Test
    public void testRegistration() {
        String username = "testUser";
        String password = "testPassword";

        ResponseEntity<Void> response = registrationController.registration(username, password);

        verify(userService).registration(username, password);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}
