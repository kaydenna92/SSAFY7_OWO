package com.sos.owo.domain.repository;

import com.sos.owo.domain.MeetingRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
@Transactional
public class GameRepository {

    @PersistenceContext
    private final EntityManager em;

    public void startRoom(int roomID){
        MeetingRoom findRoom = em.find(MeetingRoom.class, roomID);
//        findRoom.setStatus();
    }
}
