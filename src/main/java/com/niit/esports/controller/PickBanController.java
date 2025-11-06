package com.niit.esports.controller;

import com.niit.esports.entity.Match;
import com.niit.esports.entity.MatchPickBan;
import com.niit.esports.entity.Player;
import com.niit.esports.service.MatchPickBanService;
import com.niit.esports.service.MatchService;
import com.niit.esports.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/analysis/pickban")
public class PickBanController {

    private static final Logger logger = LoggerFactory.getLogger(PickBanController.class);
    
    @Autowired
    private MatchPickBanService matchPickBanService;
    
    @Autowired
    private MatchService matchService;
    
    @Autowired
    private PlayerService playerService;
    
    /**
     * BP分析根路径 - 显示比赛列表供选择
     */
    @GetMapping
    public String pickBanRoot(Model model) {
        logger.info("访问BP分析根路径，显示比赛列表");
        
        try {
            List<Match> matches = matchService.getAllMatches();
            model.addAttribute("matches", matches);
            model.addAttribute("pageTitle", "选择比赛进行BP分析");
        } catch (Exception e) {
            logger.error("获取比赛列表时出错", e);
            model.addAttribute("error", "获取比赛数据时发生错误: " + e.getMessage());
            model.addAttribute("matches", new ArrayList<>());
        }
        
        return "pickban/match-list";
    }

    /**
     * BP分析主页 - 显示比赛的Pick/Ban数据
     */
    @GetMapping("/match/{matchId}")
    public String matchPickBan(@PathVariable String matchId, Model model) {
        logger.info("访问比赛 {} 的BP分析", matchId);

        try {
            // 从数据库获取数据
            List<MatchPickBan> pickBanList = matchPickBanService.getPickBansByMatch(matchId);
            
            // 分离PICK和BAN数据
            List<MatchPickBan> picks = new ArrayList<>();
            List<MatchPickBan> bans = new ArrayList<>();
            
            if (pickBanList != null) {
                for (MatchPickBan pickBan : pickBanList) {
                    if (pickBan.isPick()) {
                        picks.add(pickBan);
                    } else {
                        bans.add(pickBan);
                    }
                }
            }
            
            // 获取选手信息（如果需要的话）
            List<Player> blueTeamPlayers = new ArrayList<>();
            List<Player> redTeamPlayers = new ArrayList<>();
            
            // 如果有pick数据，尝试获取队伍ID来查询选手
            if (!picks.isEmpty()) {
                // 假设第一个pick是蓝队，第二个不同的队伍是红队
                String blueTeamId = null;
                String redTeamId = null;
                
                for (MatchPickBan pick : picks) {
                    if (blueTeamId == null) {
                        blueTeamId = pick.getTeamId();
                    } else if (!blueTeamId.equals(pick.getTeamId()) && redTeamId == null) {
                        redTeamId = pick.getTeamId();
                        break;
                    }
                }
                
                // 根据队伍ID获取选手列表
                if (blueTeamId != null) {
                    blueTeamPlayers = playerService.getPlayersByTeamId(blueTeamId);
                }
                
                if (redTeamId != null) {
                    redTeamPlayers = playerService.getPlayersByTeamId(redTeamId);
                }
            }
            
            Map<String, Object> stats = matchPickBanService.getPickBanStats(matchId);
            
            // 按对局分组数据（每20条为一个对局）
            Map<Integer, List<MatchPickBan>> picksByRound = groupPickBansByRound(picks);
            Map<Integer, List<MatchPickBan>> bansByRound = groupPickBansByRound(bans);

            model.addAttribute("picks", picks != null ? picks : new ArrayList<>());
            model.addAttribute("bans", bans != null ? bans : new ArrayList<>());
            model.addAttribute("picksByRound", picksByRound);
            model.addAttribute("bansByRound", bansByRound);
            model.addAttribute("stats", stats);
            model.addAttribute("matchId", matchId);
            model.addAttribute("blueTeamPlayers", blueTeamPlayers);
            model.addAttribute("redTeamPlayers", redTeamPlayers);
            model.addAttribute("pageTitle", "BP分析");
        } catch (Exception e) {
            logger.error("获取BP分析数据时出错", e);
            model.addAttribute("error", "获取数据时发生错误: " + e.getMessage());
            model.addAttribute("picks", new ArrayList<>());
            model.addAttribute("bans", new ArrayList<>());
            model.addAttribute("picksByRound", new HashMap<>());
            model.addAttribute("bansByRound", new HashMap<>());
            model.addAttribute("stats", new HashMap<>());
            model.addAttribute("matchId", matchId);
            model.addAttribute("blueTeamPlayers", new ArrayList<>());
            model.addAttribute("redTeamPlayers", new ArrayList<>());
        }

        return "pickban/match-pickban";
    }

    /**
     * 添加PickBan记录表单页面
     */
    @GetMapping("/add")
    public String addPickBanForm(@RequestParam(required = false) String matchId, Model model) {
        logger.info("访问添加PickBan记录表单页面");
        
        MatchPickBan pickBan = new MatchPickBan();
        if (matchId != null) {
            pickBan.setMatchId(matchId);
        }
        
        model.addAttribute("pickBan", pickBan);
        model.addAttribute("matchId", matchId);
        model.addAttribute("pageTitle", "添加Pick/Ban记录");
        
        return "pickban/form";
    }

    /**
     * 编辑PickBan记录表单页面
     */
    @GetMapping("/edit/{pickBanId}")
    public String editPickBanForm(@PathVariable String pickBanId, Model model) {
        logger.info("访问编辑PickBan记录表单页面: {}", pickBanId);
        
        try {
            MatchPickBan pickBan = matchPickBanService.getPickBanById(pickBanId);
            
            model.addAttribute("pickBan", pickBan);
            model.addAttribute("matchId", pickBan.getMatchId());
            model.addAttribute("pageTitle", "编辑Pick/Ban记录");
        } catch (Exception e) {
            logger.error("获取PickBan记录时出错: {}", pickBanId, e);
            model.addAttribute("error", "获取记录时发生错误: " + e.getMessage());
            return "redirect:/analysis/pickban/match/MATCH_001";
        }
        
        return "pickban/form";
    }

    /**
     * 保存PickBan记录
     */
    @PostMapping("/save")
    public String savePickBan(MatchPickBan pickBan) {
        logger.info("保存PickBan记录: {} - {}", pickBan.getPickBanId(), pickBan.getAction());
        
        try {
            if (pickBan.getPickBanId() == null || pickBan.getPickBanId().isEmpty()) {
                // 添加新记录
                // 生成ID (实际项目中可能通过数据库自增或其他方式)
                pickBan.setPickBanId("PB_" + System.currentTimeMillis());
                matchPickBanService.addPickBan(pickBan);
            } else {
                // 更新记录
                matchPickBanService.updatePickBan(pickBan);
            }
        } catch (Exception e) {
            logger.error("保存PickBan记录失败", e);
            // 实际项目中应该有错误处理机制
        }
        
        return "redirect:/analysis/pickban/match/" + pickBan.getMatchId();
    }

    /**
     * 删除PickBan记录
     */
    @PostMapping("/delete/{pickBanId}")
    public String deletePickBan(@PathVariable String pickBanId, @RequestParam String matchId) {
        logger.info("删除PickBan记录: {}", pickBanId);
        
        try {
            matchPickBanService.deletePickBan(pickBanId);
        } catch (Exception e) {
            logger.error("删除PickBan记录失败", e);
            // 实际项目中应该有错误处理机制
        }
        
        return "redirect:/analysis/pickban/match/" + matchId;
    }

    /**
     * BP统计分析页面
     */
    @GetMapping("/stats/{matchId}")
    public String pickBanStats(@PathVariable String matchId, Model model) {
        logger.info("访问比赛 {} 的BP统计分析", matchId);
        
        try {
            Map<String, Object> stats = matchPickBanService.getPickBanStats(matchId);
            
            model.addAttribute("stats", stats);
            model.addAttribute("matchId", matchId);
            model.addAttribute("pageTitle", "BP统计分析");
        } catch (Exception e) {
            logger.error("获取BP统计分析数据时出错", e);
            model.addAttribute("error", "获取统计数据时发生错误: " + e.getMessage());
            return "redirect:/analysis/pickban/match/" + matchId;
        }
        
        return "pickban/stats";
    }

    /**
     * 按对局对PickBan记录进行分组（每20条为一个对局）
     * @param pickBans PickBan记录列表
     * @return 按对局分组的Map
     */
    private Map<Integer, List<MatchPickBan>> groupPickBansByRound(List<MatchPickBan> pickBans) {
        Map<Integer, List<MatchPickBan>> grouped = new HashMap<>();
        
        if (pickBans != null) {
            for (MatchPickBan pickBan : pickBans) {
                Integer turn = pickBan.getTurn();
                if (turn != null) {
                    // 计算对局号：(turn - 1) / 20 + 1
                    int roundNumber = (turn - 1) / 20 + 1;
                    grouped.computeIfAbsent(roundNumber, k -> new ArrayList<>()).add(pickBan);
                }
            }
        }
        
        return grouped;
    }
    
    /**
     * 按对局对PickBan记录进行分组
     * @param pickBans PickBan记录列表
     * @param groupSize 每组的记录数
     * @return 按对局分组的Map
     */
    private Map<Integer, List<MatchPickBan>> groupPickBansBySession(List<MatchPickBan> pickBans, int groupSize) {
        Map<Integer, List<MatchPickBan>> grouped = new HashMap<>();
        
        if (pickBans != null) {
            for (MatchPickBan pickBan : pickBans) {
                Integer turn = pickBan.getTurn();
                if (turn != null) {
                    // 计算对局号：(turn - 1) / groupSize + 1
                    int sessionNumber = (turn - 1) / groupSize + 1;
                    grouped.computeIfAbsent(sessionNumber, k -> new ArrayList<>()).add(pickBan);
                }
            }
        }
        
        return grouped;
    }
    
    /**
     * 按队伍对PickBan记录进行分组
     * @param pickBans PickBan记录列表
     * @return 按队伍分组的Map
     */
    private Map<String, List<MatchPickBan>> groupPickBansByTeam(List<MatchPickBan> pickBans) {
        Map<String, List<MatchPickBan>> grouped = new HashMap<>();
        
        if (pickBans != null) {
            for (MatchPickBan pickBan : pickBans) {
                String teamId = pickBan.getTeamId();
                if (teamId != null) {
                    grouped.computeIfAbsent(teamId, k -> new ArrayList<>()).add(pickBan);
                }
            }
        }
        
        return grouped;
    }
}