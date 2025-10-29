package com.niit.esports.service;

import com.niit.esports.entity.MatchEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MatchEventService {

    // 基本CRUD
    List<MatchEvent> getAllEvents();
    MatchEvent getEventById(String eventId);
    void addEvent(MatchEvent matchEvent);
    void updateEvent(MatchEvent matchEvent);
    void deleteEvent(String eventId);

    // 业务方法
    List<MatchEvent> getEventsByMatch(String matchId);
    List<MatchEvent> getEventsByPlayer(String playerId);
    List<MatchEvent> getEventsByMatchAndType(String matchId, String eventType);
    List<MatchEvent> getKillEvents(String matchId);
    List<MatchEvent> getImportantEvents(String matchId);

    // 统计方法
    Map<String, Object> getMatchEventStats(String matchId);
    Map<String, Object> getPlayerEventStats(String matchId, String playerId);

    // 实时数据
    List<MatchEvent> getRecentEvents(String matchId, String lastEventId);

    // 时间线
    List<MatchEvent> getMatchTimeline(String matchId);

    // 分页查询
    List<MatchEvent> getEventsByMatchWithPage(String matchId, int page, int size);
    int getEventCountByMatch(String matchId);

    // 选手比赛事件
    List<MatchEvent> getEventsByMatchAndPlayer(String matchId, String playerId);

    // 批量操作
    void batchInsertEvents(List<MatchEvent> events);
}