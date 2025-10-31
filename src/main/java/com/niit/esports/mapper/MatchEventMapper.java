package com.niit.esports.mapper;

import com.niit.esports.entity.MatchEvent;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MatchEventMapper {

    // 基本CRUD操作
    MatchEvent findById(String eventId);
    int insert(MatchEvent matchEvent);
    int update(MatchEvent matchEvent);
    int delete(String eventId);

    // 业务查询方法
    List<MatchEvent> findByMatchId(String matchId);
    List<MatchEvent> findByPlayerId(String playerId);
    List<MatchEvent> findByMatchIdAndType(@Param("matchId") String matchId,
                                          @Param("eventType") String eventType);

    // 统计方法
    @MapKey("eventId")
    List<Map<String, Object>> getEventStatsByMatch(String matchId);
}