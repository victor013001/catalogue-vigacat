package com.vigacat.catalogue.web.exceptions;

import com.vigacat.catalogue.service.exceptions.GameCreateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GameCreateException.class)
    protected ResponseEntity<Map<String, Object>> handleGameCreateException(GameCreateException gameCreateException) {
        return ResponseEntity.status(gameCreateException.getHttpStatus())
                .body(gameCreateExceptionBody(gameCreateException));
    }

    private Map<String, Object> gameCreateExceptionBody(GameCreateException gameCreateException) {
        GameCreateException.Type exceptionType = gameCreateException.getType();
        if (exceptionType.equals(GameCreateException.Type.DUPLICATE_GAME)) {
            return createGameCreateDuplicateGameBody(gameCreateException);
        } else {
            return createGameCreateNonGenresBody(gameCreateException);
        }
    }

    private Map<String, Object> createGameCreateNonGenresBody(GameCreateException gameCreateException) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", gameCreateException.getType());
        body.put("title", gameCreateException.getHttpStatus().name());
        body.put("status", gameCreateException.getHttpStatus().value());
        body.put("genres", gameCreateException.getGameGenres());
        body.put("detail", gameCreateException.getMessage());
        return body;
    }

    private Map<String, Object> createGameCreateDuplicateGameBody(GameCreateException gameCreateException) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", gameCreateException.getType());
        body.put("title", gameCreateException.getHttpStatus().name());
        body.put("status", gameCreateException.getHttpStatus().value());
        body.put("game-title", gameCreateException.getGameTitle());
        body.put("game-release-date", gameCreateException.getGameReleaseDate());
        body.put("detail", gameCreateException.getMessage());
        return body;
    }
}
