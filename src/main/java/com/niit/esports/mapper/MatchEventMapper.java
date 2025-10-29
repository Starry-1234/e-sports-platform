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
    List<MatchEvent> findAll();
    MatchEvent findById(String eventId);
    int insert(MatchEvent matchEvent);
    int update(MatchEvent matchEvent);
    int delete(String eventId);

    // 业务查询方法
    List<MatchEvent> findByMatchId(String matchId);
    List<MatchEvent> findByPlayerId(String playerId);
    List<MatchEvent> findByMatchIdAndType(@Param("matchId") String matchId,
                                          @Param("eventType") String eventType);

    // 特定事件类型查询
    List<MatchEvent> findKillEvents(String matchId);
    List<MatchEvent> findImportantEvents(String matchId);

    // 统计方法
    @MapKey("eventType")
    List<Map<String, Object>> getEventStatsByMatch(String matchId);
    @MapKey("eventType")
    List<Map<String, Object>> getPlayerEventStats(@Param("matchId") String matchId,
                                                  @Param("playerId") String playerId);

    // 实时数据查询
    List<MatchEvent> findRecentEvents(@Param("matchId") String matchId,
                                      @Param("lastEventId") String lastEventId);

    // 时间线查询
    List<MatchEvent> getMatchTimeline(String matchId);

    // 分页查询
    List<MatchEvent> findByMatchIdWithPage(@Param("matchId") String matchId,
                                           @Param("offset") int offset,
                                           @Param("limit") int limit);

    // 获取比赛事件数量
    int countByMatchId(String matchId);

    // 获取选手在某场比赛中的事件
    List<MatchEvent> findByMatchAndPlayer(@Param("matchId") String matchId,
                                          @Param("playerId") String playerId);
}