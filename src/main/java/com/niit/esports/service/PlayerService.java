package com.niit.esports.service;

import com.niit.esports.entity.Player;
import java.util.List;

public interface PlayerService {
    /**
     * 获取所有选手
     */
    List<Player> getAllPlayers();
    
    /**
     * 根据队伍ID获取选手列表
     */
    List<Player> getPlayersByTeamId(String teamId);
    
    /**
     * 获取选手总数
     */
    int getPlayerCount();
}