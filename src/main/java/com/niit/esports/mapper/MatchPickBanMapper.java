package com.niit.esports.mapper;

import com.niit.esports.entity.MatchPickBan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchPickBanMapper {
    
    // 基本CRUD操作
    List<MatchPickBan> findAll();
    MatchPickBan findById(String pickBanId);
    int insert(MatchPickBan matchPickBan);
    int update(MatchPickBan matchPickBan);
    int delete(String pickBanId);
    
    // 业务查询方法
    List<MatchPickBan> findByMatchId(String matchId);
    List<MatchPickBan> findByTeamId(String teamId);
    List<MatchPickBan> findByMatchIdAndTeamId(@Param("matchId") String matchId, @Param("teamId") String teamId);
    List<MatchPickBan> findByMatchIdAndAction(@Param("matchId") String matchId, @Param("action") String action);
    
    // 分页查询
    List<MatchPickBan> findByMatchIdWithPage(@Param("matchId") String matchId, @Param("offset") int offset, @Param("limit") int limit);
    
    // 统计方法
    int countByMatchId(String matchId);
    int countByMatchIdAndAction(@Param("matchId") String matchId, @Param("action") String action);
}