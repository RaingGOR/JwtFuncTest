package ru.Raingor.webAnimeSite.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.Raingor.webAnimeSite.utils.exceptions.anime.AnimeNotFoundException;
import ru.Raingor.webAnimeSite.utils.exceptions.user.UserNotCreatedException;
import ru.Raingor.webAnimeSite.utils.exceptions.user.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    // exception handling
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleExecption(UserNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        //return 400
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleExecption(UserNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "User not found",
                System.currentTimeMillis()
        );

        //return 404
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleExecption(AnimeNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "Anime not found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
