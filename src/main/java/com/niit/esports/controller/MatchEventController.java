package com.niit.esports.controller;

import com.niit.esports.entity.MatchEvent;
import com.niit.esports.service.MatchEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/matches/events")
public class MatchEventController {

    private static final Logger logger = LoggerFactory.getLogger(MatchEventController.class);

    @Autowired
    private MatchEventService matchEventService;

    /**
     * 比赛事件列表页面
     */
    @GetMapping("/match/{matchId}")
    public String matchEvents(@PathVariable String matchId, Model model) {
        logger.info("访问比赛 {} 的事件列表", matchId);

        List<MatchEvent> events = matchEventService.getEventsByMatch(matchId);
        Map<String, Object> stats = matchEventService.getMatchEventStats(matchId);

        model.addAttribute("events", events);
        model.addAttribute("stats", stats);
        model.addAttribute("matchId", matchId);
        model.addAttribute("pageTitle", "比赛事件列表");

        return "match/events";
    }

    /**
     * 选手事件页面
     */
    @GetMapping("/player/{playerId}")
    public String playerEvents(@PathVariable String playerId, Model model) {
        logger.info("访问选手 {} 的事件列表", playerId);

        List<MatchEvent> events = matchEventService.getEventsByPlayer(playerId);
        model.addAttribute("events", events);
        model.addAttribute("playerId", playerId);
        model.addAttribute("pageTitle", "选手事件记录");

        return "match/player-events";
    }

    /**
     * 实时事件数据（AJAX接口）
     */
    @GetMapping("/realtime/{matchId}")
    @ResponseBody
    public List<MatchEvent> getRealTimeEvents(@PathVariable String matchId,
                                              @RequestParam(required = false) String lastEventId) {
        logger.debug("获取比赛 {} 的实时事件，最后事件ID: {}", matchId, lastEventId);
        return matchEventService.getRecentEvents(matchId, lastEventId != null ? lastEventId : "0");
    }

    /**
     * 事件统计页面
     */
    @GetMapping("/stats/{matchId}")
    public String eventStats(@PathVariable String matchId, Model model) {
        logger.info("访问比赛 {} 的事件统计", matchId);

        Map<String, Object> stats = matchEventService.getMatchEventStats(matchId);
        model.addAttribute("stats", stats);
        model.addAttribute("matchId", matchId);
        model.addAttribute("pageTitle", "事件统计分析");

        return "match/event-stats";
    }

    /**
     * 时间线页面
     */
    @GetMapping("/timeline/{matchId}")
    public String matchTimeline(@PathVariable String matchId, Model model) {
        logger.info("访问比赛 {} 的时间线", matchId);

        List<MatchEvent> timeline = matchEventService.getMatchTimeline(matchId);
        model.addAttribute("timeline", timeline);
        model.addAttribute("matchId", matchId);
        model.addAttribute("pageTitle", "比赛时间线");

        return "match/timeline";
    }

    /**
     * 添加事件页面
     */
    @GetMapping("/add")
    public String addEventForm(Model model) {
        model.addAttribute("matchEvent", new MatchEvent());
        model.addAttribute("pageTitle", "添加比赛事件");
        return "match/event-form";
    }

    /**
     * 保存事件
     */
    @PostMapping("/save")
    public String saveEvent(@ModelAttribute MatchEvent matchEvent) {
        logger.info("保存比赛事件: {}", matchEvent.getEventType());

        if (matchEvent.getEventId() == null || matchEvent.getEventId().isEmpty()) {
            // 生成事件ID（实际项目中应该用更复杂的ID生成策略）
            matchEvent.setEventId("EVENT_" + System.currentTimeMillis());
            matchEventService.addEvent(matchEvent);
        } else {
            matchEventService.updateEvent(matchEvent);
        }

        return "redirect:/matches/events/match/" + matchEvent.getMatchId();
    }

    /**
     * 编辑事件页面
     */
    @GetMapping("/edit/{eventId}")
    public String editEventForm(@PathVariable String eventId, Model model) {
        MatchEvent event = matchEventService.getEventById(eventId);
        model.addAttribute("matchEvent", event);
        model.addAttribute("pageTitle", "编辑比赛事件");
        return "match/event-form";
    }

    /**
     * 删除事件
     */
    @PostMapping("/delete/{eventId}")
    public String deleteEvent(@PathVariable String eventId) {
        logger.info("删除比赛事件: {}", eventId);

        MatchEvent event = matchEventService.getEventById(eventId);
        String matchId = event.getMatchId();
        matchEventService.deleteEvent(eventId);

        return "redirect:/matches/events/match/" + matchId;
    }

    /**
     * 事件类型筛选
     */
    @GetMapping("/match/{matchId}/type/{eventType}")
    public String eventsByType(@PathVariable String matchId,
                               @PathVariable String eventType,
                               Model model) {
        logger.info("筛选比赛 {} 的 {} 事件", matchId, eventType);

        List<MatchEvent> events = matchEventService.getEventsByMatchAndType(matchId, eventType);
        model.addAttribute("events", events);
        model.addAttribute("matchId", matchId);
        model.addAttribute("eventType", eventType);
        model.addAttribute("pageTitle", eventType + "事件列表");

        return "match/events-by-type";
    }
}