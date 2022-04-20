package recipes.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomExceptions {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class NotFound extends RuntimeException {
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public static class NoContent extends RuntimeException {
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public static class BadRequest extends RuntimeException {
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public static class ForbiddenRequest extends RuntimeException {
    }
}
