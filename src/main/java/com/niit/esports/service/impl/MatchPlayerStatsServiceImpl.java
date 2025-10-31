package com.niit.esports.service.impl;

import com.niit.esports.entity.MatchPlayerStats;
import com.niit.esports.mapper.MatchPlayerStatsMapper;
import com.niit.esports.service.MatchPlayerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchPlayerStatsServiceImpl implements MatchPlayerStatsService {
    
    @Autowired
    private MatchPlayerStatsMapper matchPlayerStatsMapper;
    
    @Override
    public List<MatchPlayerStats> getStatsByMatchId(String matchId) {
        return matchPlayerStatsMapper.getStatsByMatchId(matchId);
    }
    
    @Override
    public MatchPlayerStats getStatsById(String statsId) {
        return matchPlayerStatsMapper.getStatsById(statsId);
    }
    
    @Override
    public void addStats(MatchPlayerStats stats) {
        matchPlayerStatsMapper.addStats(stats);
    }
    
    @Override
    public void updateStats(MatchPlayerStats stats) {
        matchPlayerStatsMapper.updateStats(stats);
    }
    
    @Override
    public void deleteStats(String statsId) {
        matchPlayerStatsMapper.deleteStats(statsId);
    }
}