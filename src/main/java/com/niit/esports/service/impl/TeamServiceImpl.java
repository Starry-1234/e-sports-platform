package com.niit.esports.service.impl;

import com.niit.esports.entity.Team;
import com.niit.esports.mapper.TeamMapper;
import com.niit.esports.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Team> getAllTeams() {
        return teamMapper.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Team> getTeamsByName(String teamName) {
        return teamMapper.findByName(teamName);
    }
    @Override
    @Transactional(readOnly = true)
    public Team getTeamById(int teamId) {
        return teamMapper.findById(teamId);
    }

    @Override
    public void addTeam(Team team) {
        teamMapper.insert(team);
    }

    @Override
    public void updateTeam(Team team) {
        teamMapper.update(team);
    }

    @Override
    public void deleteTeam(int teamId) {
        teamMapper.delete(teamId);
    }
}
