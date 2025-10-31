package com.niit.esports.mapper;

import com.niit.esports.entity.Player;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PlayerMapper {
    List<Player> selectAllPlayers();
    int selectPlayerCount();
}