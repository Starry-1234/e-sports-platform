package com.niit.esports.service;

import com.niit.esports.entity.MatchPlayerStats;

import java.util.List;

public interface MatchPlayerStatsService {
    List<MatchPlayerStats> getStatsByMatchId(String matchId);
    MatchPlayerStats getStatsById(String statsId);
    void addStats(MatchPlayerStats stats);
    void updateStats(MatchPlayerStats stats);
    void deleteStats(String statsId);
}