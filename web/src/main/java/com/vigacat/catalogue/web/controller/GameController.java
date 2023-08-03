package com.vigacat.catalogue.web.controller;

import com.vigacat.catalogue.persistence.dto.GameDto;
import com.vigacat.catalogue.service.component.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('permission::CAT_QUERY_GAMES')")
    public GameDto getGame(@RequestParam(name = "id") Long id) {
        return gameService.getGameById(id);
    }

}
