package com.niit.esports.service.impl;

import com.niit.esports.entity.Match;
import com.niit.esports.mapper.MatchMapper;
import com.niit.esports.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchMapper matchMapper;

    @Override
    public List<Match> getAllMatches() {
        return matchMapper.selectAllMatches();
    }

    @Override
    public Match getMatchById(String matchId) {
        return matchMapper.selectMatchById(matchId);
    }

    @Override
    public List<Match> getMatchesWithTeamInfo() {
        return matchMapper.selectMatchesWithTeamInfo();
    }
}