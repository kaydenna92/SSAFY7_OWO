package com.sos.owo.service;

import com.sos.owo.domain.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
//
//    public void createRoom(int roomId){
//        gameRepository.createRoom(roomId);
//    }
    @Transactional
    public void startRoom(int roomID){
        gameRepository.startRoom(roomID);
    }

    @Transactional
    public void endRoom(int roomID){
        gameRepository.startRoom(roomID);
    }


}
