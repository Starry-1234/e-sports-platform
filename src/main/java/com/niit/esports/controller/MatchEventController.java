package com.niit.esports.controller;

import com.niit.esports.entity.Match;
import com.niit.esports.entity.MatchEvent;
import com.niit.esports.service.MatchEventService;
import com.niit.esports.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/matches")
public class MatchEventController {

    private static final Logger logger = LoggerFactory.getLogger(MatchEventController.class);

    @Autowired
    private MatchEventService matchEventService;

    @Autowired
    private MatchService matchService;

    /**
     * 比赛列表页面
     */
    @GetMapping("/events")
    public String matchEvents(Model model) {
        logger.info("访问比赛列表页面");

        try {
            List<Match> matches = matchService.getMatchesWithTeamInfo();
            model.addAttribute("matches", matches != null ? matches : new ArrayList<>());
        } catch (Exception e) {
            logger.error("获取比赛列表错误: {}", e.getMessage());
            model.addAttribute("matches", new ArrayList<>());
        }

        model.addAttribute("pageTitle", "选择比赛查看事件");
        return "match/events";
    }

    /**
     * 比赛事件列表页面 - 按小局显示
     */
    @GetMapping("/events/match/{matchId}")
    public String matchEventList(@PathVariable String matchId,
                                 @RequestParam(defaultValue = "1") int round,
                                 Model model) {
        logger.info("访问比赛 {} 的第 {} 局事件列表", matchId, round);

        try {
            // 数据库已经按事件ID排序，我们直接分组即可
            List<MatchEvent> allEvents = matchEventService.getEventsWithDetails(matchId);
            Match currentMatch = matchService.getMatchById(matchId);

            if (allEvents == null) {
                allEvents = new ArrayList<>();
            }

            // 计算总小局数
            int eventsPerRound = 11;
            int totalRounds = (allEvents.size() + eventsPerRound - 1) / eventsPerRound;

            // 获取当前小局的事件（数据库已按ID排序，我们直接分组）
            List<MatchEvent> currentRoundEvents = new ArrayList<>();
            if (!allEvents.isEmpty() && round >= 1 && round <= totalRounds) {
                int startIndex = (round - 1) * eventsPerRound;
                int endIndex = Math.min(round * eventsPerRound, allEvents.size());
                currentRoundEvents = allEvents.subList(startIndex, endIndex);

                // 注意：这里不需要再排序，因为数据库查询时已经按时间排序了
                // 每个小局内的事件会按时间顺序显示
            }

            model.addAttribute("events", currentRoundEvents);
            model.addAttribute("allEvents", allEvents);
            model.addAttribute("matchId", matchId);
            model.addAttribute("currentMatch", currentMatch);
            model.addAttribute("currentRound", round);
            model.addAttribute("totalRounds", totalRounds);
            model.addAttribute("pageTitle", "比赛事件列表 - 第" + round + "局");

        } catch (Exception e) {
            logger.error("加载比赛事件列表时发生错误: {}", e.getMessage(), e);
            model.addAttribute("error", "加载事件数据时发生错误: " + e.getMessage());
            model.addAttribute("events", new ArrayList<>());
            model.addAttribute("totalRounds", 0);
            model.addAttribute("currentRound", 1);
        }

        return "match/event-list";
    }


    /**
     * 事件统计页面
     */
    @GetMapping("/events/stats/{matchId}")
    public String eventStats(@PathVariable String matchId, Model model) {
        logger.info("访问比赛 {} 的事件统计", matchId);

        try {
            Map<String, Object> stats = new HashMap<>();
            List<MatchEvent> events = matchEventService.getEventsWithDetails(matchId);
            int totalEvents = events != null ? events.size() : 0;
            int totalRounds = (totalEvents + 10) / 11;

            stats.put("totalEvents", totalEvents);
            stats.put("totalRounds", totalRounds);
            stats.put("eventStats", matchEventService.getEventStatsByMatch(matchId));

            model.addAttribute("stats", stats);
        } catch (Exception e) {
            logger.error("加载统计错误: {}", e.getMessage());
            model.addAttribute("stats", new HashMap<>());
        }

        model.addAttribute("matchId", matchId);
        model.addAttribute("pageTitle", "事件统计分析");
        return "match/event-stats";
    }

    /**
     * 编辑事件页面
     */
    @GetMapping("/events/edit/{eventId}")
    public String editEventForm(@PathVariable String eventId, Model model) {
        try {
            MatchEvent event = matchEventService.getEventById(eventId);
            model.addAttribute("matchEvent", event);
        } catch (Exception e) {
            logger.error("获取事件错误: {}", e.getMessage());
            model.addAttribute("matchEvent", new MatchEvent());
        }
        model.addAttribute("pageTitle", "编辑比赛事件");
        return "match/event-form";
    }

    /**
     * 保存事件
     */
    @PostMapping("/events/save")
    public String saveEvent(@ModelAttribute MatchEvent matchEvent) {
        logger.info("保存比赛事件: {}", matchEvent.getEventType());

        try {
            if (matchEvent.getEventId() == null || matchEvent.getEventId().isEmpty()) {
                matchEvent.setEventId("EVENT_" + System.currentTimeMillis());
                matchEventService.addEvent(matchEvent);
            } else {
                matchEventService.updateEvent(matchEvent);
            }
        } catch (Exception e) {
            logger.error("保存事件错误: {}", e.getMessage());
        }

        return "redirect:/matches/events/match/" + matchEvent.getMatchId();
    }

    /**
     * 删除事件
     */
    @PostMapping("/events/delete/{eventId}")
    public String deleteEvent(@PathVariable String eventId, @RequestParam String matchId) {
        logger.info("删除比赛事件: {}", eventId);

        try {
            matchEventService.deleteEvent(eventId);
        } catch (Exception e) {
            logger.error("删除事件错误: {}", e.getMessage());
        }

        return "redirect:/matches/events/match/" + matchId;
    }
}