package com.sos.owo.service;

import com.sos.owo.domain.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    @Transactional
    public void startrRoom(int roomID){
        gameRepository.startRoom(roomID);
    }

}
