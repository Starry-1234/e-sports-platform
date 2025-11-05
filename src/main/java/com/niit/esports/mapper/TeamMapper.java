package com.niit.esports.mapper;

import com.niit.esports.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TeamMapper {
    List<Team> selectAllTeams();
    int selectTeamCount();

    List<Team> findAll();
    List<Team> findByName(String teamName);
    Team findById(int teamId);
    void insert(Team team);
    void update(Team team);
    void delete(int teamId);
}