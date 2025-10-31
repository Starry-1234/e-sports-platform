package com.niit.esports.service.impl;

import com.niit.esports.mapper.MatchPickBanMapper;
import com.niit.esports.entity.MatchPickBan;
import com.niit.esports.service.MatchPickBanService;
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
public class MatchPickBanServiceImpl implements MatchPickBanService {
    
    private static final Logger logger = LoggerFactory.getLogger(MatchPickBanServiceImpl.class);
    
    @Autowired
    private MatchPickBanMapper matchPickBanDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<MatchPickBan> getAllPickBans() {
        logger.info("查询所有PickBan记录");
        return matchPickBanDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public MatchPickBan getPickBanById(String pickBanId) {
        logger.debug("根据ID查询PickBan记录: {}", pickBanId);
        return matchPickBanDao.findById(pickBanId);
    }
    
    @Override
    public void addPickBan(MatchPickBan matchPickBan) {
        logger.info("添加PickBan记录: {} - {}", matchPickBan.getAction(), matchPickBan.getPickBanId());
        
        // 验证必要字段
        if (matchPickBan.getPickBanId() == null || matchPickBan.getPickBanId().trim().isEmpty()) {
            throw new IllegalArgumentException("记录ID不能为空");
        }
        if (matchPickBan.getMatchId() == null || matchPickBan.getMatchId().trim().isEmpty()) {
            throw new IllegalArgumentException("比赛ID不能为空");
        }
        if (matchPickBan.getTeamId() == null || matchPickBan.getTeamId().trim().isEmpty()) {
            throw new IllegalArgumentException("队伍ID不能为空");
        }
        if (matchPickBan.getAction() == null || matchPickBan.getAction().trim().isEmpty()) {
            throw new IllegalArgumentException("操作类型不能为空");
        }
        if (matchPickBan.getTurn() == null) {
            throw new IllegalArgumentException("顺序不能为空");
        }
        
        matchPickBanDao.insert(matchPickBan);
        logger.info("PickBan记录添加成功: {}", matchPickBan.getPickBanId());
    }
    
    @Override
    public void updatePickBan(MatchPickBan matchPickBan) {
        logger.info("更新PickBan记录: {}", matchPickBan.getPickBanId());
        
        MatchPickBan existingPickBan = matchPickBanDao.findById(matchPickBan.getPickBanId());
        if (existingPickBan == null) {
            throw new RuntimeException("PickBan记录不存在: " + matchPickBan.getPickBanId());
        }
        
        matchPickBanDao.update(matchPickBan);
        logger.info("PickBan记录更新成功: {}", matchPickBan.getPickBanId());
    }
    
    @Override
    public void deletePickBan(String pickBanId) {
        logger.info("删除PickBan记录: {}", pickBanId);
        
        MatchPickBan existingPickBan = matchPickBanDao.findById(pickBanId);
        if (existingPickBan == null) {
            throw new RuntimeException("PickBan记录不存在: " + pickBanId);
        }
        
        matchPickBanDao.delete(pickBanId);
        logger.info("PickBan记录删除成功: {}", pickBanId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MatchPickBan> getPickBansByMatch(String matchId) {
        logger.info("查询比赛 {} 的所有PickBan记录", matchId);
        List<MatchPickBan> pickBans = matchPickBanDao.findByMatchId(matchId);
        
        // 为每条记录计算对局号（每20条为一个对局）
        if (pickBans != null) {
            for (MatchPickBan pickBan : pickBans) {
                if (pickBan.getTurn() != null) {
                    // 计算对局号：(turn - 1) / 20 + 1
                    int roundNumber = (pickBan.getTurn() - 1) / 20 + 1;
                    pickBan.setRoundNumber(roundNumber);
                }
            }
        }
        
        return pickBans;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MatchPickBan> getPickBansByTeam(String teamId) {
        logger.info("查询队伍 {} 的所有PickBan记录", teamId);
        return matchPickBanDao.findByTeamId(teamId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MatchPickBan> getPickBansByMatchAndTeam(String matchId, String teamId) {
        logger.info("查询比赛 {} 队伍 {} 的所有PickBan记录", matchId, teamId);
        return matchPickBanDao.findByMatchIdAndTeamId(matchId, teamId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MatchPickBan> getPicksByMatch(String matchId) {
        logger.info("查询比赛 {} 的所有选择记录", matchId);
        return matchPickBanDao.findByMatchIdAndAction(matchId, "PICK");
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MatchPickBan> getBansByMatch(String matchId) {
        logger.info("查询比赛 {} 的所有禁用记录", matchId);
        return matchPickBanDao.findByMatchIdAndAction(matchId, "BAN");
    }
    
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getPickBanStats(String matchId) {
        logger.info("获取比赛 {} 的PickBan统计", matchId);
        Map<String, Object> stats = new HashMap<>();
        
        try {
            int totalPicks = matchPickBanDao.countByMatchIdAndAction(matchId, "PICK");
            int totalBans = matchPickBanDao.countByMatchIdAndAction(matchId, "BAN");
            int totalRecords = matchPickBanDao.countByMatchId(matchId);
            
            stats.put("totalPicks", totalPicks);
            stats.put("totalBans", totalBans);
            stats.put("totalRecords", totalRecords);
            
            // 计算总对局数量
            int totalRounds = 0;
            if (totalRecords > 0) {
                // 假设turn是连续的，从1开始
                totalRounds = (totalRecords - 1) / 20 + 1;
            }
            stats.put("totalRounds", totalRounds);
            
            logger.debug("比赛 {} PickBan统计: 总记录数={}, 选择数={}, 禁用数={}, 总对局={}", matchId, totalRecords, totalPicks, totalBans, totalRounds);
        } catch (Exception e) {
            logger.error("获取比赛 {} 的PickBan统计时出错", matchId, e);
            stats.put("totalPicks", 0);
            stats.put("totalBans", 0);
            stats.put("totalRecords", 0);
            stats.put("totalRounds", 0);
        }
        return stats;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getTeamPickBanStats(String matchId, String teamId) {
        logger.info("获取比赛 {} 队伍 {} 的PickBan统计", matchId, teamId);
        Map<String, Object> stats = new HashMap<>();
        
        // 这里可以添加更详细的队伍级别统计
        List<MatchPickBan> teamPickBans = matchPickBanDao.findByMatchIdAndTeamId(matchId, teamId);
        stats.put("teamPickBans", teamPickBans);
        stats.put("count", teamPickBans.size());
        
        return stats;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MatchPickBan> getPickBansByMatchWithPage(String matchId, int page, int size) {
        logger.info("分页查询比赛 {} 的PickBan记录，页码: {}, 页大小: {}", matchId, page, size);
        int offset = (page - 1) * size;
        return matchPickBanDao.findByMatchIdWithPage(matchId, offset, size);
    }
    
    @Override
    @Transactional(readOnly = true)
    public int getPickBanCountByMatch(String matchId) {
        logger.debug("统计比赛 {} 的PickBan记录数量", matchId);
        return matchPickBanDao.countByMatchId(matchId);
    }
}