package com.niit.esports.service;

import com.niit.esports.entity.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    
    /**
     * 获取所有比赛
     * @return 比赛列表
     */
    List<Match> getAllMatches();
    
    /**
     * 根据ID获取比赛
     * @param matchId 比赛ID
     * @return 比赛信息
     */
    Match getMatchById(String matchId);
}