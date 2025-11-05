package com.niit.esports.service.impl;

import com.niit.esports.entity.Player;
import com.niit.esports.mapper.PlayerMapper;
import com.niit.esports.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl  implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Player> getAllPlayers() {
        return playerMapper.findAllWithTeam();
    }

    @Override
    @Transactional(readOnly = true)
    public Player getPlayerById(int playerId) {
        return playerMapper.findById(playerId);
    }

    @Override
    public void addPlayer(Player player) {
        playerMapper.insert(player);
    }

    @Override
    public void updatePlayer(Player player) {
        playerMapper.update(player);
    }

    @Override
    public void deletePlayer(int playerId) {
        playerMapper.delete(playerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> getPlayersByTeamId(int teamId) {
        return playerMapper.findByTeamId(teamId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> getPlayersByGameName(String gameName) {
        return playerMapper.findByGameName(gameName);
    }
}
