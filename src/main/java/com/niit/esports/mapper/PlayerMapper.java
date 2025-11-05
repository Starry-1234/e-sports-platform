package com.niit.esports.mapper;

import com.niit.esports.entity.Player;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PlayerMapper {
    List<Player> selectAllPlayers();
    int selectPlayerCount();

    List<Player> findAllWithTeam();
    Player findById(int playerId);
    void insert(Player player);
    void update(Player player);
    void delete(int playerId);
    List<Player> findByTeamId(Integer teamId);
    List<Player> findByGameName(String gameName);

}