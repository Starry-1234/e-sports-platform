package com.niit.esports.mapper;

import com.niit.esports.entity.Player;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface  PlayerMapper {
    List<Player> selectAllPlayers();
    int selectPlayerCount();
    
    // 根据teamId查询选手列表
    @Select("SELECT * FROM player WHERE team_id = #{teamId} ORDER BY FIELD(position, 'Top', 'Jungle', 'Mid', 'ADC', 'Support')")
    List<Player> selectPlayersByTeamId(String teamId);
}