package com.vigacat.catalogue.service.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class GameCreateException extends RuntimeException {

    private final String gameTitle;
    private final String gameReleaseDate;
    private final String gameGenres;
    private final Type type;
    private final HttpStatus httpStatus;

    public GameCreateException(String gameTitle, String gameReleaseDate, Type type) {
        super(type.getMessage());
        this.gameTitle = gameTitle;
        this.gameReleaseDate = gameReleaseDate;
        this.gameGenres = null;
        this.type = type;
        this.httpStatus = type.getHttpStatus();
    }

    public GameCreateException(String gameGenres, Type type) {
        super(type.getMessage());
        this.gameTitle = null;
        this.gameReleaseDate = null;
        this.gameGenres = gameGenres;
        this.type = type;
        this.httpStatus = type.getHttpStatus();
    }

    @Getter
    @RequiredArgsConstructor
    public enum Type {
        DUPLICATE_GAME("Game already exists in the catalogue", HttpStatus.CONFLICT),
        NON_EXISTENT_GENRES("One or more genres does not exist", HttpStatus.BAD_REQUEST);

        private final String message;
        private final HttpStatus httpStatus;
    }
}
