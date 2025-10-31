package com.niit.esports.mapper;

import com.niit.esports.entity.Match;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchMapper {
    // ... 其他已有方法

    List<Match> selectAllMatches();

    Match selectMatchById(String matchId);

    List<Match> selectMatchesWithTeamInfo();
}