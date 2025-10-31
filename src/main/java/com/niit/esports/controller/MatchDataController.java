package com.niit.esports.controller;

import com.niit.esports.entity.Match;
import com.niit.esports.entity.MatchPickBan;
import com.niit.esports.entity.MatchPlayerStats;
import com.niit.esports.service.MatchPickBanService;
import com.niit.esports.service.MatchPlayerStatsService;
import com.niit.esports.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/analysis/matchdata")
public class MatchDataController {

    private static final Logger logger = LoggerFactory.getLogger(MatchDataController.class);

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchPickBanService matchPickBanService;
    
    @Autowired
    private MatchPlayerStatsService matchPlayerStatsService;

    /**
     * 比赛数据根路径 - 显示比赛列表供选择
     */
    @GetMapping
    public String matchDataRoot(Model model) {
        logger.info("访问比赛数据根路径，显示比赛列表");

        try {
            List<Match> matches = matchService.getAllMatches();
            model.addAttribute("matches", matches);
            model.addAttribute("pageTitle", "选择比赛查看数据");
        } catch (Exception e) {
            logger.error("获取比赛列表时出错", e);
            model.addAttribute("error", "获取比赛数据时发生错误: " + e.getMessage());
            model.addAttribute("matches", new ArrayList<>());
        }

        return "matchdata/match-list";
    }

    /**
     * 比赛数据主页 - 显示比赛的详细数据
     */
    @GetMapping("/match/{matchId}")
    public String matchData(@PathVariable String matchId, Model model) {
        logger.info("访问比赛 {} 的详细数据", matchId);

        try {
            // 获取比赛信息
            Match match = matchService.getMatchById(matchId);
            
            // 获取比赛统计数据
            List<MatchPlayerStats> playerStatsList = matchPlayerStatsService.getStatsByMatchId(matchId);

            // 按对局分组数据（每10条为一个对局）
            Map<Integer, List<MatchPlayerStats>> statsByRound = groupStatsByRound(playerStatsList);

            model.addAttribute("match", match);
            model.addAttribute("playerStatsList", playerStatsList);
            model.addAttribute("statsByRound", statsByRound);
            model.addAttribute("matchId", matchId);
            model.addAttribute("pageTitle", "比赛数据详情");
        } catch (Exception e) {
            logger.error("获取比赛数据时出错", e);
            model.addAttribute("error", "获取数据时发生错误: " + e.getMessage());
            model.addAttribute("playerStatsList", new ArrayList<>());
            model.addAttribute("statsByRound", new HashMap<>());
            model.addAttribute("matchId", matchId);
        }

        return "matchdata/match-data";
    }

    /**
     * 按对局对玩家统计数据进行分组（每10条为一个对局）
     * @param statsList 玩家统计数据列表
     * @return 按对局分组的Map
     */
    private Map<Integer, List<MatchPlayerStats>> groupStatsByRound(List<MatchPlayerStats> statsList) {
        Map<Integer, List<MatchPlayerStats>> grouped = new HashMap<>();

        if (statsList != null) {
            // 按照stats_id的数字部分进行排序和分组
            for (int i = 0; i < statsList.size(); i++) {
                MatchPlayerStats stats = statsList.get(i);
                String statsId = stats.getStatsId();
                
                // 提取stats_id中的数字部分（假设格式为"001","002"等）
                try {
                    // 从stats_id中提取数字部分
                    String numericPart = statsId.replaceAll("\\D+", ""); // 移除非数字字符
                    if (!numericPart.isEmpty()) {
                        int numericValue = Integer.parseInt(numericPart);
                        // 计算对局号：(数值 - 1) / 10 + 1
                        int roundNumber = (numericValue - 1) / 10 + 1;
                        grouped.computeIfAbsent(roundNumber, k -> new ArrayList<>()).add(stats);
                    } else {
                        // 如果无法提取数字，按顺序分组
                        int roundNumber = (i / 10) + 1;
                        grouped.computeIfAbsent(roundNumber, k -> new ArrayList<>()).add(stats);
                    }
                } catch (NumberFormatException e) {
                    // 如果转换失败，按顺序分组
                    int roundNumber = (i / 10) + 1;
                    grouped.computeIfAbsent(roundNumber, k -> new ArrayList<>()).add(stats);
                }
            }
        }

        return grouped;
    }
}