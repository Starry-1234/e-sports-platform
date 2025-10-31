package com.niit.esports.service;

import com.niit.esports.entity.MatchPickBan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MatchPickBanService {
    
    // 基本CRUD
    List<MatchPickBan> getAllPickBans();
    MatchPickBan getPickBanById(String pickBanId);
    void addPickBan(MatchPickBan matchPickBan);
    void updatePickBan(MatchPickBan matchPickBan);
    void deletePickBan(String pickBanId);
    
    // 业务方法
    List<MatchPickBan> getPickBansByMatch(String matchId);
    List<MatchPickBan> getPickBansByTeam(String teamId);
    List<MatchPickBan> getPickBansByMatchAndTeam(String matchId, String teamId);
    List<MatchPickBan> getPicksByMatch(String matchId);
    List<MatchPickBan> getBansByMatch(String matchId);
    
    // 统计方法
    Map<String, Object> getPickBanStats(String matchId);
    Map<String, Object> getTeamPickBanStats(String matchId, String teamId);
    
    // 分页查询
    List<MatchPickBan> getPickBansByMatchWithPage(String matchId, int page, int size);
    int getPickBanCountByMatch(String matchId);
}