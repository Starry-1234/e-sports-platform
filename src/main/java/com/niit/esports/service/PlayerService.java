package com.niit.esports.service;

import com.niit.esports.entity.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    Player getPlayerById(int playerId);
    void addPlayer(Player player);
    void updatePlayer(Player player);
    void deletePlayer(int playerId);
    List<Player> getPlayersByTeamId(int teamId);
    List<Player> getPlayersByGameName(String gameName);

}
