package com.vigacat.catalogue.web.controller;

import com.vigacat.catalogue.persistence.dto.GameDto;
import com.vigacat.catalogue.persistence.dto.GameToSaveDto;
import com.vigacat.catalogue.service.component.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @PreAuthorize("hasAnyAuthority('permission::CAT_CREATE_GAMES')")
    public GameDto createNewGame(@RequestBody @Valid GameToSaveDto gameToSaveDto) {
        return gameService.createNewGame(gameToSaveDto);
    }

}
