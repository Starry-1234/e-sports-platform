package com.niit.esports.service.impl;

import com.niit.esports.entity.Player;
import com.niit.esports.mapper.PlayerMapper;
import com.niit.esports.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    
    @Autowired
    private PlayerMapper playerMapper;
    
    @Override
    public List<Player> getAllPlayers() {
        return playerMapper.selectAllPlayers();
    }
    
    @Override
    public List<Player> getPlayersByTeamId(String teamId) {
        return playerMapper.selectPlayersByTeamId(teamId);
    }
    
    @Override
    public int getPlayerCount() {
        return playerMapper.selectPlayerCount();
    }
}