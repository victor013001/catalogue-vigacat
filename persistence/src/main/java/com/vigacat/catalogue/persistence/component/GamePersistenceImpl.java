package com.vigacat.catalogue.persistence.component;

import com.vigacat.catalogue.dao.repository.GameRepository;
import com.vigacat.catalogue.persistence.dto.GameDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GamePersistenceImpl implements GamePersistence{

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    @Override
    public GameDto getGameById(Long id) {
        return gameRepository.findById(id)
                .map(game -> modelMapper.map(game, GameDto.class))
                .orElseThrow();
    }
}
