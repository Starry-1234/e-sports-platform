package com.niit.esports.service;

import com.niit.esports.entity.MatchEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MatchEventService {

    // 基本CRUD操作
    MatchEvent getEventById(String eventId);
    void addEvent(MatchEvent matchEvent);
    void updateEvent(MatchEvent matchEvent);
    void deleteEvent(String eventId);

    // 核心查询方法
    List<MatchEvent> getEventsByMatch(String matchId);
    List<MatchEvent> getEventsByPlayer(String playerId);
    List<MatchEvent> getEventsByMatchAndType(String matchId, String eventType);

    // 统计方法
    List<Map<String, Object>> getEventStatsByMatch(String matchId);

    // 获取带详情的事件列表（用于事件列表页面）
    List<MatchEvent> getEventsWithDetails(String matchId);
}