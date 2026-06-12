package catalog.kyrs.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CinemaNotFoundException extends Exception {
    private final Long id;
}
