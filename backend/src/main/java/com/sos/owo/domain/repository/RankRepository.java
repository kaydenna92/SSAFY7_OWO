package com.sos.owo.domain.repository;

import com.sos.owo.domain.Member;
import com.sos.owo.domain.Rank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RankRepository {

    @PersistenceContext
    private final EntityManager em;
    private RedisTemplate<String, String> redisTemplate;


    public void addRank(int member_id, int time){
        redisTemplate.delete("ranking");
        redisTemplate.opsForZSet().add("ranking", String.valueOf(member_id), time);
    }


}
