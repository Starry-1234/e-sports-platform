package com.niit.esports.service.impl;

import com.niit.esports.mapper.MatchMapper;
import com.niit.esports.entity.Match;
import com.niit.esports.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {
    
    private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);
    
    @Autowired
    private MatchMapper matchMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<Match> getAllMatches() {
        logger.info("查询所有比赛");
        return matchMapper.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Match getMatchById(String matchId) {
        logger.info("根据ID查询比赛: {}", matchId);
        return matchMapper.findById(matchId);
    }
}