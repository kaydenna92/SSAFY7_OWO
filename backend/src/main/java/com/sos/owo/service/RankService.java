package com.sos.owo.service;

import com.sos.owo.domain.repository.RankRepository;
import com.sos.owo.dto.ResponseRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RankService {

    private final RankRepository rankRepository;
    private RedisTemplate<String, String> redisTemplate;

    // 랭킹 점수 반영
    @Transactional
    public void addRank(int member_id, int time){
        rankRepository.addRank(member_id, time);

    }


    public List<ResponseRankingDto> getRankingList(){
        String key = "ranking";
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringStringZSetOperations.reverseRangeWithScores(key, 0, 3);

        List<ResponseRankingDto> rankingList = typedTuples.stream().map(ResponseRankingDto::convertToResponseRankingDto).collect(Collectors.toList());
        return rankingList;
    }

    public int getMyRank(int member_id){
        Long ranking=0L;
        Double ranking1 = redisTemplate.opsForZSet().score("ranking", String.valueOf(member_id));
        Set<String> ranking2 = redisTemplate.opsForZSet().reverseRangeByScore("ranking", ranking1, ranking1, 0, 1);
        for (String s : ranking2) {
            ranking = redisTemplate.opsForZSet().reverseRank("ranking", s);
        }
        return (int)(ranking+1);//index가 0부터 시작되어서 1 더해준다
    }
}
