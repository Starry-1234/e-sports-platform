package com.niit.esports.mapper;

import com.niit.esports.entity.Match;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchMapper {
    
    /**
     * 查询所有比赛
     * @return 比赛列表
     */
    List<Match> findAll();
    
    /**
     * 根据ID查询比赛
     * @param matchId 比赛ID
     * @return 比赛信息
     */
    Match findById(String matchId);
}