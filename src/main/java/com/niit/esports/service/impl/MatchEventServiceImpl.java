package com.niit.esports.service.impl;

import com.niit.esports.mapper.MatchEventMapper;
import com.niit.esports.entity.MatchEvent;
import com.niit.esports.service.MatchEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MatchEventServiceImpl implements MatchEventService {

    private static final Logger logger = LoggerFactory.getLogger(MatchEventServiceImpl.class);

    @Autowired
    private MatchEventMapper matchEventMapper;

    @Override
    @Transactional(readOnly = true)
    public MatchEvent getEventById(String eventId) {
        logger.debug("根据ID查询事件: {}", eventId);
        return matchEventMapper.findById(eventId);
    }

    @Override
    public void addEvent(MatchEvent matchEvent) {
        logger.info("添加比赛事件: {} - {}", matchEvent.getEventType(), matchEvent.getEventId());

        // 验证必要字段
        if (matchEvent.getEventId() == null || matchEvent.getEventId().trim().isEmpty()) {
            throw new IllegalArgumentException("事件ID不能为空");
        }
        if (matchEvent.getMatchId() == null || matchEvent.getMatchId().trim().isEmpty()) {
            throw new IllegalArgumentException("比赛ID不能为空");
        }

        matchEventMapper.insert(matchEvent);
        logger.info("比赛事件添加成功: {}", matchEvent.getEventId());
    }

    @Override
    public void updateEvent(MatchEvent matchEvent) {
        logger.info("更新比赛事件: {}", matchEvent.getEventId());

        MatchEvent existingEvent = matchEventMapper.findById(matchEvent.getEventId());
        if (existingEvent == null) {
            throw new RuntimeException("比赛事件不存在: " + matchEvent.getEventId());
        }

        matchEventMapper.update(matchEvent);
        logger.info("比赛事件更新成功: {}", matchEvent.getEventId());
    }

    @Override
    public void deleteEvent(String eventId) {
        logger.info("删除比赛事件: {}", eventId);

        MatchEvent existingEvent = matchEventMapper.findById(eventId);
        if (existingEvent == null) {
            throw new RuntimeException("比赛事件不存在: " + eventId);
        }

        matchEventMapper.delete(eventId);
        logger.info("比赛事件删除成功: {}", eventId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getEventsByMatch(String matchId) {
        logger.info("查询比赛 {} 的所有事件", matchId);
        return matchEventMapper.findByMatchId(matchId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getEventsByPlayer(String playerId) {
        logger.info("查询选手 {} 的所有事件", playerId);
        return matchEventMapper.findByPlayerId(playerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getEventsByMatchAndType(String matchId, String eventType) {
        logger.info("查询比赛 {} 的 {} 事件", matchId, eventType);
        return matchEventMapper.findByMatchIdAndType(matchId, eventType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getEventsWithDetails(String matchId) {
        logger.info("查询比赛 {} 的详细事件信息", matchId);
        return matchEventMapper.findByMatchId(matchId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getEventStatsByMatch(String matchId) {
        logger.info("获取比赛 {} 的事件统计", matchId);
        return matchEventMapper.getEventStatsByMatch(matchId);
    }
}