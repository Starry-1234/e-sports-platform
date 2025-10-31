package com.niit.esports.service;

import com.niit.esports.entity.Match;
import java.util.List;

public interface MatchService {
    List<Match> getAllMatches();
    Match getMatchById(String matchId);
    List<Match> getMatchesWithTeamInfo();
}