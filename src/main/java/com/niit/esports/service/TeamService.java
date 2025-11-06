package com.niit.esports.service;

import com.niit.esports.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    List<Team> getTeamsByName(String teamName);
    Team getTeamById(int teamId);
    void addTeam(Team team);
    void updateTeam(Team team);
    void deleteTeam(int teamId);
}
