package catalog.kyrs.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LikeNotFoundException extends Exception {
    private final Long id;
}
