package com.sos.owo.domain.repository;

import com.sos.owo.domain.MeetingRoom;
import com.sos.owo.domain.RoomStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
@Transactional
public class GameRepository {

    @PersistenceContext
    private final EntityManager em;

//    public void createRoom(int roomID){
//        MeetingRoom findRoom = em.find(MeetingRoom.class, roomID);
//        findRoom.setStatus(RoomStatus.WAIT);
//        em.persist(findRoom);
//        em.flush();
//    }
    public void startRoom(int roomID){
        MeetingRoom findRoom = em.find(MeetingRoom.class, roomID);
        findRoom.setStatus(RoomStatus.START);
        findRoom.setStartDate(LocalDateTime.now());
        em.persist(findRoom);
        em.flush();
    }

    public void endRoom(int roomID){
        MeetingRoom findRoom = em.find(MeetingRoom.class, roomID);
        findRoom.setStatus(RoomStatus.END);
        findRoom.setEndDate(LocalDateTime.now());
        em.persist(findRoom);
        em.flush();
    }
}
