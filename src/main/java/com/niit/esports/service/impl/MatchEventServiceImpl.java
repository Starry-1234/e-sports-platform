package com.niit.esports.service.impl;

import com.niit.esports.mapper.MatchEventMapper;
import com.niit.esports.entity.MatchEvent;
import com.niit.esports.service.MatchEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MatchEventServiceImpl implements MatchEventService {

    private static final Logger logger = LoggerFactory.getLogger(MatchEventServiceImpl.class);

    @Autowired
    private MatchEventMapper matchEventDao;

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getAllEvents() {
        logger.info("查询所有比赛事件");
        return matchEventDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public MatchEvent getEventById(String eventId) {
        logger.debug("根据ID查询比赛事件: {}", eventId);
        return matchEventDao.findById(eventId);
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
        if (matchEvent.getPlayerId() == null || matchEvent.getPlayerId().trim().isEmpty()) {
            throw new IllegalArgumentException("选手ID不能为空");
        }

        matchEventDao.insert(matchEvent);
        logger.info("比赛事件添加成功: {}", matchEvent.getEventId());
    }

    @Override
    public void updateEvent(MatchEvent matchEvent) {
        logger.info("更新比赛事件: {}", matchEvent.getEventId());

        MatchEvent existingEvent = matchEventDao.findById(matchEvent.getEventId());
        if (existingEvent == null) {
            throw new RuntimeException("比赛事件不存在: " + matchEvent.getEventId());
        }

        matchEventDao.update(matchEvent);
        logger.info("比赛事件更新成功: {}", matchEvent.getEventId());
    }

    @Override
    public void deleteEvent(String eventId) {
        logger.info("删除比赛事件: {}", eventId);

        MatchEvent existingEvent = matchEventDao.findById(eventId);
        if (existingEvent == null) {
            throw new RuntimeException("比赛事件不存在: " + eventId);
        }

        matchEventDao.delete(eventId);
        logger.info("比赛事件删除成功: {}", eventId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getEventsByMatch(String matchId) {
        logger.info("查询比赛 {} 的所有事件", matchId);
        return matchEventDao.findByMatchId(matchId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getEventsByPlayer(String playerId) {
        logger.info("查询选手 {} 的所有事件", playerId);
        return matchEventDao.findByPlayerId(playerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getEventsByMatchAndType(String matchId, String eventType) {
        logger.info("查询比赛 {} 的 {} 事件", matchId, eventType);
        return matchEventDao.findByMatchIdAndType(matchId, eventType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getKillEvents(String matchId) {
        logger.info("查询比赛 {} 的击杀事件", matchId);
        return matchEventDao.findKillEvents(matchId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getImportantEvents(String matchId) {
        logger.info("查询比赛 {} 的重要事件", matchId);
        return matchEventDao.findImportantEvents(matchId);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getMatchEventStats(String matchId) {
        logger.info("获取比赛 {} 的事件统计", matchId);
        Map<String, Object> stats = new HashMap<>();

        List<Map<String, Object>> eventStats = matchEventDao.getEventStatsByMatch(matchId);
        stats.put("eventStats", eventStats);

        // 计算总事件数
        int totalEvents = eventStats.stream()
                .mapToInt(stat -> ((Long) stat.get("event_count")).intValue())
                .sum();
        stats.put("totalEvents", totalEvents);

        // 计算总金币
        long totalGold = eventStats.stream()
                .mapToLong(stat -> ((Number) stat.get("total_gold")).longValue())
                .sum();
        stats.put("totalGold", totalGold);

        logger.debug("比赛 {} 事件统计: 总事件数={}, 总金币={}", matchId, totalEvents, totalGold);
        return stats;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getPlayerEventStats(String matchId, String playerId) {
        logger.info("获取选手 {} 在比赛 {} 中的事件统计", playerId, matchId);
        Map<String, Object> stats = new HashMap<>();

        List<Map<String, Object>> playerStats = matchEventDao.getPlayerEventStats(matchId, playerId);
        if (!playerStats.isEmpty()) {
            stats.putAll(playerStats.get(0));

            // 计算KDA
            int kills = ((Long) stats.getOrDefault("kills", 0L)).intValue();
            int deaths = ((Long) stats.getOrDefault("deaths", 0L)).intValue();
            int assists = ((Long) stats.getOrDefault("assists", 0L)).intValue();

            double kda = deaths == 0 ? kills + assists : (kills + assists) / (double) deaths;
            stats.put("kda", String.format("%.2f", kda));
        }

        return stats;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getRecentEvents(String matchId, String lastEventId) {
        logger.debug("获取比赛 {} 的最近事件，最后事件ID: {}", matchId, lastEventId);
        return matchEventDao.findRecentEvents(matchId, lastEventId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getMatchTimeline(String matchId) {
        logger.info("获取比赛 {} 的时间线", matchId);
        return matchEventDao.getMatchTimeline(matchId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getEventsByMatchWithPage(String matchId, int page, int size) {
        logger.info("分页查询比赛 {} 的事件，页码: {}, 页大小: {}", matchId, page, size);
        int offset = (page - 1) * size;
        return matchEventDao.findByMatchIdWithPage(matchId, offset, size);
    }

    @Override
    @Transactional(readOnly = true)
    public int getEventCountByMatch(String matchId) {
        logger.debug("统计比赛 {} 的事件数量", matchId);
        return matchEventDao.countByMatchId(matchId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchEvent> getEventsByMatchAndPlayer(String matchId, String playerId) {
        logger.info("查询选手 {} 在比赛 {} 中的事件", playerId, matchId);
        return matchEventDao.findByMatchAndPlayer(matchId, playerId);
    }

    @Override
    public void batchInsertEvents(List<MatchEvent> events) {
        logger.info("批量插入 {} 个比赛事件", events.size());

        for (MatchEvent event : events) {
            try {
                matchEventDao.insert(event);
            } catch (Exception e) {
                logger.error("批量插入事件失败: {}", event.getEventId(), e);
                throw new RuntimeException("批量插入事件失败", e);
            }
        }

        logger.info("批量插入事件完成");
    }
}