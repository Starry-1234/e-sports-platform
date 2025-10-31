package com.niit.esports.mapper;

import com.niit.esports.entity.MatchPlayerStats;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchPlayerStatsMapper {
    List<MatchPlayerStats> getStatsByMatchId(@Param("matchId") String matchId);
    MatchPlayerStats getStatsById(@Param("statsId") String statsId);
    void addStats(MatchPlayerStats stats);
    void updateStats(MatchPlayerStats stats);
    void deleteStats(@Param("statsId") String statsId);
}